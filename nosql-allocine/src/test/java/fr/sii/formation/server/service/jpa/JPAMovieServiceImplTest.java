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

import fr.sii.formation.server.service.MovieServiceTest;
import fr.sii.formation.server.service.MovieServiceTestImpl;
import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.repository.jpa.optimised.JpaMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles({ "jpa" })
public class JPAMovieServiceImplTest implements MovieServiceTest {

	@Autowired
	@Qualifier("jpaMovieRepository")
	private JpaMovieRepository movieRepository;

	@Autowired
	private FileMovieRepository fileMovieRepository;

	@Autowired
	private AlloCineService alloCineService;

	@Autowired
	private MovieService movieService;

	private MovieServiceTest movieServiceTest;

	@PostConstruct
	public void before() {
		movieService.setMovieRepository(movieRepository);
		movieServiceTest = new MovieServiceTestImpl(fileMovieRepository, movieService);
	}

	@Test
	@Override
	public void insertMovie() throws MovieServiceException {
		movieServiceTest.insertMovie();
	}

	@Test
	@Override
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {
		movieServiceTest.populateFromFileRepository();
	}

	@Test
	@Override
	public void findByIdTest() throws MovieServiceException {
		movieServiceTest.findByIdTest();
	}

	@Test
	@Override
	public void findByActor() throws MovieServiceException {
		movieServiceTest.findByActor();
	}

	@Test
	@Override
	public void findByKindTest() throws MovieServiceException {
		movieServiceTest.findByKindTest();
	}

	@Test
	@Override
	public void allTests4Times() throws InterruptedException, MovieServiceException {
		movieServiceTest.allTests4Times();
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
