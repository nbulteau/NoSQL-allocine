package fr.sii.formation.gwt.server.jparepository;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import fr.sii.formation.gwt.server.repository.jpa.Film;
import fr.sii.formation.gwt.server.repository.jpa.FilmDAO;

//@Ignore
@Profile("jpa")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application-context.xml")
public class FilmDAOImplTest {

	@Autowired
	FilmDAO filmDAO;

	@Test
	public void testFindById() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		Film film1 = filmDAO.loadById(62l);
		Assert.assertNotNull(film1);

		// 'La Guerre des boutons'
		Film film2 = filmDAO.loadById(188649l);
		Assert.assertNotNull(film2);

		long end = System.currentTimeMillis();
		System.out.println("loadById : " + (end - deb));

		filmDAO.detach(film1);
		filmDAO.detach(film2);
	}

	@Test
	public void testFindByTitle() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Film> films1 = filmDAO.rechercherFilmsParLeTitre("Alien, le huitième passager");
		Assert.assertEquals(1, films1.size());

		// 'La Guerre des boutons'
		List<Film> films2 = filmDAO.rechercherFilmsParLeTitre("La Guerre des boutons");
		Assert.assertEquals(2, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherFilmsParLeTitre : " + (end - deb));

		for (Film film : films1) {
			filmDAO.detach(film);
		}
		for (Film film : films2) {
			filmDAO.detach(film);
		}
	}

	@Test
	public void testFindByTitleLike() {
		long deb = System.currentTimeMillis();

		// 'Alien, le huitième passager'
		List<Film> films1 = filmDAO.rechercherFilmsDontLeTitreCommencePar("Alien, le huitième");
		Assert.assertEquals(1, films1.size());

		// 'La Guerre des boutons'
		List<Film> films2 = filmDAO.rechercherFilmsDontLeTitreCommencePar("La Guerre des");
		Assert.assertEquals(6, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherFilmsDontLeTitreCommencePar : " + (end - deb));

		for (Film film : films1) {
			filmDAO.detach(film);
		}
		for (Film film : films2) {
			filmDAO.detach(film);
		}
	}

	@Test
	public void testLoadAll() {
		long count = filmDAO.count();

		long deb = System.currentTimeMillis();

		// 'La Guerre des boutons'
		List<Film> films = filmDAO.loadAll();
		Assert.assertEquals(count, films.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherMoviesDontLeTitreCommencePar : " + (end - deb));
	}

	@Test
	public void testFindByActorId() {
		// 'Meryl Streep'
		long id = 9;
		List<Film> films = null;

		long deb = System.currentTimeMillis();

		films = filmDAO.rechercherFilmsDansLequelPersonneEstActeur(id);
		Assert.assertEquals(7, films.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherFilmsDansLequelPersonneEstActeur(id) : " + (end - deb));

		for (Film film : films) {
			filmDAO.detach(film);
		}
	}

	@Test
	public void testFindByActorName() {
		long deb = System.currentTimeMillis();

		// 'Meryl Streep'
		String name = "Meryl Streep";
		List<Film> films1 = filmDAO.rechercherFilmsDansLequelPersonneEstActeur(name);
		Assert.assertEquals(7, films1.size());

		// 'François Berland'
		name = "François Berland";
		List<Film> films2 = filmDAO.rechercherFilmsDansLequelPersonneEstActeur(name);
		Assert.assertEquals(1, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherFilmsDansLequelPersonneEstActeur : " + (end - deb));

		for (Film film : films1) {
			filmDAO.detach(film);
		}
		for (Film film : films2) {
			filmDAO.detach(film);
		}

	}

	@Test
	public void testFindByDirectorName() {
		long deb = System.currentTimeMillis();

		// 'Meryl Streep'
		List<Film> films1 = filmDAO.rechercherFilmsDansLequelPersonneEstRealisateur("Meryl Streep");
		Assert.assertEquals(0, films1.size());

		// 'Robert De Niro'
		List<Film> films2 = filmDAO.rechercherFilmsDansLequelPersonneEstRealisateur("Robert De Niro");
		Assert.assertEquals(1, films2.size());

		long end = System.currentTimeMillis();
		System.out.println("rechercherFilmsDansLequelPersonneEstRealisateur : " + (end - deb));

		for (Film film : films1) {
			filmDAO.detach(film);
		}
		for (Film film : films2) {
			filmDAO.detach(film);
		}
	}

}
