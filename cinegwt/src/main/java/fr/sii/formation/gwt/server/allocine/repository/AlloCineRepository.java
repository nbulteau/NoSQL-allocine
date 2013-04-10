package fr.sii.formation.gwt.server.allocine.repository;

import java.util.List;

import fr.sii.formation.gwt.server.allocine.buisiness.Movie;

public interface AlloCineRepository {

	Movie retrieveMovie(long idMovie) throws RetrieveException;

	List<Movie> retrieveMovielist() throws RetrieveException;

}