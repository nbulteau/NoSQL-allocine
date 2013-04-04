package fr.sii.formation.gwt.server.repository;

import org.junit.Ignore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.formation.gwt.server.repository.jpa.Film;
import fr.sii.formation.gwt.server.repository.jpa.FilmDAO;
import fr.sii.formation.gwt.server.repository.jpa.Personne;
import fr.sii.formation.gwt.server.repository.jpa.PersonneDAO;

@Ignore
@Profile("jpa")
@Service("bidonService")
public class BidonService {

	@Autowired
	FilmDAO filmDAO;

	@Autowired
	PersonneDAO personneDAO;

	@Transactional
	public void update(Film film) {
		filmDAO.update(film);
		filmDAO.detach(film);
	}

	@Transactional
	public void update(Personne personne) {
		personneDAO.update(personne);
		personneDAO.detach(personne);
	}

	@Transactional
	public void save(Film film) {
		filmDAO.persist(film);
	}

	@Transactional
	public void delete(Film film) {
		film = filmDAO.loadById(film.getId());
		filmDAO.delete(film);
	}

	@Transactional
	public void delete(Personne personne) {
		personne = personneDAO.loadById(personne.getId());
		personneDAO.delete(personne);
	}
}
