package fr.sii.formation.server.repository.mongodb;

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

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
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
	MovieRepository movieRepository;

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileRepository;

	@Ignore
	@Test
	public void testFindByActorId() {
		// 'Meryl Streep'
		long id = 9;
		List<Movie> films = null;

		long deb = System.currentTimeMillis();

		films = movieRepository.findByActor(id);
		Assert.assertEquals(59, films.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorId : " + (end - deb));
	}

	@Ignore
	@Test
	public void findByDirectorName() {
		long deb = System.currentTimeMillis();

		// 'Meryl Streep'
		List<Movie> films1 = movieRepository.findByDirector("Meryl Streep");
		Assert.assertEquals(0, films1.size());

		// 'Robert De Niro'
		List<Movie> films2 = movieRepository.findByDirector("Robert De Niro");
		Assert.assertEquals(4, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("findByDirectorName : " + (end - deb));
	}

	@Ignore
	@Test
	public void countMoviesMongoDBWithoutMapReduce() {
		long deb = System.currentTimeMillis();

		long count = movieRepository.count();
		Assert.assertEquals(MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countMoviesMongoDBWithoutMapReduce : " + (end - deb));
	}

//	@Test
//	public void averageDurationByKindMongoDBWithMapReduce() {
//		long deb = System.currentTimeMillis();
//
//		int duration = mapReduceRepository.averageDurationWithQueryMR(Kind.Action);
//		Assert.assertEquals(AVERAGE_ACTION_MOVIES_DURATION, duration);
//
//		long end = System.currentTimeMillis();
//		System.out.println("averageDurationByKindMongoDBWithMapReduce : " + (end - deb));
//	}
//
//	@Test
//	public void averageDurationMongoDBWithMapReduce() {
//		long deb = System.currentTimeMillis();
//
//		int duration = mapReduceRepository.averageDurationWithQueryMR(null);
//		Assert.assertEquals(AVERAGE_MOVIES_DURATION, duration);
//
//		long end = System.currentTimeMillis();
//		System.out.println("averageDurationMongoDBWithMapReduce : " + (end - deb));
//	}
//
//	@Test
//	public void averageDurationMongoDB() {
//		long deb = System.currentTimeMillis();
//
//		int duration = mapReduceRepository.averageDuration(null);
//		Assert.assertEquals(AVERAGE_MOVIES_DURATION, duration);
//
//		long end = System.currentTimeMillis();
//		System.out.println("averageDurationByKindMongoDBWithMapReduce : " + (end - deb));
//	}
//
//	@Test
//	public void countMoviesByKindMongoDB() {
//		long deb = System.currentTimeMillis();
//
//		long count = mapReduceRepository.countWithQuery(Kind.Action);
//		Assert.assertEquals(ACTION_MOVIES_COUNT, count);
//
//		long end = System.currentTimeMillis();
//		System.out.println("countByKindMongoDB : " + (end - deb));
//	}

}
