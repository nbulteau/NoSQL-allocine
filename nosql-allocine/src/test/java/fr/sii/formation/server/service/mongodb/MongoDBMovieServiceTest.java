package fr.sii.formation.server.service.mongodb;

import java.util.ArrayList;
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

import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("mongodb")
public class MongoDBMovieServiceTest {

	private static final String AVATAR = "AlloCineMovie [id=61282, title=Avatar, originaltitle=Avatar, releasedate=Wed Dec 16 00:00:00 CET 2009, duration=9720, directors=[AlloCinePerson [id=1066, name=James Cameron]], actors=[AlloCineCastMember [role=Jake Sully, person=AlloCinePerson [id=41339, name=Sam Worthington]], AlloCineCastMember [role=Neytiri, person=AlloCinePerson [id=34515, name=Zoe Saldana]], AlloCineCastMember [role=Grace Augustine, person=AlloCinePerson [id=259, name=Sigourney Weaver]], AlloCineCastMember [role=le colonel Miles Quaritch, person=AlloCinePerson [id=6407, name=Stephen Lang]], AlloCineCastMember [role=Trudy Chacon, person=AlloCinePerson [id=60617, name=Michelle Rodriguez]], AlloCineCastMember [role=Parker Selfridge, person=AlloCinePerson [id=28985, name=Giovanni Ribisi]], AlloCineCastMember [role=Norm Spellman, person=AlloCinePerson [id=130952, name=Joel Moore]], AlloCineCastMember [role=Eytukan, person=AlloCinePerson [id=18050, name=Wes Studi]], AlloCineCastMember [role=Moat, person=AlloCinePerson [id=175724, name=CCH Pounder]], AlloCineCastMember [role=Tsu'Tey, person=AlloCinePerson [id=117875, name=Laz Alonso]], AlloCineCastMember [role=Dr. Max Patel, person=AlloCinePerson [id=218328, name=Dileep Rao]], AlloCineCastMember [role=Akwey, person=AlloCinePerson [id=77973, name=Peter Mensah]], AlloCineCastMember [role=le caporal Lyne Wainfleet, person=AlloCinePerson [id=61597, name=Matt Gerald]], AlloCineCastMember [role=le chef d'équipage du Venture Star, person=AlloCinePerson [id=104999, name=Scott Lawrence]]], kinds=[Science_fiction, Aventure], synopsis=Malgré sa paralysie, Jake Sully, un ancien marine immobilisé dans un fauteuil roulant, est resté un combattant au plus profond de son être. Il est recruté pour se rendre à des années-lumière de la Terre, sur Pandora, où de puissants groupes industriels exploitent un minerai rarissime destiné à résoudre la crise énergétique sur Terre. Parce que l'atmosphère de Pandora est toxique pour les humains, ceux-ci ont créé le Programme Avatar, qui permet à des \" pilotes \" humains de lier leur esprit à un avatar, un corps biologique commandé à distance, capable de survivre dans cette atmosphère létale. Ces avatars sont des hybrides créés génétiquement en croisant l'ADN humain avec celui des Na'vi, les autochtones de Pandora.Sous sa forme d'avatar, Jake peut de nouveau marcher. On lui confie une mission d'infiltration auprès des Na'vi, devenus un obstacle trop conséquent à l'exploitation du précieux minerai. Mais tout va changer lorsque Neytiri, une très belle Na'vi, sauve la vie de Jake...]";

	@Autowired
	@Qualifier("movieService")
	MovieService movieService;

	@Autowired
	@Qualifier("fileMovieRepository")
	FileMovieRepository fileMovieRepository;

	@Autowired
	@Qualifier("fileMovieRepository")
	MongoDBMovieRepository mongoDBMovieRepository;

	public MongoDBMovieServiceTest() {
		super();
	}

	@Before
	public void before() {
		movieService.setMovieRepository(mongoDBMovieRepository);
	}

	@Test
	public void insertMovie() throws MovieServiceException {
		Movie movie = mongoDBMovieRepository.findById(62l);
		movieService.save(movie, true);
	}

