package fr.sii.formation.gwt.server.allocine.service;

import java.util.List;

import fr.sii.formation.gwt.server.service.MovieServiceException;
import fr.sii.formation.gwt.shared.buisiness.Movie;

public interface AlloCineService {

	Movie retrieveMovie(long idMovie);

	List<Movie> retrieveMovies(String inputFileName) throws MovieServiceException;

	List<Movie> retrieveMovieList();

}