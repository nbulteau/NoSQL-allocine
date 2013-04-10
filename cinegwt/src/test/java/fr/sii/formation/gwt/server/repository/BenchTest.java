package fr.sii.formation.gwt.server.repository;

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

import fr.sii.nosql.server.repository.hbase.HBaseMovieRepository;
import fr.sii.nosql.server.repository.jpa.Acteur;
import fr.sii.nosql.server.repository.jpa.Film;
import fr.sii.nosql.server.repository.jpa.FilmDAO;
import fr.sii.nosql.server.repository.jpa.Genre;
import fr.sii.nosql.server.repository.jpa.GenreDAO;
import fr.sii.nosql.server.repository.jpa.Personne;
import fr.sii.nosql.server.repository.jpa.PersonneDAO;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieMapReduceRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieRepository;
import fr.sii.nosql.server.repository.redis.RedisMovieRepository;
import fr.sii.nosql.shared.MovieFilter;
import fr.sii.nosql.shared.buisiness.Actor;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

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
	FilmDAO filmDAO;

	@Autowired(required = false)
	PersonneDAO personneDAO;

	@Autowired(required = false)
	private GenreDAO genreService;

	@Autowired(required = false)
	BidonService bidonService;

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
		Film film = filmDAO.loadById(ALIEN);
		Assert.assertNotNull(film);

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
		List<Film> films = filmDAO.rechercherFilmsParLeTitre(LA_GUERRE_DES_BOUTONS);
		Assert.assertEquals(2, films.size());

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
		List<Film> films1 = filmDAO.rechercherFilmsDontLeTitreCommencePar(ALIEN_LE_HUITIEME);
		Assert.assertEquals(1, films1.size());

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

		List<Film> films = filmDAO.rechercherFilmsDansLequelPersonneEstActeur(MERYL_STEEP_ID);
		Assert.assertEquals(7, films.size());

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

		List<Film> films = filmDAO.rechercherFilmsDansLequelPersonneEstActeur(MERYL_STREEP);
		Assert.assertEquals(7, films.size());

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

		long count = filmDAO.count();
		Assert.assertEquals(TOTAL_MOVIE_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countJPA : " + (end - deb));
	}

	@Test
	public void countMoviesByKindMongoDBWithMapReduce() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		long count = mapReduceRepository.countWithQueryMR(movieFilter);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countByKindMongoDBWithMapReduce : " + (end - deb));
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
	public void countMoviesByKindJPA() {
		long deb = System.currentTimeMillis();

		long count = filmDAO.count(null, Kind.Action.getLabel(), null);
		Assert.assertEquals(ACTION_MOVIES_COUNT, count);

		long end = System.currentTimeMillis();
		System.out.println("countByKindJPA : " + (end - deb));
	}

	@Test
	public void findMoviesByKindMongoDBWithMapReduce() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		List<Movie> movies = mapReduceRepository.findByKindMR(movieFilter);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindMongoDBWithMapReduce : " + (end - deb));
	}

	@Test
	public void findMoviesByKindMongoDB() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		List<Movie> movies = mapReduceRepository.findByKind(movieFilter);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindMongoDB : " + (end - deb));
	}

	@Test
	public void findMoviesByKindRedis() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		List<Movie> movies = redisMovieRepository.findByKind(movieFilter);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindRedis : " + (end - deb));
	}

	@Test
	public void findMoviesByKindHBase() {
		MovieFilter movieFilter = new MovieFilter(null, Kind.Action, null);
		long deb = System.currentTimeMillis();

		List<Movie> movies = hbaseMovieRepository.findByKind(movieFilter);
		Assert.assertEquals(ACTION_MOVIES_COUNT, movies.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindHBase : " + (end - deb));
	}

	@Test
	public void findMoviesByKindJPA() {
		long deb = System.currentTimeMillis();

		List<Film> films = filmDAO.findAllSorted(0, 2000, null, Kind.Action.getLabel(), null);
		Assert.assertEquals(ACTION_MOVIES_COUNT, films.size());

		long end = System.currentTimeMillis();
		System.out.println("findByKindJPA : " + (end - deb));
	}

	@Test
	public void saveMovieJPA() {
		Film film = new Film();
		film.setId(100000000000l);
		film.setTitre("titre");
		film.setTitreoriginal("titreoriginal");
		film.setAffiche(null);
		film.setDatesortie(new Date());
		film.setDuree(1234);
		film.setSynopsis("synopsis");
		film.setVu(false);

		Acteur acteur = new Acteur();
		acteur.setRole("role");
		Personne personne1 = new Personne();
		personne1.setId(100000000001l);
		personne1.setNom("nom");
		acteur.setPersonne(personne1);
		film.getActeurs().add(acteur);

		Personne personne2 = new Personne();
		personne2.setId(100000000002l);
		personne2.setNom("nom");
		film.getRealisateurs().add(personne2);

		Genre genre = genreService.loadById("Action");
		film.getGenres().add(genre);

		long deb = System.currentTimeMillis();

		bidonService.save(film);

		long end = System.currentTimeMillis();
		System.out.println("saveJPA : " + (end - deb));

		film = filmDAO.loadById(film.getId());
		Assert.assertNotNull(film);
		bidonService.delete(film);
		Assert.assertNull(filmDAO.loadById(film.getId()));

		personne1 = personneDAO.loadById(personne1.getId());
		Assert.assertNotNull(personne1);
		bidonService.delete(personne1);
		Assert.assertNull(personneDAO.loadById(personne1.getId()));

		personne2 = personneDAO.loadById(personne2.getId());
		Assert.assertNotNull(personne2);
		bidonService.delete(personne2);
		Assert.assertNull(personneDAO.loadById(personne2.getId()));
	}

	@Test
	public void saveMovieMongoDB() {
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

		mongoDBMovieRepository.save(movie);

		long end = System.currentTimeMillis();
		System.out.println("saveMongoDB : " + (end - deb));

		Assert.assertNotNull(mongoDBMovieRepository.findOne(movie.getId()));
		mongoDBMovieRepository.delete(movie);
		Assert.assertNull(mongoDBMovieRepository.findOne(movie.getId()));
	}
}
