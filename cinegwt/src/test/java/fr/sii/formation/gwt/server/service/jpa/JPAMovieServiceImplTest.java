package fr.sii.formation.gwt.server.service.jpa;

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

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
// @ActiveProfiles({ "jpa", "mongodb" })
@ActiveProfiles({ "jpa" })
public class JPAMovieServiceImplTest {

	private static final int CONFETTI_108988 = 108988;

	@Autowired
	@Qualifier("jpaMovieService")
	private MovieService filmService;

	@Test
	@Ignore
	public void testFindById() {
		// "Alien, le huitième passager" id
		long idMovie = 62;

		Movie movie = filmService.findById(idMovie);

		Assert.assertEquals(idMovie, movie.getId());
	}

	@Autowired
	private AlloCineService alloCineService;

	@Test
	public void populate() throws MovieServiceException {

		String fileName = "D:\\Users\\Nicolas\\Documents\\My Dropbox\\vu.txt";
		List<Movie> movies = alloCineService.retrieveMovies(fileName);
		for (Movie movie : movies) {
			filmService.save(movie);
		}
	}

	// @Autowired
	// @Qualifier("mongoDBMovieService")
	// MovieService movieService;
	//
	// @Test
	// @Ignore
	// public void populateMoviesFromMongoDB() throws IOException,
	// MovieServiceException {
	// long nbMovies = movieService.getMoviesCount(null);
	// System.out.println("nb movies  : " + nbMovies);
	//
	// int step = 10;
	// int count = 0;
	// int index = count * step;
	// while (count < nbMovies) {
	// List<AlloCineMovie> movies = movieService.fetchMovies(count, step, null);
	// for (AlloCineMovie movie : movies) {
	// System.out.println("=> " + index++ + " : movie : " + movie.getTitle());
	// filmService.save(movie);
	// }
	// count++;
	// }
	// }
	//
	// @Test
	// @Ignore
	// public void populateOneMovieFromMongoDB() throws IOException,
	// MovieServiceException {
	// // the movie Confetti has some weird characters in the synopsis
	// // this test makes sure that all is perfectly utf8-compatible
	// AlloCineMovie movie = movieService.findById(CONFETTI_108988);
	// System.out.println("=> movie : " + movie.getTitle());
	// filmService.save(movie);
	// }
	//
	// @Autowired
	// BidonService bidonService;
	//
	// @Autowired
	// FilmDAO filmDAO;
	//
	// @Autowired
	// PosterService posterService;
	//
	// @Test
	// @Ignore
	// public void populatePostersFromMongoDB() throws IOException,
	// MovieServiceException {
	// long nbMovies = filmDAO.count(false, null, null);
	// System.out.println("nb movies  : " + nbMovies);
	//
	// int step = 10;
	// int count = 0;
	// int index = count;
	// while (count < nbMovies) {
	// List<Film> films = filmDAO.findAllSorted(count, step, false, null, null);
	// for (Film film : films) {
	// System.out.println("=> " + index++ + " : movie : " + film.getTitre());
	// byte[] poster = posterService.getPoster(film.getId());
	// film.setAffiche(poster);
	// bidonService.update(film);
	// }
	// count += step;
	// }
	// }
	//
	// @Autowired
	// PersonneDAO personeDAO;
	//
	// @Autowired
	// PhotoService photoService;
	//
	// @Test
	// @Ignore
	// public void populatePhotosFromMongoDB() throws IOException,
	// MovieServiceException {
	// long nbPersonnes = personeDAO.countNbElementsInTable(null);
	// System.out.println("nb personnes  : " + nbPersonnes);
	//
	// int step = 10;
	// int count = 10303;
	// int index = count;
	// while (count < nbPersonnes) {
	// List<Personne> personnes = personeDAO.findAllSorted(count, step, null,
	// null, null);
	// for (Personne personne : personnes) {
	// System.out.println("=> " + index++ + " : peronne : " +
	// personne.getNom());
	// byte[] poster = photoService.getPhoto(personne.getId());
	// personne.setPhoto(poster);
	// bidonService.update(personne);
	// }
	// count += step;
	// }
	// }

}
