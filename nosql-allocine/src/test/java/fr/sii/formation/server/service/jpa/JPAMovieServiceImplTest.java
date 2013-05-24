package fr.sii.formation.server.service.jpa;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.repository.jpa.complex.JpaMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles({ "jpa" })
public class JPAMovieServiceImplTest {

	@Autowired
	@Qualifier("jpaMovieService")
	private MovieService movieService;

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileMovieRepository;

	@Autowired
	@Qualifier("jpaMovieRepository")
	JpaMovieRepository jpaRepository;

	@Autowired
	private AlloCineService alloCineService;

	@Before
	public void before() {
		movieService.setMovieRepository(jpaRepository);
	}

	@Test
	public void populateOne() throws MovieServiceException {
		// "Alien, le huitième passager" id
		long idMovie = 62;

		Movie movie = fileMovieRepository.findById(idMovie);

		Assert.assertEquals(idMovie, movie.getId());

		movieService.save(movie, false);
	}

	@Test
	@Ignore
	public void populate() throws MovieServiceException {

		String fileName = "D:\\Users\\Nicolas\\Documents\\My Dropbox\\vu.txt";
		List<Movie> movies = alloCineService.retrieveMovies(fileName);
		for (Movie movie : movies) {
			movieService.save(movie, true);
		}
	}

	@Test
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {

		Iterable<Movie> iterable = fileMovieRepository.all();

		long index = 0;
		long deb = System.currentTimeMillis();
		for (Movie movie : iterable) {
			movieService.save(movie, false);
			index++;
		}

		long end = System.currentTimeMillis();

		System.out.println("populate " + index + " movies in " + (end - deb) + " ms");
	}

}
