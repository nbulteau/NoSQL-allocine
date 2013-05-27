package fr.sii.formation.server.service.redis;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.repository.redis.RedisMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("redis")
public class RedisMovieServiceTest {

	@Autowired
	@Qualifier("nosqlMovieService")
	MovieService movieService;

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileMovieRepository;

	@Autowired
	@Qualifier("redisMovieRepository")
	RedisMovieRepository redisMovieRepository;

	public RedisMovieServiceTest() {
		super();
	}

	@Before
	public void before() {
		movieService.setMovieRepository(redisMovieRepository);
	}

	@Test
	@Ignore
	public void insertMovie() throws MovieServiceException {
		Movie movie = fileMovieRepository.findById(62l);
		movieService.save(movie, true);
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
