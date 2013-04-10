package fr.sii.formation.gwt.server.allocine.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.service.MovieServiceException;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class AlloCineServiceImplTest {

	@Autowired
	AlloCineService alloCineService;

	private static final String AVATAR = "Movie [id=61282, title=Avatar, originaltitle=Avatar, releasedate=Wed Dec 16 00:00:00 CET 2009, duration=9720, directors=[Person [id=1066, name=James Cameron]], actors=[Actor [role=Jake Sully, person=Person [id=41339, name=Sam Worthington]], Actor [role=Neytiri, person=Person [id=34515, name=Zoe Saldana]], Actor [role=Grace Augustine, person=Person [id=259, name=Sigourney Weaver]], Actor [role=le colonel Miles Quaritch, person=Person [id=6407, name=Stephen Lang]], Actor [role=Trudy Chacon, person=Person [id=60617, name=Michelle Rodriguez]], Actor [role=Parker Selfridge, person=Person [id=28985, name=Giovanni Ribisi]], Actor [role=Norm Spellman, person=Person [id=130952, name=Joel Moore]], Actor [role=Eytukan, person=Person [id=18050, name=Wes Studi]], Actor [role=Moat, person=Person [id=175724, name=CCH Pounder]], Actor [role=Tsu'Tey, person=Person [id=117875, name=Laz Alonso]], Actor [role=Dr. Max Patel, person=Person [id=218328, name=Dileep Rao]], Actor [role=Akwey, person=Person [id=77973, name=Peter Mensah]], Actor [role=le caporal Lyne Wainfleet, person=Person [id=61597, name=Matt Gerald]], Actor [role=le chef d'équipage du Venture Star, person=Person [id=104999, name=Scott Lawrence]]], kinds=[Science_fiction, Aventure], synopsis=Malgré sa paralysie, Jake Sully, un ancien marine immobilisé dans un fauteuil roulant, est resté un combattant au plus profond de son être. Il est recruté pour se rendre à des années-lumière de la Terre, sur Pandora, où de puissants groupes industriels exploitent un minerai rarissime destiné à résoudre la crise énergétique sur Terre. Parce que l'atmosphère de Pandora est toxique pour les humains, ceux-ci ont créé le Programme Avatar, qui permet à des \" pilotes \" humains de lier leur esprit à un avatar, un corps biologique commandé à distance, capable de survivre dans cette atmosphère létale. Ces avatars sont des hybrides créés génétiquement en croisant l'ADN humain avec celui des Na'vi, les autochtones de Pandora.Sous sa forme d'avatar, Jake peut de nouveau marcher. On lui confie une mission d'infiltration auprès des Na'vi, devenus un obstacle trop conséquent à l'exploitation du précieux minerai. Mais tout va changer lorsque Neytiri, une très belle Na'vi, sauve la vie de Jake...]";

	private static final String CONDORMAN = "Movie [id=61281, title=Condorman, originaltitle=Condorman, releasedate=Wed Oct 28 00:00:00 CET 1981, duration=5400, directors=[Person [id=51388, name=Charles Jarrott]], actors=[Actor [role=Woody Wilkins, person=Person [id=50506, name=Michael Crawford]], Actor [role=Krokov, person=Person [id=2630, name=Oliver Reed]], Actor [role=Natalia, person=Person [id=3858, name=Barbara Carrera]], Actor [role=Harry Oslo, person=Person [id=3327, name=James Hampton]], Actor [role=Morovich, person=Person [id=916, name=Jean-Pierre Kalfon]], Actor [role=Russ Devlin, person=Person [id=40015, name=Dana Elcar]], Actor [role=Agent russe, person=Person [id=791, name=Vernon Dobtcheff]], Actor [role=Chef de la CIA, person=Person [id=73702, name=Robert Arden]], Actor [role=null, person=Person [id=128125, name=David Pontremoli]]], kinds=[Comedie, Aventure, Action], synopsis=Un auteur de bandes dessinées devient le super-héros qu'il a créé et se trouve mêlé à une affaire d'espionnage dans un pays de l'Est.]";

	@Ignore
	@Test
	public void testRetrieveAvatar() {
		// Avatar id
		long idMovie = 61282;

		Movie movie = alloCineService.retrieveMovie(idMovie);
		Assert.assertEquals(idMovie, movie.getId());
		Assert.assertEquals(AVATAR, movie.toString());
	}

	@Ignore
	@Test
	public void testRetrieveCondorman() {
		// Condorman id
		long idMovie = 61281;

		Movie movie = alloCineService.retrieveMovie(idMovie);
		Assert.assertEquals(idMovie, movie.getId());
		Assert.assertEquals(CONDORMAN, movie.toString());
	}

	@Test
	@Ignore
	public void populate() throws MovieServiceException {

		String fileName = "D:\\Users\\Nicolas\\Documents\\My Dropbox\\vu.txt";
		List<Movie> movies = alloCineService.retrieveMovies(fileName);
		System.out.println(movies.size());
	}

	@Test
	public void retrieveMovieList() {
		List<Movie> movies = alloCineService.retrieveMovieList();
		System.out.println(movies.size());
	}
}
