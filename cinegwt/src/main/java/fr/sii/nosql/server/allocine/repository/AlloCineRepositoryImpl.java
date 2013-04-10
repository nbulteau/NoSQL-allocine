package fr.sii.nosql.server.allocine.repository;

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

import fr.sii.nosql.server.allocine.buisiness.MovieResult;
import fr.sii.nosql.server.allocine.buisiness.MovieListResult;
import fr.sii.nosql.server.allocine.buisiness.AlloCineMovie;
import fr.sii.nosql.server.repository.file.FileMovieRepository;

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
	public AlloCineMovie retrieveMovie(long idMovie) throws RetrieveException {
		String query = MOVIE_QUERY + idMovie;

		LOGGER.debug("Query : {}", query);

		MovieResult movieResult = null;
		try {
			ResponseEntity<MovieResult> response = restTemplate.exchange(query, HttpMethod.GET, getHttpEntity(), MovieResult.class);

			movieResult = response.getBody();

			if (movieResult != null) {
				fileRepository.add(idMovie, movieResult);
			}

		} catch (Exception e) {
			throw new RetrieveException("Retrieve problem for movie id : " + idMovie, e);
		}

		return movieResult.getMovie();
	}

	@Override
	public List<AlloCineMovie> retrieveMovielist() throws RetrieveException {

		List<AlloCineMovie> alloCineMovies = new ArrayList<>();

		int totalResults = Integer.MAX_VALUE;
		int count = 50;
		int page = 1;

		while (count * page < totalResults) {
			String query = MOVIELIST_QUERY + "&count=" + count + "&page=" + page;
			LOGGER.info("Query : {}", query);

			try {
				ResponseEntity<MovieListResult> response = restTemplate.exchange(query, HttpMethod.GET, getHttpEntity(), MovieListResult.class);

				MovieListResult movieListResult = response.getBody();
				alloCineMovies.addAll(movieListResult.getFeed().getMovie());

				totalResults = movieListResult.getFeed().getTotalResults();
				page = movieListResult.getFeed().getPage() + 1;
				Thread.sleep(2000); // Tempo for AlloCine
			} catch (Exception e) {
				throw new RetrieveException(e);
			}
		}

		return alloCineMovies;
	}

	private HttpEntity<String> getHttpEntity() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		return httpEntity;
	}

}
