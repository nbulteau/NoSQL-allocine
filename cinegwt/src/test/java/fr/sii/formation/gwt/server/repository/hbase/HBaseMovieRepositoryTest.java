package fr.sii.formation.gwt.server.repository.hbase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.service.MovieServiceException;
import fr.sii.formation.gwt.shared.buisiness.Actor;
import fr.sii.formation.gwt.shared.buisiness.Kind;
import fr.sii.formation.gwt.shared.buisiness.Movie;
import fr.sii.formation.gwt.shared.buisiness.Person;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("hbase")
public class HBaseMovieRepositoryTest {

	@Autowired
	HBaseMovieRepository movieRepository;

	// copy/paste from redis
	@Test
	@Ignore
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

		movieRepository.save(movie);

		long end = System.currentTimeMillis();
		System.out.println("saveHBase : " + (end - deb));

		Assert.assertNotNull(movieRepository.findOne(movie.getId()));
		movieRepository.delete(movie);
		Assert.assertNull(movieRepository.findOne(movie.getId()));
	}

	@Test
	@Ignore
	public void testSynopsisLength() {
		int max = -1;
		for (Movie m : movieRepository.findAll())
			max = Math.max(max, m.getSynopsis() == null ? 0 : m.getSynopsis().length());

		System.out.println("testSynopsisLength : " + max);
		Assert.assertTrue("Max SynopsisLength < 2000", max < 2000);
	}

	@Test
	public void countMovies() throws IOException, MovieServiceException {
		movieRepository.count();
	}
}
