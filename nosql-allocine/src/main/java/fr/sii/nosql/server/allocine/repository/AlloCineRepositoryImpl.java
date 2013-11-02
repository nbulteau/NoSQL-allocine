package fr.sii.nosql.server.allocine.repository;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
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

import fr.sii.nosql.server.allocine.buisiness.AlloCineMovie;
import fr.sii.nosql.server.allocine.buisiness.MovieListResult;
import fr.sii.nosql.server.allocine.buisiness.MovieResult;
import fr.sii.nosql.server.repository.file.FileMovieRepository;

@Repository
public class AlloCineRepositoryImpl implements AlloCineRepository {

	private final static Logger LOGGER = LoggerFactory.getLogger(AlloCineRepositoryImpl.class);

	private static final String API_URL = "http://api.allocine.fr/rest/v3";

	private static final String ALLOCINE_PARTNER_KEY = "100043982026";

	private static final String ALLOCINE_SECRET_KEY = "29d185d98c984a359e6e6f26a0474269";

	private static final String USER_AGENT = "Dalvik/1.6.0 (Linux; U; Android 4.2.2; Nexus 4 Build/JDQ39E)";

	private static final String MOVIE_QUERY_PARAMS = "&format=json&profile=medium&filter=movie,person&striptags=synopsis%2Csynopsisshort";

	private static final String MOVIELIST_QUERY_PARAMS = "&filter=nowshowing&format=json";

	private final RestTemplate restTemplate;

	private final FileMovieRepository fileRepository;

	private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("YYYYMMdd");

	@Autowired
	public AlloCineRepositoryImpl(RestTemplate restTemplate, @Qualifier("fileMovieRepository") FileMovieRepository fileRepository) {
		super();
		this.restTemplate = restTemplate;
		this.fileRepository = fileRepository;
	}

	@Override
	public AlloCineMovie retrieveMovie(long idMovie) throws RetrieveException {
		String query = API_URL + "/movie?";

		String params = "partner=" + ALLOCINE_PARTNER_KEY;
		params += "&code=" + idMovie;
		params += MOVIE_QUERY_PARAMS;
		params += "&sed=" + simpleDateFormat.format(new Date());

		MovieResult movieResult = null;

		try {
			String sig = buildAlloCineSignature(params);
			query += params + sig;
			LOGGER.debug("Query : {}", query);

			URI uri = URI.create(query);
			ResponseEntity<MovieResult> response = restTemplate.exchange(uri, HttpMethod.GET, getHttpEntity(), MovieResult.class);
			movieResult = response.getBody();

			if (movieResult != null) {
				fileRepository.save(idMovie, movieResult);
			}
		} catch (Exception e) {
			LOGGER.error("Exception : {}", e.getMessage());
			throw new RetrieveException("Retrieve problem for movie id : " + idMovie, e);
		}

		return movieResult.getMovie();
	}

	@Override
	public List<AlloCineMovie> retrieveMovielist() throws RetrieveException {

		List<AlloCineMovie> alloCineMovies = new ArrayList<>();

		int totalResults = Integer.MAX_VALUE;
		int count = 10;
		int page = 1;

		while (count * page < totalResults) {
			String query = API_URL + "/movielist?";

			String params = "partner=" + ALLOCINE_PARTNER_KEY;
			// params += "&count=" + count;
			params += MOVIELIST_QUERY_PARAMS;
			params += "&page=" + page;
			params += "&sed=" + simpleDateFormat.format(new Date());

			try {
				String sig = buildAlloCineSignature(params);
				query += params + sig;
				LOGGER.debug("Query : {}", query);

				URI uri = URI.create(query);
				ResponseEntity<MovieListResult> response = restTemplate.exchange(uri, HttpMethod.GET, getHttpEntity(), MovieListResult.class);
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
		headers.set("User-Agent", USER_AGENT);
		HttpEntity<String> httpEntity = new HttpEntity<String>(headers);
		return httpEntity;
	}

	private String buildAlloCineSignature(String params) throws UnsupportedEncodingException {
		LOGGER.debug("Params : {}", params);

		String sha1params = computeSha1OfString(ALLOCINE_SECRET_KEY + params);
		return "&sig=" + URLEncoder.encode(sha1params, "UTF-8");
	}

	/**
	 * Compute a SHA-1 hash of a String argument
	 * 
	 * @param arg
	 *            the UTF-8 String to encode
	 * @return the sha1 hash as a string.
	 */
	public static String computeSha1OfString(String arg) {
		try {
			return computeSha1OfByteArray(arg.getBytes(("UTF-8")));
		} catch (UnsupportedEncodingException ex) {
			throw new UnsupportedOperationException(ex);
		}
	}

	private static String computeSha1OfByteArray(byte[] arg) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(arg);
			byte[] res = md.digest();
			return new String(Base64.encodeBase64(res));
		} catch (NoSuchAlgorithmException ex) {
			throw new UnsupportedOperationException(ex);
		}
	}
}
