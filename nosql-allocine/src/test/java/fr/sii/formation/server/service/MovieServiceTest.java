package fr.sii.formation.server.service;

import fr.sii.nosql.server.service.MovieServiceException;

public interface MovieServiceTest extends SimpleMovieServiceTest {

	void findByActor() throws MovieServiceException;

	void allTests4Times() throws InterruptedException, MovieServiceException;

}