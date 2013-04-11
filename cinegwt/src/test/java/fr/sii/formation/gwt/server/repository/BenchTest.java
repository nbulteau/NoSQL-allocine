package fr.sii.formation.gwt.server.repository;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.repository.hbase.HBaseMovieRepository;
import fr.sii.nosql.server.repository.jpa.JpaMovieRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieMapReduceRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieRepository;
import fr.sii.nosql.server.repository.redis.RedisMovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles({ "jpa", "mongodb", "redis", "hbase" })
public class BenchTest {

	private static final String ALIEN_LE_HUITIEME = "Alien, le huitième";

	// private static final int ACTION_MOVIES_COUNT = 300;
	private static final int ACTION_MOVIES_COUNT = 105472;

	// private static final int TOTAL_MOVIE_COUNT = 1881;
	private static final int TOTAL_MOVIE_COUNT = 2150;

	private static final String LA_GUERRE_DES_BOUTONS = "La Guerre des boutons";

	private static final long MERYL_STEEP_ID = 9;
	private static final String MERYL_STREEP = "Meryl Streep";

	private static final long ALIEN = 62;

	@Autowired
	RedisMovieRepository redisMovieRepository;

	@Autowired
	HBaseMovieRepository hbaseMovieRepository;

	@Autowired
	MongoDBMovieMapReduceRepository mapReduceRepository;

	@Autowired
	MongoDBMovieRepository mongoDBMovieRepository;

	@Autowired
	// (required = false)
	JpaMovieRepository jpaMovieRepository;

	@Test
	public void findMovieByIdMongoDB() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Movie movie = mongoDBMovieRepository.findOne(ALIEN);
		Assert.assertNotNull(movie);

