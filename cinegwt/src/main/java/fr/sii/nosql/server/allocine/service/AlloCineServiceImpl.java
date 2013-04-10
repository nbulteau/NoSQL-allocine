package fr.sii.nosql.server.allocine.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sii.nosql.server.allocine.buisiness.Movie;
import fr.sii.nosql.server.allocine.repository.AlloCineRepository;
import fr.sii.nosql.server.allocine.repository.RetrieveException;
import fr.sii.nosql.server.service.MovieServiceException;

@Service
public class AlloCineServiceImpl implements AlloCineService {
	private final static Logger LOGGER = LoggerFactory.getLogger(AlloCineServiceImpl.class);

	private static final String HTTP_WWW_ALLOCINE_FR = "http://www.allocine.fr/film/fichefilm_gen_cfilm=";

	private final MovieMapperService movieMapperService;

	private final AlloCineRepository alloCineRepository;

	@Autowired
	public AlloCineServiceImpl(MovieMapperService movieMapper, AlloCineRepository alloCineRepository) {
		super();
		this.movieMapperService = movieMapper;
		this.alloCineRepository = alloCineRepository;
	}

	@Override
	public fr.sii.nosql.shared.buisiness.Movie retrieveMovie(long idMovie) {
		fr.sii.nosql.shared.buisiness.Movie movie = null;
		try {
			Movie alloCineMovie = alloCineRepository.retrieveMovie(idMovie);
			LOGGER.info("retrieveMovie id : {} => {} ", alloCineMovie.getCode(), alloCineMovie.getTitle());
			movie = movieMapperService.convertToBuisinessObject(alloCineMovie);
		} catch (RetrieveException e) {
			LOGGER.error("Retrieve movie problem with movie id : {} ", idMovie);
		} catch (ConvertException e) {
			LOGGER.error("Convert movie problem with movie id : {} ", idMovie);
		}

		return movie;
	}

	@Override
	public List<fr.sii.nosql.shared.buisiness.Movie> retrieveMovieList() {
		List<fr.sii.nosql.shared.buisiness.Movie> movies = new ArrayList<>();
		try {
			List<Movie> alloCineMovies = alloCineRepository.retrieveMovielist();

			fr.sii.nosql.shared.buisiness.Movie movie = null;
			for (Movie alloCineMovie : alloCineMovies) {
				movie = retrieveMovie(alloCineMovie.getCode());
				if (movie != null) {
					movies.add(movie);
				}
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {

				} // Tempo for AlloCine
			}
		} catch (RetrieveException e) {
			LOGGER.error("Retrieve movielist problem");
		}

		return movies;
	}

	@Override
	public List<fr.sii.nosql.shared.buisiness.Movie> retrieveMovies(String inputFileName) throws MovieServiceException {
		List<fr.sii.nosql.shared.buisiness.Movie> movies = new ArrayList<>();

		FileInputStream fileInputStream = null;
		BufferedReader br = null;
		try {
			fileInputStream = new FileInputStream(inputFileName);
			br = new BufferedReader(new InputStreamReader(fileInputStream));

			String alloCineString = null;
			while ((alloCineString = br.readLine()) != null) {
				if (alloCineString.contains(HTTP_WWW_ALLOCINE_FR)) {
					String id = alloCineString.replace(HTTP_WWW_ALLOCINE_FR, "").replace(".html", "");
					fr.sii.nosql.shared.buisiness.Movie movie = retrieveMovie(Long.parseLong(id));
					if (movie != null) {
						movies.add(movie);
					}
				}
			}
		} catch (IOException e) {
			LOGGER.error("retrieveMovie : ", e);
		} finally {
			if (fileInputStream != null) {
				try {
					fileInputStream.close();
				} catch (IOException e) {
					LOGGER.error("retrieveMovie : ", e);
				}
			}
			try {
				br.close();
			} catch (IOException e) {
				LOGGER.error("retrieveMovie : ", e);
			}

		}
		return movies;
	}

}
