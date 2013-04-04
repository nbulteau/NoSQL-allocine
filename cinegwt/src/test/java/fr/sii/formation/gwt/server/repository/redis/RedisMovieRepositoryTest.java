package fr.sii.formation.gwt.server.repository.redis;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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

import fr.sii.formation.gwt.server.repository.file.FileMovieRepository;
import fr.sii.formation.gwt.server.service.MovieServiceException;
import fr.sii.formation.gwt.shared.MovieFilter;
import fr.sii.formation.gwt.shared.buisiness.Actor;
import fr.sii.formation.gwt.shared.buisiness.Kind;
import fr.sii.formation.gwt.shared.buisiness.Movie;
import fr.sii.formation.gwt.shared.buisiness.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("redis")
public class RedisMovieRepositoryTest {

	private static final int ACTION_MOVIES_COUNT = 3577;
	private static final int TOTAL_MOVIE_COUNT = 105472;
	private static final long MERYL_STEEP_ID = 9;
	private static final int TOTAL_KIND_COUNT = 34;
	private static final String MERYL_STREEP = "Meryl Streep";

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileRepository;

	@Autowired
	RedisMovieRepository redisMovieRepository;

	@Test
	public void updateMoviesRedis() throws IOException, MovieServiceException {
		long deb = System.currentTimeMillis();

		long nbMovies = fileRepository.count();
		int index = 0;
		for (Movie movie : fileRepository.all()) {
			System.out.println("=> " + index++ + " " + (System.currentTimeMillis() - deb) / 1000 + " s : movie : " + movie.getTitle());
			redisMovieRepository.save(movie);
		}

		long end = System.currentTimeMillis();
		System.out.println("updateMoviesRedis : " + (end - deb) + " for " + nbMovies + " movies");
	}

	@Test
	public void countMoviesRedis() {
		long deb = System.currentTimeMillis();

		long count = redisMovieRepository.countMovies();
		Assert.assertEquals(TOTAL_MOVIE_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMoviesRedis : " + (end - deb));
	}

	@Test
	public void countMoviesByKindRedis() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		long count = redisMovieRepository.countMoviesWithQuery(movieFilter);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMoviesByKindRedis : " + (end - deb));
	}

	@Test
	public void countKindsRedis() {
		long deb = System.currentTimeMillis();

		long count = redisMovieRepository.countKinds();
		Assert.assertEquals(TOTAL_KIND_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countKindsRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByActorRedis() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByActor(MERYL_STEEP_ID);
		Assert.assertEquals(59, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByActorNameRedis() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByActor(MERYL_STREEP);
		Assert.assertEquals(59, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findMoviesByActorNameRedis : " + (end - deb));
	}

	@Ignore
	@Test
	public void testCRUD() {
		Movie movie = new Movie();
		movie.setId(100000000000l);
		movie.setTitle("titre");
		movie.setOriginaltitle("titreoriginal");
		movie.setReleasedate(new Date());
		movie.setDuration(1234);
		movie.setSynopsis("synopsis");
		movie.setViewed(false);

		Actor actor = new Actor();
		actor.setRole("role");
		Person person1 = new Person();
		person1.setId(100000000000l);
		person1.setName("nom");
		actor.setPerson(person1);
		List<Actor> actors = new ArrayList<Actor>();
		actors.add(actor);
		movie.setActors(actors);

		Person person2 = new Person();
		person2.setId(100000000001l);
		person2.setName("nom");
		List<Person> directors = new ArrayList<Person>();
		directors.add(person2);
		movie.setDirectors(directors);

		List<Kind> kinds = new ArrayList<Kind>();
		kinds.add(Kind.Action);
		movie.setKinds(kinds);

		long deb = System.currentTimeMillis();

		redisMovieRepository.save(movie);

		long end = System.currentTimeMillis();
		System.out.println("saveRedis : " + (end - deb));

		Assert.assertNotNull(redisMovieRepository.findOne(movie.getId()));
		redisMovieRepository.delete(movie);
		Assert.assertNull(redisMovieRepository.findOne(movie.getId()));
	}

}