		long end = System.currentTimeMillis();
		System.out.println("findByIdMongoDB : " + (end - deb));
	}

	@Test
	public void findMovieByIdRedis() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Movie movie = redisMovieRepository.findOne(ALIEN);
		Assert.assertNotNull(movie);

		long end = System.currentTimeMillis();
		System.out.println("findByIdRedis : " + (end - deb));
	}

	@Test
	public void findMovieByIdHBase() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Movie movie = hbaseMovieRepository.findOne(ALIEN);
		Assert.assertNotNull(movie);

		long end = System.currentTimeMillis();
		System.out.println("findByIdHBase : " + (end - deb));
	}

	@Test
	public void findMovieByIdJPA() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Movie movie = jpaMovieRepository.findOne(ALIEN);
		Assert.assertNotNull(movie);

		long end = System.currentTimeMillis();
		System.out.println("findByIdJPA : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleMongoDB() {
		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Movie> movies = mongoDBMovieRepository.findByTitle(LA_GUERRE_DES_BOUTONS);
		Assert.assertEquals(2, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleMongoDB : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleRedis() {
		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Movie> movies = redisMovieRepository.findByTitle(LA_GUERRE_DES_BOUTONS);
		Assert.assertEquals(2, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleHBase() {
		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Movie> movies = hbaseMovieRepository.findByTitle(LA_GUERRE_DES_BOUTONS);
		Assert.assertEquals(2, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleHBase : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleJPA() {
		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Movie> movies = jpaMovieRepository.findByTitle(LA_GUERRE_DES_BOUTONS);
		Assert.assertEquals(2, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleJPA : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleLikeMongoDB() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> movie = mongoDBMovieRepository.findByTitleLike(ALIEN_LE_HUITIEME);
		Assert.assertEquals(1, movie.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleLikeMongoDB : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleLikeRedis() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> movie = redisMovieRepository.findByTitleLike(ALIEN_LE_HUITIEME);
		Assert.assertEquals(1, movie.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleLikeRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleLikeHBase() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> movie = hbaseMovieRepository.findByTitleLike(ALIEN_LE_HUITIEME);
		Assert.assertEquals(1, movie.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleLikeHBase : " + (end - deb));
	}

	@Test
	public void findMoviesByTitleLikeJPA() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> movies1 = jpaMovieRepository.findByTitleLike(ALIEN_LE_HUITIEME);
		Assert.assertEquals(1, movies1.size());

		long end = System.currentTimeMillis();
		System.out.println("findByTitleLikeJPA : " + (end - deb));
	}

	@Test
	public void findMoviesByActorMongoDBWithMapReduce() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = mapReduceRepository.findByActorMR(MERYL_STEEP_ID);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorMongoDBWithMapReduce : " + (end - deb));
	}

	@Test
	public void findMoviesByActorMongoDB() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = mongoDBMovieRepository.findByActor(MERYL_STEEP_ID);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorMongoDB : " + (end - deb));
	}

	@Test
	public void findMoviesByActorRedis() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByActor(MERYL_STEEP_ID);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByActorHBase() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = hbaseMovieRepository.findByActor(MERYL_STEEP_ID);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorHBase : " + (end - deb));
	}

	@Test
	public void findMoviesByActorJPA() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = jpaMovieRepository.findByActor(MERYL_STEEP_ID);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorJPA : " + (end - deb));
	}

	@Test
	public void findMoviesByActorNameMongoDB() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = mongoDBMovieRepository.findByActor(MERYL_STREEP);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorNameMongoDB : " + (end - deb));
	}

	@Test
	public void findMoviesByActorNameRedis() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByActor(MERYL_STREEP);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorNameRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByActorNameHBase() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = hbaseMovieRepository.findByActor(MERYL_STREEP);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorNameHBase : " + (end - deb));
	}

	@Test
	public void findMoviesByActorNameJPA() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = jpaMovieRepository.findByActor(MERYL_STREEP);
		Assert.assertEquals(7, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorNameJPA : " + (end - deb));
	}

	@Test
	public void countMoviesMongoDB() {
		long deb = System.currentTimeMillis();

		long count = mongoDBMovieRepository.count();
		Assert.assertEquals(TOTAL_MOVIE_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMongoDB : " + (end - deb));
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
	public void countMoviesHBase() {
		long deb = System.currentTimeMillis();

		long count = hbaseMovieRepository.count();
		Assert.assertEquals(TOTAL_MOVIE_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countHBase : " + (end - deb));
	}

	@Test
	public void countMoviesJPA() {
		long deb = System.currentTimeMillis();

		long count = jpaMovieRepository.count();
		Assert.assertEquals(TOTAL_MOVIE_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countJPA : " + (end - deb));
	}

	@Test
	public void countMoviesByKindMongoDBWithMapReduce() {
		long deb = System.currentTimeMillis();

		long count = mapReduceRepository.countWithQueryMR(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countByKindMongoDBWithMapReduce : " + (end - deb));
	}

	@Test
	public void countMoviesByKindMongoDB() {
		long deb = System.currentTimeMillis();

		long count = mapReduceRepository.countWithQuery(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countByKindMongoDB : " + (end - deb));
	}

	@Test
	public void countMoviesByKindRedis() {
		long deb = System.currentTimeMillis();

		long count = redisMovieRepository.countMoviesWithQuery(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMoviesByKindRedis : " + (end - deb));
	}

	@Test
	public void countMoviesByKindJPA() {
		long deb = System.currentTimeMillis();

		long count = jpaMovieRepository.countByKind(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countByKindJPA : " + (end - deb));
	}

	@Test
	public void findMoviesByKindMongoDBWithMapReduce() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = mapReduceRepository.findByKindMR(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindMongoDBWithMapReduce : " + (end - deb));
	}

	@Test
	public void findMoviesByKindMongoDB() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = mapReduceRepository.findByKind(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindMongoDB : " + (end - deb));
	}

	@Test
	public void findMoviesByKindRedis() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByKind(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByKindHBase() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = hbaseMovieRepository.findByKind(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindHBase : " + (end - deb));
	}

	@Test
	public void findMoviesByKindJPA() {
		long deb = System.currentTimeMillis();

		List<Movie> movies = jpaMovieRepository.findByKind(Kind.Action);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindJPA : " + (end - deb));
	}
}
