package fr.sii.nosql.server.allocine.repository;

import java.util.List;

import fr.sii.nosql.server.allocine.buisiness.Movie;

public interface AlloCineRepository {

	Movie retrieveMovie(long idMovie) throws RetrieveException;

	List<Movie> retrieveMovielist() throws RetrieveException;

}