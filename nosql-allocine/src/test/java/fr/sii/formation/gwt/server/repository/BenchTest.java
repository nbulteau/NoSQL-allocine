package fr.sii.formation.gwt.server.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
// @ActiveProfiles({ "" })
public class BenchTest {

	private static final String ALIEN_LE_HUITIEME = "Alien, le huitième";

	private static final int TOTAL_MOVIE_COUNT = 2150;

	private static final String LA_GUERRE_DES_BOUTONS = "La Guerre des boutons";

	private static final long MERYL_STEEP_ID = 9;
	private static final String MERYL_STREEP = "Meryl Streep";

	private static final long ALIEN = 62;

	@Autowired
	MovieRepository movieRepository;

	@Test
	public void findMovieById() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Movie movie = movieRepository.findById(ALIEN);
		Assert.assertNotNull(movie);

		long end = System.currentTimeMillis();
		System.out.println("findById : " + (end - deb));
	}

	@Test
	public void findMoviesByTitle() {
		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Movie> movies = movieRepository.findByTitle(LA_GUERRE_DES_BOUTONS);
		Assert.assertEquals(2, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitle : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleLike() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> movie = movieRepository.findByTitleLike(ALIEN_LE_HUITIEME);
		Assert.assertEquals(1, movie.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleLike : " + (end - deb));
	}

	@Test
	public void findMoviesByActor() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = movieRepository.findByActor(MERYL_STEEP_ID);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActor : " + (end - deb));
	}

	@Test
	public void findMoviesByActorName() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = movieRepository.findByActor(MERYL_STREEP);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorName : " + (end - deb));
	}

	@Test
	public void countMovies() {
		long deb = System.currentTimeMillis();

		long count = movieRepository.count();
		Assert.assertEquals(TOTAL_MOVIE_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("count : " + (end - deb));
	}

}
