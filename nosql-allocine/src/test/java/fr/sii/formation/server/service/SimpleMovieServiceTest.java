package fr.sii.formation.server.service;

import fr.sii.nosql.server.service.MovieServiceException;

public interface SimpleMovieServiceTest {

	void insertMovie() throws MovieServiceException;

	void populateFromFileRepository() throws InterruptedException, MovieServiceException;

	void findMovieById() throws MovieServiceException;

	void findMoviesByTitle() throws MovieServiceException;

	void findMoviesByTitleLike() throws MovieServiceException;

}