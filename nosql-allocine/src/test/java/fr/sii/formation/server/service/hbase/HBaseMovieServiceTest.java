package fr.sii.formation.server.service.hbase;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.server.service.MovieServiceTest;
import fr.sii.formation.server.service.MovieServiceTestImpl;
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("hbase")
public class HBaseMovieServiceTest implements MovieServiceTest {

	@Autowired
	FileMovieRepository fileMovieRepository;

	@Autowired
	MovieService movieService;

	@Autowired
	@Qualifier("hbaseMovieRepository")
	MovieRepository movieRepository;

	private MovieServiceTest movieServiceTest;

	@BeforeClass
	public void before() {
		movieService.setMovieRepository(movieRepository);
		movieServiceTest = new MovieServiceTestImpl(
				fileMovieRepository, movieService);
	}

	@Test
	@Override
	public void insertMovie() throws MovieServiceException {
		movieServiceTest.insertMovie();
	}

	@Test
	@Override
	public void populateFromFileRepository() throws InterruptedException,
			MovieServiceException {
		movieServiceTest.populateFromFileRepository();
	}

	@Test
	@Override
	public void findByIdTest() throws MovieServiceException {
		movieServiceTest.findByIdTest();
	}

	@Override
	public void findByActor() throws MovieServiceException {
		movieServiceTest.findByActor();		
	}

	@Override
	public void allTests4Times() throws InterruptedException,
			MovieServiceException {
		movieServiceTest.allTests4Times();		
	}
}
