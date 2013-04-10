package fr.sii.formation.gwt.server.repository.mongodb;

import java.io.IOException;
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

import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieMapReduceRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieRepository;
import fr.sii.nosql.server.service.MovieFilter;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("mongodb")
public class MongodbMovieRepositoryTest {

	private static final int ACTION_MOVIES_COUNT = 3577;

	private static final int AVERAGE_ACTION_MOVIES_DURATION = 6117;

	private static final int AVERAGE_MOVIES_DURATION = 5192;

	private static final int MOVIES_COUNT = 105472;

	@Autowired
	MongoDBMovieRepository mongoDBMovieRepository;

	@Autowired
	MongoDBMovieMapReduceRepository mapReduceRepository;

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileRepository;

	@Ignore
	@Test
	public void updateMoviesRedis() throws IOException, MovieServiceException {
		long deb = System.currentTimeMillis();

		long nbMovies = fileRepository.count();
		int index = 0;
		for (Movie movie : fileRepository.all()) {
			System.out.println("=> " + index++ + " " + (System.currentTimeMillis() - deb) / 1000 + " s : movie : " + movie.getTitle());
			mongoDBMovieRepository.save(movie);
		}

		long end = System.currentTimeMillis();
		System.out.println("updateMoviesRedis : " + (end - deb) + " for " + nbMovies + " movies");
	}

	@Test
	public void testFindById() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Movie film1 = mongoDBMovieRepository.findOne(62l);
		Assert.assertNotNull(film1);

		// 'La Guerre des boutons'
		Movie film2 = mongoDBMovieRepository.findOne(188649l);
		Assert.assertNotNull(film2);

		long end = System.currentTimeMillis();
		System.out.println("testFindById : " + (end - deb));
	}

	@Test
	public void testFindByTitle() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> films1 = mongoDBMovieRepository.findByTitle("Alien, le huitième passager");
		Assert.assertEquals(1, films1.size());

		// 'La Guerre des boutons'
		List<Movie> films2 = mongoDBMovieRepository.findByTitle("La Guerre des boutons");
		Assert.assertEquals(2, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherMoviesParLeTitre : " + (end - deb));
	}

	@Test
	public void testFindByTitleLike() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> films1 = mongoDBMovieRepository.findByTitleLike("Alien, le huitième");
		Assert.assertEquals(1, films1.size());

		// 'La Guerre des boutons'
		List<Movie> films2 = mongoDBMovieRepository.findByTitleLike("La Guerre des");
		Assert.assertEquals(43, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherMoviesDontLeTitreCommencePar : " + (end - deb));
	}

	@Ignore
	@Test
	public void testLoadAll() {
		long count = mongoDBMovieRepository.count();

		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Movie> films = mongoDBMovieRepository.findAll();
		Assert.assertEquals(count, films.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherMoviesDontLeTitreCommencePar : " + (end - deb));
	}

	@Test
	public void testFindByActorId() {
		// 'Meryl Streep'
		long id = 9;
		List<Movie> films = null;

		long deb = System.currentTimeMillis();

		films = mongoDBMovieRepository.findByActor(id);
		Assert.assertEquals(59, films.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorId : " + (end - deb));
	}

	@Test
	public void findByActorName() {
		long deb = System.currentTimeMillis();

		// 'Meryl Streep'
		String name = "Meryl Streep";
		List<Movie> films1 = mongoDBMovieRepository.findByActor(name);
		Assert.assertEquals(59, films1.size());

		// 'François Berland'
		name = "François Berland";
		List<Movie> films2 = mongoDBMovieRepository.findByActor(name);
		Assert.assertEquals(8, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActor : " + (end - deb));
	}

	@Test
	public void findByDirectorName() {
		long deb = System.currentTimeMillis();

		// 'Meryl Streep'
		List<Movie> films1 = mongoDBMovieRepository.findByDirector("Meryl Streep");
		Assert.assertEquals(0, films1.size());

		// 'Robert De Niro'
		List<Movie> films2 = mongoDBMovieRepository.findByDirector("Robert De Niro");
		Assert.assertEquals(4, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("findByDirectorName : " + (end - deb));
	}

	@Test
	public void countMoviesMongoDBWithoutMapReduce() {
		long deb = System.currentTimeMillis();

		long count = mongoDBMovieRepository.count();
		Assert.assertEquals(MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMoviesMongoDBWithoutMapReduce : " + (end - deb));
	}

	@Test
	public void averageDurationByKindMongoDBWithMapReduce() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		int duration = mapReduceRepository.averageDurationWithQueryMR(movieFilter);
		Assert.assertEquals(AVERAGE_ACTION_MOVIES_DURATION, duration);

		long end = System.currentTimeMillis();
		System.out.println("averageDurationByKindMongoDBWithMapReduce : " + (end - deb));
	}

	@Test
	public void averageDurationMongoDBWithMapReduce() {
		long deb = System.currentTimeMillis();

		int duration = mapReduceRepository.averageDurationWithQueryMR(null);
		Assert.assertEquals(AVERAGE_MOVIES_DURATION, duration);

		long end = System.currentTimeMillis();
		System.out.println("averageDurationMongoDBWithMapReduce : " + (end - deb));
	}

	@Test
	public void averageDurationMongoDB() {
		long deb = System.currentTimeMillis();

		int duration = mapReduceRepository.averageDuration(null);
		Assert.assertEquals(AVERAGE_MOVIES_DURATION, duration);

		long end = System.currentTimeMillis();
		System.out.println("averageDurationByKindMongoDBWithMapReduce : " + (end - deb));
	}

	@Test
	public void countMoviesByKindMongoDB() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		long count = mapReduceRepository.countWithQuery(movieFilter);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countByKindMongoDB : " + (end - deb));
	}

}
