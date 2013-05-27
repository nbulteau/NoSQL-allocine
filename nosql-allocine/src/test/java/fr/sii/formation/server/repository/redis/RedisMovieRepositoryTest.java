package fr.sii.formation.server.repository.redis;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import fr.sii.nosql.server.repository.redis.RedisMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

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
	FileMovieRepository fileRepository;

	@Autowired
	RedisMovieRepository redisMovieRepository;

	@Autowired
	@Qualifier("nosqlMovieService")
	private MovieService movieService;

	@Autowired
	private AlloCineService alloCineService;

	@Before
	public void before() {
		movieService.setMovieRepository(redisMovieRepository);
	}

	@Test
	public void populateOne() throws MovieServiceException {
		// "Alien, le huitième passager" id
		long idMovie = 62;

		Movie movie = fileRepository.findById(idMovie);

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
	@Ignore
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {

		Iterable<Movie> iterable = fileRepository.all();

		long index = 0;
		long deb = System.currentTimeMillis();
		for (Movie movie : iterable) {
			movieService.save(movie, false);
			index++;
		}

		long end = System.currentTimeMillis();

		System.out.println("populate " + index + " movies in " + (end - deb) + " ms");
	}

	@Ignore
	@Test
	public void countMoviesRedis() {
		long deb = System.currentTimeMillis();

		long count = redisMovieRepository.count();
		Assert.assertEquals(TOTAL_MOVIE_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMoviesRedis : " + (end - deb));
	}

	@Ignore
	@Test
	public void countMoviesByKindRedis() {
		long deb = System.currentTimeMillis();

		long count = redisMovieRepository.countMoviesWithQuery(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMoviesByKindRedis : " + (end - deb));
	}

	@Ignore
	@Test
	public void countKindsRedis() {
		long deb = System.currentTimeMillis();

		long count = redisMovieRepository.countKinds();
		Assert.assertEquals(TOTAL_KIND_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countKindsRedis : " + (end - deb));
	}

	@Ignore
	@Test
	public void findMoviesByActorRedis() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByActor(MERYL_STEEP_ID);
		Assert.assertEquals(59, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorRedis : " + (end - deb));
	}

	@Ignore
	@Test
	public void findMoviesByActorNameRedis() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByActor(MERYL_STREEP);
		Assert.assertEquals(59, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findMoviesByActorNameRedis : " + (end - deb));
	}

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

		CastMember castMember = new CastMember();
		castMember.setRole("role");
		Person person1 = new Person();
		person1.setId(100000000000l);
		person1.setName("nom");
		castMember.setPerson(person1);
		Set<CastMember> castMembers = new HashSet<>();
		castMembers.add(castMember);
		movie.setCastMembers(castMembers);

		Person person2 = new Person();
		person2.setId(100000000001l);
		person2.setName("nom");
		Set<Person> directors = new HashSet<>();
		directors.add(person2);
		movie.setDirectors(directors);

		Set<Kind> kinds = new HashSet<>();
		kinds.add(Kind.Action);
		movie.setKinds(kinds);

		long deb = System.currentTimeMillis();

		redisMovieRepository.save(movie);

		long end = System.currentTimeMillis();
		System.out.println("saveRedis : " + (end - deb));

		Assert.assertNotNull(redisMovieRepository.findById(movie.getId()));
		redisMovieRepository.delete(movie);
		Assert.assertNull(redisMovieRepository.findById(movie.getId()));
	}

}
