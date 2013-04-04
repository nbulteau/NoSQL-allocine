package fr.sii.formation.gwt.server.repository.jpa;

import java.util.List;

public interface FilmDAO extends GenericDAO<Film, Long> {
	long count();

	long count(Boolean selectedVu, String selectedGenre, String filtreTitreParameter);

	List<Film> rechercherFilmsParLeTitre(String titre);

	List<Film> rechercherFilmsDontLeTitreCommencePar(String titre);

	List<Film> rechercherFilmsDansLequelPersonneEstActeur(long id);

	List<Film> rechercherFilmsDansLequelPersonneEstActeur(String name);

	List<Film> rechercherFilmsDansLequelPersonneEstRealisateur(long id);

	List<Film> rechercherFilmsDansLequelPersonneEstRealisateur(String name);

	List<Long> rechercherTousLesIdsDeFilm();

	List<Film> findAllSorted(int firstResult, int maxResults, Boolean selectedVu, String selectedGenre, String filtreTitreParameter);

}
