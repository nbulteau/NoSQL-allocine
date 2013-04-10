package fr.sii.nosql.server.allocine.repository;

import java.util.List;

import fr.sii.nosql.server.allocine.buisiness.AlloCineMovie;

public interface AlloCineRepository {

	AlloCineMovie retrieveMovie(long idMovie) throws RetrieveException;

	List<AlloCineMovie> retrieveMovielist() throws RetrieveException;

}