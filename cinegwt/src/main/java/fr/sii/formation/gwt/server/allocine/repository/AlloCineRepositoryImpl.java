package fr.sii.formation.gwt.server.allocine.repository;

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
import fr.sii.formation.gwt.server.repository.file.FileMovieRepository;

@Repository
public class AlloCineRepositoryImpl implements AlloCineRepository {

	private final static Logger LOGGER = LoggerFactory.getLogger(AlloCineRepositoryImpl.class);

	private static final String QUERY = "http://api.allocine.fr/rest/v3/movie?partner=YW5kcm9pZC12M3M&profile=medium&format=json&filter=movie,person&striptags=synopsis,synopsisshort&code=";

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
		String query = QUERY + idMovie;

		LOGGER.debug("Query : {}", query);

		AlloCineMovie alloCineMovie = null;
		try {
			HttpHeaders headers = new HttpHeaders();
			headers.set("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/535.7 (KHTML, like Gecko) Chrome/16.0.912.75 Safari/535.7");
			HttpEntity<String> entity = new HttpEntity<String>(headers);

			ResponseEntity<AlloCineMovie> response = restTemplate.exchange(query, HttpMethod.GET, entity, AlloCineMovie.class);

			alloCineMovie = response.getBody();

			if (alloCineMovie != null) {
				fileRepository.add(idMovie, alloCineMovie);
			}

		} catch (Exception e) {
			throw new RetrieveException("Retrive problem for movie id : " + idMovie, e);
		}

		return alloCineMovie;
	}
}
