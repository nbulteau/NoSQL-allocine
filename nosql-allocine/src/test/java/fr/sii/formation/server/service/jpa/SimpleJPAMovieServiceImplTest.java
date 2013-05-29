package fr.sii.formation.server.service.jpa;

import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.server.service.SimpleMovieServiceTest;
import fr.sii.formation.server.service.SimpleMovieServiceTestImpl;
import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles({ "jpa" })
public class SimpleJPAMovieServiceImplTest implements SimpleMovieServiceTest {

	@Autowired
	@Qualifier("simpleJpaMovieRepository")
	private MovieRepository movieRepository;

	@Autowired
	private FileMovieRepository fileMovieRepository;

	@Autowired
	private AlloCineService alloCineService;

	@Autowired
	private MovieService movieService;

	private SimpleMovieServiceTest simpleMovieServiceTest;

	@PostConstruct
	public void before() {
		movieService.setMovieRepository(movieRepository);
		simpleMovieServiceTest = new SimpleMovieServiceTestImpl(fileMovieRepository, movieService);
	}

	@Test
	@Override
	public void insertMovie() throws MovieServiceException {
		simpleMovieServiceTest.insertMovie();
	}

	@Test
	@Override
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {
		simpleMovieServiceTest.populateFromFileRepository();
	}

	@Test
	@Override
	public void findByIdTest() throws MovieServiceException {
		simpleMovieServiceTest.findByIdTest();
	}

	@Test
	public void populate() throws MovieServiceException {

		String fileName = "D:\\Users\\Nicolas\\Documents\\My Dropbox\\vu.txt";
		List<Movie> movies = alloCineService.retrieveMovies(fileName);
		for (Movie movie : movies) {
			movieService.save(movie, true);
		}
	}
}
