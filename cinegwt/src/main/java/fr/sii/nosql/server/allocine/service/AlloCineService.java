package fr.sii.nosql.server.allocine.service;

import java.util.List;

import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

public interface AlloCineService {

	Movie retrieveMovie(long idMovie);

	List<Movie> retrieveMovies(String inputFileName) throws MovieServiceException;

	List<Movie> retrieveMovieList();

}