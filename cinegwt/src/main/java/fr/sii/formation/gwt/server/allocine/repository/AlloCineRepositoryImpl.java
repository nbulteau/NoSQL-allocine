package fr.sii.formation.gwt.server.allocine.repository;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import fr.sii.formation.gwt.server.allocine.buisiness.AlloCineMovie;
import fr.sii.formation.gwt.server.allocine.buisiness.AlloCineMovieList;
import fr.sii.formation.gwt.server.allocine.buisiness.Movie;
import fr.sii.formation.gwt.server.repository.file.FileMovieRepository;

@Repository
public class AlloCineRepositoryImpl implements AlloCineRepository {

	private final static Logger LOGGER = LoggerFactory.getLogger(AlloCineRepositoryImpl.class);

	private static final String MOVIE_QUERY = "http://api.allocine.fr/rest/v3/movie?partner=YW5kcm9pZC12M3M&profile=medium&format=json&filter=movie,person&striptags=synopsis,synopsisshort&code=";

	private static final String MOVIELIST_QUERY = "http://api.allocine.fr/rest/v3/movielist?partner=YW5kcm9pZC12M3M&filter=nowshowing&profile=small&order=datedesc&format=json";

	private final RestTemplate restTemplate;

	private final FileMovieRepository fileRepository;

	@Autowired
	public AlloCineRepositoryImpl(RestTemplate restTemplate, @Qualifier("fileMovieRepository") FileMovieRepository fileRepository) {
		super();
		this.restTemplate = restTemplate;
		this.fileRepository = fileRepository;
	}

	@Override
	public Movie retrieveMovie(long idMovie) throws RetrieveException {
		String query = MOVIE_QUERY + idMovie;

		LOGGER.debug("Query : {}", query);

		AlloCineMovie alloCineMovie = null;
		try {
			ResponseEntity<AlloCineMovie> response = restTemplate.exchange(query, HttpMethod.GET, getHttpEntity(), AlloCineMovie.class);

			alloCineMovie = response.getBody();

			if (alloCineMovie != null) {
				fileRepository.add(idMovie, alloCineMovie);
			}

		} catch (Exception e) {
			throw new RetrieveException("Retrieve problem for movie id : " + idMovie, e);
		}

		return alloCineMovie.getMovie();
	}

	@Override
	public List<Movie> retrieveMovielist() throws RetrieveException {

		List<Movie> movies = new ArrayList<>();

		int totalResults = Integer.MAX_VALUE;
		int count = 50;
		int page = 1;

		while (count * page < totalResults) {
			String query = MOVIELIST_QUERY + "&count=" + count + "&page=" + page;
			LOGGER.info("Query : {}", query);

			try {
				ResponseEntity<AlloCineMovieList> response = restTemplate.exchange(query, HttpMethod.GET, getHttpEntity(), AlloCineMovieList.class);

				AlloCineMovieList alloCineMovieList = response.getBody();
				movies.addAll(alloCineMovieList.getFeed().getMovie());

				totalResults = alloCineMovieList.getFeed().getTotalResults();
				page = alloCineMovieList.getFeed().getPage() + 1;
				Thread.sleep(2000); // Tempo for AlloCine
			} catch (Exception e) {
				throw new RetrieveException(e);
			}
		}

		return movies;
	}

	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		return httpEntity;
	}

}
