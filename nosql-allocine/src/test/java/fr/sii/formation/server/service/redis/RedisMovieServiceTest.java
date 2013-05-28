package fr.sii.formation.server.service.redis;

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

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("redis")
public class RedisMovieServiceTest {

	@Autowired
	MovieService movieService;

	@Autowired
	FileMovieRepository fileMovieRepository;

	@Autowired
	@Qualifier("redisMovieRepository")
	MovieRepository redisMovieRepository;

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

	@Test
	public void findByIdTest() throws MovieServiceException {
		Long[] ids = { 5091l, 203l, 42729l, 50072l, 99876l, 139957l, 185220l, 197774l, 205895l, 221092l };
		long deb = System.currentTimeMillis();
		for (Long id : ids) {
			movieService.findById(id);
		}
		long end = System.currentTimeMillis();

		System.out.println("findById movies in " + (end - deb) / 10 + " ms");
	}

	@Test
	public void findByActor() throws MovieServiceException {
		String[] names = { "Bérénice Bejo", "Orlando Bloom", "Emmanuelle Seigner", "Joaquin Phoenix", "Tom Hanks", "Liam Neeson", "Brad Pitt", "Al Pacino",
				"Morgan Freeman", "Kevin Spacey", "Gary Oldman", "Emma Watson", "Harrison Ford", "Johnny Depp", "Winona Ryder" };
		long deb = System.currentTimeMillis();
		List<Movie> movies = null;
		int sum = 0;
		for (String name : names) {
			movies = movieService.findByActor(name);
			sum += movies.size();
		}
		long end = System.currentTimeMillis();
		Assert.assertEquals(743, sum);

		System.out.println("findByActor movies in " + (end - deb) / names.length + " ms");
	}

	@Test
	public void allTests4Times() throws InterruptedException, MovieServiceException {
		for (int i = 0; i < 5; i++) {
			populateFromFileRepository();
			findByIdTest();
			findByActor();
		}
	}

}