	@Test
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {

		Iterable<Movie> iterable = fileMovieRepository.all();

		long index = 0;
		long deb = System.currentTimeMillis();
		for (Movie movie : iterable) {
			movieService.save(movie, true);
			index++;
		}

		long end = System.currentTimeMillis();

		System.out.println("populate " + index + " movies in " + (end - deb) + " ms");
	}

	@Test
	@Ignore
	public void saveMovie() throws MovieServiceException {
		String movieTitle = "movieTitle";

		Person person1 = new Person(1, "P1");
		Person person2 = new Person(2, "P2");
		Person person3 = new Person(2, "P3");

		Movie movie = new Movie(1, movieTitle, "");

		List<CastMember> castMembers = new ArrayList<CastMember>();
		CastMember actor1 = new CastMember(person1, "role1");
		castMembers.add(actor1);
		CastMember actor2 = new CastMember(person3, "role2");
		castMembers.add(actor2);
		movie.getCastMembers().addAll(castMembers);

		List<Person> directors = new ArrayList<Person>();
		directors.add(person2);
		movie.getDirectors().addAll(directors);

		List<Kind> kinds = new ArrayList<Kind>();
		kinds.add(Kind.Action);
		kinds.add(Kind.Arts_Martiaux);
		movie.getKinds().addAll(kinds);

		movieService.save(movie, false);
		movie = movieService.findById(movie.getId());

		// oracle
		Assert.assertNotNull(movie);
		Assert.assertEquals(movieTitle, movie.getTitle());
	}

	@Test
	@Ignore
	public void retrieveAndSaveTest() throws MovieServiceException {
		long idMovie = 61282;

		Movie movie = movieService.retrieveAndSave(idMovie, false);
		Assert.assertEquals(idMovie, movie.getId());
		Assert.assertEquals(AVATAR, movie.toString());
	}

	@Test
	@Ignore
	public void testFindById() throws MovieServiceException {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Movie film1 = movieService.findById(62l);
		System.out.println(film1);
		Assert.assertNotNull(film1);

		// 'La Guerre des boutons'
		Movie film2 = movieService.findById(188649l);
		System.out.println(film2);
		Assert.assertNotNull(film2);

		long end = System.currentTimeMillis();
		System.out.println("testFindById : " + (end - deb));
	}

	@Test
	@Ignore
	public void testFindByTitle() throws MovieServiceException {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> films1 = movieService.findByTitle("Alien, le huitième passager");
		for (Movie movie : films1) {
			System.out.println(movie);
		}
		Assert.assertEquals(1, films1.size());

		// 'La Guerre des boutons'
		List<Movie> films2 = movieService.findByTitle("La Guerre des boutons");
		for (Movie movie : films2) {
			System.out.println(movie);
		}
		Assert.assertEquals(2, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherMoviesParLeTitre : " + (end - deb));
	}

	@Test
	@Ignore
	public void testFindByTitleLike() throws MovieServiceException {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Movie> films1 = movieService.findByTitleLike("Alien, le huitième");
		for (Movie movie : films1) {
			System.out.println(movie);
		}
		Assert.assertEquals(1, films1.size());

		// 'La Guerre des boutons'
		List<Movie> films2 = movieService.findByTitleLike("La Guerre des");
		for (Movie movie : films2) {
			System.out.println(movie);
		}
		Assert.assertEquals(6, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherMoviesDontLeTitreCommencePar : " + (end - deb));
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
	public void findByActorName() throws MovieServiceException {
		long deb = System.currentTimeMillis();

		// 'Meryl Streep'
		String name = "Meryl Streep";
		List<Movie> films1 = movieService.findByActor(name);
		for (Movie movie : films1) {
			System.out.println(movie);
		}
		Assert.assertEquals(7, films1.size());

		// 'François Berland'
		name = "François Berland";
		List<Movie> films2 = movieService.findByActor(name);
		for (Movie movie : films2) {
			System.out.println(movie);
		}
		Assert.assertEquals(1, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("findByActor : " + (end - deb));
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
