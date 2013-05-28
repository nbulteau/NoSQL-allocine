package fr.sii.formation.server.service;

import fr.sii.nosql.server.service.MovieServiceException;

public interface SimpleMovieServiceTest {

	public abstract void insertMovie() throws MovieServiceException;

	public abstract void populateFromFileRepository()
			throws InterruptedException, MovieServiceException;

	public abstract void findByIdTest() throws MovieServiceException;

}