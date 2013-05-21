package fr.sii.formation.server.service.hbase;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

//@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("hbase")
public class HBaseMovieServiceTest {

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileMovieRepository;

	@Autowired
	@Qualifier("hbaseMovieRepository")
	MovieRepository movieRepository;

	@Autowired
	MovieService movieService;

	
	@Before
	public void before() {
		movieService.setMovieRepository(movieRepository);
	}
	
	@Test
	public void insertMovie() throws MovieServiceException {
		Movie movie = fileMovieRepository.findById(62l);
		movieService.save(movie, true);
	}

	@Test
	@Ignore
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {

		Iterable<Movie> iterable = fileMovieRepository.all();

		long index = 0;
		long deb = System.currentTimeMillis();
		for (Movie movie : iterable) {
			movieService.save(movie, true);
			index++;
		}

		long end = System.currentTimeMillis();

		System.out.println("populate " + index + " movies in " + (end - deb) + " ms");
	}
}
