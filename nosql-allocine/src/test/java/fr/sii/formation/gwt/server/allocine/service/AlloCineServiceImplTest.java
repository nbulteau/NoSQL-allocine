package fr.sii.formation.gwt.server.allocine.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class AlloCineServiceImplTest {

	@Autowired
	AlloCineService alloCineService;

	@Test
	@Ignore
	public void testRetrieveAvatar() {
		// Avatar id
		long idMovie = 61282;

		Movie movie = alloCineService.retrieveMovie(idMovie);
		Assert.assertEquals(idMovie, movie.getId());
	}

	@Test
	@Ignore
	public void testRetrieveCondorman() {
		// Condorman id
		long idMovie = 61281;

		Movie movie = alloCineService.retrieveMovie(idMovie);
		Assert.assertEquals(idMovie, movie.getId());
	}

	@Test
	public void testRetrieveOblivion() {
		// Oblivion id
		long idMovie = 27405;

		Movie movie = alloCineService.retrieveMovie(idMovie);
		Assert.assertEquals(idMovie, movie.getId());
	}

	@Test
	@Ignore
	public void populate() throws MovieServiceException {

		String fileName = "D:\\Users\\Nicolas\\Documents\\My Dropbox\\vu.txt";
		List<Movie> movies = alloCineService.retrieveMovies(fileName);
		System.out.println(movies.size());
	}

	@Test
	@Ignore
	public void retrieveMovieList() {
		List<Movie> movies = alloCineService.retrieveMovieList();
		System.out.println(movies.size());
	}
}
