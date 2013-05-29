package fr.sii.formation.server.service.mongodb;

import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.server.service.MovieServiceTest;
import fr.sii.formation.server.service.MovieServiceTestImpl;
import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("mongodb")
public class MongoDBMovieServiceTest implements MovieServiceTest {

	private static final String AVATAR = "AlloCineMovie [id=61282, title=Avatar, originaltitle=Avatar, releasedate=Wed Dec 16 00:00:00 CET 2009, duration=9720, directors=[AlloCinePerson [id=1066, name=James Cameron]], actors=[AlloCineCastMember [role=Jake Sully, person=AlloCinePerson [id=41339, name=Sam Worthington]], AlloCineCastMember [role=Neytiri, person=AlloCinePerson [id=34515, name=Zoe Saldana]], AlloCineCastMember [role=Grace Augustine, person=AlloCinePerson [id=259, name=Sigourney Weaver]], AlloCineCastMember [role=le colonel Miles Quaritch, person=AlloCinePerson [id=6407, name=Stephen Lang]], AlloCineCastMember [role=Trudy Chacon, person=AlloCinePerson [id=60617, name=Michelle Rodriguez]], AlloCineCastMember [role=Parker Selfridge, person=AlloCinePerson [id=28985, name=Giovanni Ribisi]], AlloCineCastMember [role=Norm Spellman, person=AlloCinePerson [id=130952, name=Joel Moore]], AlloCineCastMember [role=Eytukan, person=AlloCinePerson [id=18050, name=Wes Studi]], AlloCineCastMember [role=Moat, person=AlloCinePerson [id=175724, name=CCH Pounder]], AlloCineCastMember [role=Tsu'Tey, person=AlloCinePerson [id=117875, name=Laz Alonso]], AlloCineCastMember [role=Dr. Max Patel, person=AlloCinePerson [id=218328, name=Dileep Rao]], AlloCineCastMember [role=Akwey, person=AlloCinePerson [id=77973, name=Peter Mensah]], AlloCineCastMember [role=le caporal Lyne Wainfleet, person=AlloCinePerson [id=61597, name=Matt Gerald]], AlloCineCastMember [role=le chef d'équipage du Venture Star, person=AlloCinePerson [id=104999, name=Scott Lawrence]]], kinds=[Science_fiction, Aventure], synopsis=Malgré sa paralysie, Jake Sully, un ancien marine immobilisé dans un fauteuil roulant, est resté un combattant au plus profond de son être. Il est recruté pour se rendre à des années-lumière de la Terre, sur Pandora, où de puissants groupes industriels exploitent un minerai rarissime destiné à résoudre la crise énergétique sur Terre. Parce que l'atmosphère de Pandora est toxique pour les humains, ceux-ci ont créé le Programme Avatar, qui permet à des \" pilotes \" humains de lier leur esprit à un avatar, un corps biologique commandé à distance, capable de survivre dans cette atmosphère létale. Ces avatars sont des hybrides créés génétiquement en croisant l'ADN humain avec celui des Na'vi, les autochtones de Pandora.Sous sa forme d'avatar, Jake peut de nouveau marcher. On lui confie une mission d'infiltration auprès des Na'vi, devenus un obstacle trop conséquent à l'exploitation du précieux minerai. Mais tout va changer lorsque Neytiri, une très belle Na'vi, sauve la vie de Jake...]";

	@Autowired
	@Qualifier("mongoDBMovieRepository")
	MongoDBMovieRepository movieRepository;

	@Autowired
	MovieService movieService;

	@Autowired
	FileMovieRepository fileMovieRepository;

	private MovieServiceTest movieServiceTest;

	@PostConstruct
	public void before() {
		movieService.setMovieRepository(movieRepository);
		movieServiceTest = new MovieServiceTestImpl(fileMovieRepository, movieService);
	}

	@Test
	@Override
	public void insertMovie() throws MovieServiceException {
		movieServiceTest.insertMovie();
	}

	@Test
	@Override
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {
		movieServiceTest.populateFromFileRepository();
	}

	@Test
	@Override
	public void findMovieById() throws MovieServiceException {
		movieServiceTest.findMovieById();
	}

	@Test
	@Override
	public void findMoviesByActorName() throws MovieServiceException {
		movieServiceTest.findMoviesByActorName();
	}

	@Test
	@Override
	public void findMoviesByKind() throws MovieServiceException {
		movieServiceTest.findMoviesByKind();
	}

	@Test
	@Override
	public void findMoviesByTitle() throws MovieServiceException {
		movieServiceTest.findMoviesByTitle();
	}

	@Test
	@Override
	public void findMoviesByTitleLike() throws MovieServiceException {
		movieServiceTest.findMoviesByTitleLike();
	}

	@Test
	@Override
	public void allTests4Times() throws InterruptedException, MovieServiceException {
		movieServiceTest.allTests4Times();
	}

	@Test
	public void retrieveAndSaveTest() throws MovieServiceException {
		long idMovie = 61282;

		Movie movie = movieService.retrieveAndSave(idMovie, false);
		Assert.assertEquals(idMovie, movie.getId());
		Assert.assertEquals(AVATAR, movie.toString());
	}

	@Test
	@Ignore
	public void testFindByActorId() throws MovieServiceException {
		// 'Meryl Streep'
		long id = 9;
		List<Movie> films = null;

		long deb = System.currentTimeMillis();

		films = movieService.findByActor(id);
		for (Movie movie : films) {
			System.out.println(movie);
		}
		Assert.assertEquals(7, films.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActorId : " + (end - deb));
	}

	@Test
	@Ignore
	public void findByDirectorName() throws MovieServiceException {
		long deb = System.currentTimeMillis();

		// 'Meryl Streep'
		List<Movie> films1 = movieService.findByDirector("Meryl Streep");
		for (Movie movie : films1) {
			System.out.println(movie);
		}
		Assert.assertEquals(0, films1.size());

		// 'Robert De Niro'
		List<Movie> films2 = movieService.findByDirector("Robert De Niro");
		for (Movie movie : films2) {
			System.out.println(movie);
		}
		Assert.assertEquals(1, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("findByDirectorName : " + (end - deb));
	}

}
