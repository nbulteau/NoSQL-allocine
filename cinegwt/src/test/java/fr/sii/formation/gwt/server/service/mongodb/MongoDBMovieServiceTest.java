package fr.sii.formation.gwt.server.service.mongodb;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Actor;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
@ActiveProfiles("mongodb")
public class MongoDBMovieServiceTest {

	private static final String AVATAR = "Movie [id=61282, title=Avatar, originaltitle=Avatar, releasedate=Wed Dec 16 00:00:00 CET 2009, duration=9720, directors=[Person [id=1066, name=James Cameron]], actors=[Actor [role=Jake Sully, person=Person [id=41339, name=Sam Worthington]], Actor [role=Neytiri, person=Person [id=34515, name=Zoe Saldana]], Actor [role=Grace Augustine, person=Person [id=259, name=Sigourney Weaver]], Actor [role=le colonel Miles Quaritch, person=Person [id=6407, name=Stephen Lang]], Actor [role=Trudy Chacon, person=Person [id=60617, name=Michelle Rodriguez]], Actor [role=Parker Selfridge, person=Person [id=28985, name=Giovanni Ribisi]], Actor [role=Norm Spellman, person=Person [id=130952, name=Joel Moore]], Actor [role=Eytukan, person=Person [id=18050, name=Wes Studi]], Actor [role=Moat, person=Person [id=175724, name=CCH Pounder]], Actor [role=Tsu'Tey, person=Person [id=117875, name=Laz Alonso]], Actor [role=Dr. Max Patel, person=Person [id=218328, name=Dileep Rao]], Actor [role=Akwey, person=Person [id=77973, name=Peter Mensah]], Actor [role=le caporal Lyne Wainfleet, person=Person [id=61597, name=Matt Gerald]], Actor [role=le chef d'équipage du Venture Star, person=Person [id=104999, name=Scott Lawrence]]], kinds=[Science_fiction, Aventure], synopsis=Malgré sa paralysie, Jake Sully, un ancien marine immobilisé dans un fauteuil roulant, est resté un combattant au plus profond de son être. Il est recruté pour se rendre à des années-lumière de la Terre, sur Pandora, où de puissants groupes industriels exploitent un minerai rarissime destiné à résoudre la crise énergétique sur Terre. Parce que l'atmosphère de Pandora est toxique pour les humains, ceux-ci ont créé le Programme Avatar, qui permet à des \" pilotes \" humains de lier leur esprit à un avatar, un corps biologique commandé à distance, capable de survivre dans cette atmosphère létale. Ces avatars sont des hybrides créés génétiquement en croisant l'ADN humain avec celui des Na'vi, les autochtones de Pandora.Sous sa forme d'avatar, Jake peut de nouveau marcher. On lui confie une mission d'infiltration auprès des Na'vi, devenus un obstacle trop conséquent à l'exploitation du précieux minerai. Mais tout va changer lorsque Neytiri, une très belle Na'vi, sauve la vie de Jake...]";

	@Autowired
	@Qualifier("mongoDBmovieService")
	MovieService movieService;

	@Test
	public void loadAlloCine() throws MovieServiceException {
		for (int i = 377; i < 200000; i++) {
			if (!movieService.exists(i)) {
				movieService.retrieveAndSave(i, false);
			} else {
				System.out.println("" + i + " already exist");
			}
		}
	}

	@Test
	public void saveMovie() throws MovieServiceException {
		String movieTitle = "movieTitle";

		Person person1 = new Person(1, "P1");
		Person person2 = new Person(2, "P2");
		Person person3 = new Person(2, "P3");

		Movie movie = new Movie(1, movieTitle, "");

		List<Actor> actors = new ArrayList<Actor>();
		Actor actor1 = new Actor(person1, "role1");
		actors.add(actor1);
		Actor actor2 = new Actor(person3, "role2");
		actors.add(actor2);
		movie.getActors().addAll(actors);

		List<Person> directors = new ArrayList<Person>();
		directors.add(person2);
		movie.getDirectors().addAll(directors);

		List<Kind> kinds = new ArrayList<Kind>();
		kinds.add(Kind.Action);
		kinds.add(Kind.Arts_Martiaux);
		movie.getKinds().addAll(kinds);

		movieService.save(movie);
		movie = movieService.findById(movie.getId());

		// oracle
		Assert.assertNotNull(movie);
		Assert.assertEquals(movieTitle, movie.getTitle());
	}

	@Test
	public void retrieveAndSaveTest() throws MovieServiceException {
		long idMovie = 61282;

		Movie movie = movieService.retrieveAndSave(idMovie, false);
		Assert.assertEquals(idMovie, movie.getId());
		Assert.assertEquals(AVATAR, movie.toString());
	}

	@Test
	public void testFindById() {
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
	public void testFindByTitle() {
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
	public void testFindByTitleLike() {
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
	public void testLoadAll() {
		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Movie> films = movieService.findAll();
		for (Movie movie : films) {
			System.out.println(movie);
		}

		long end = System.currentTimeMillis();
		System.out.println("rechercherMoviesDontLeTitreCommencePar : " + (end - deb));
	}

	@Test
	public void testFindByActorId() {
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
	public void findByActorName() {
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
	public void findByDirectorName() {
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
