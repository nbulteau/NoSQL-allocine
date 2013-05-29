package fr.sii.formation.server.service.hbase;

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
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("hbase")
public class SimpleHBaseMovieServiceTest implements SimpleMovieServiceTest {

	@Autowired
	@Qualifier("simpleHBaseMovieRepository")
	MovieRepository movieRepository;

	@Autowired
	FileMovieRepository fileMovieRepository;

	@Autowired
	MovieService movieService;

	private SimpleMovieServiceTest movieServiceTest;

	@PostConstruct
	public void before() {
		movieService.setMovieRepository(movieRepository);
		movieServiceTest = new SimpleMovieServiceTestImpl(fileMovieRepository, movieService);
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
	public void findMovieById() throws MovieServiceException {
		movieServiceTest.findMovieById();
	}

	@Test
	@Override
	public void findMoviesByTitle() throws MovieServiceException {
		movieServiceTest.findMoviesByTitle();
	}

	@Test
	@Override
	public void findMoviesByTitleLike() throws MovieServiceException {
		movieServiceTest.findMoviesByTitleLike();
	}

}
