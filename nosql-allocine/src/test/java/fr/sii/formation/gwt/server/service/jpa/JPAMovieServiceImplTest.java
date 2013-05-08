package fr.sii.formation.gwt.server.service.jpa;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
// @ActiveProfiles({ "jpa", "mongodb" })
@ActiveProfiles({ "jpa" })
public class JPAMovieServiceImplTest {

	@Autowired
	@Qualifier("jpaMovieService")
	private MovieService filmService;

	@Test
	@Ignore
	public void testFindById() {
		// "Alien, le huitième passager" id
		long idMovie = 62;

		Movie movie = filmService.findById(idMovie);

		Assert.assertEquals(idMovie, movie.getId());
	}

	@Autowired
	private AlloCineService alloCineService;

	@Test
	public void populate() throws MovieServiceException {

		String fileName = "D:\\Users\\Nicolas\\Documents\\My Dropbox\\vu.txt";
		List<Movie> movies = alloCineService.retrieveMovies(fileName);
		for (Movie movie : movies) {
			filmService.save(movie);
		}
	}
}
