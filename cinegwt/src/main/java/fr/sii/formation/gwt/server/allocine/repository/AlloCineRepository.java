package fr.sii.formation.gwt.server.allocine.repository;

import fr.sii.formation.gwt.server.allocine.buisiness.AlloCineMovie;

public interface AlloCineRepository
{

	public abstract AlloCineMovie retrieveMovie(long idMovie) throws RetrieveException;

}