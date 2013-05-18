package fr.sii.formation.gwt.server.repository.hbase;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("hbase")
public class HBaseMovieRepositoryTest {

	@Autowired
	MovieRepository movieRepository;

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

		movieRepository.save(movie);

		long end = System.currentTimeMillis();
		System.out.println("saveHBase : " + (end - deb));

		Assert.assertNotNull(movieRepository.findById(movie.getId()));
		movieRepository.delete(movie);
		Assert.assertNull(movieRepository.findById(movie.getId()));
	}

	@Test
	public void countMovies() throws IOException, MovieServiceException {
		movieRepository.count();
	}
}
