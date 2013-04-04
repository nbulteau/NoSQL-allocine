package fr.sii.formation.gwt.server.repository.jpa;

import java.util.List;

import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("jpa")
@Repository("filmDAO")
public class FilmDAOImpl extends GenericDAOWithJPA<Film, Long> implements FilmDAO {

	private final static Logger LOGGER = LoggerFactory.getLogger(FilmDAOImpl.class);

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> findAllSorted(int firstResult, int maxResults, Boolean selectedVu, String selectedGenre, String filtreTitreParameter) {

		Query query = null;
		String stringQuery = "SELECT f FROM Film f ";
		if (selectedGenre != null && selectedVu != null) {
			stringQuery += "JOIN f.genres g WHERE f.vu=:vu AND g.label=:genre";
		} else if (selectedGenre == null && selectedVu != null) {
			stringQuery += "WHERE f.vu=:vu";
		} else if (selectedGenre != null && selectedVu == null) {
			stringQuery += "JOIN f.genres g WHERE g.label=:genre";
		}
		if (filtreTitreParameter != null && !"".equals(filtreTitreParameter)) {
			if (selectedGenre != null || selectedVu != null) {
				stringQuery += " AND f.titre LIKE :titre";
			} else {
				stringQuery += "WHERE f.titre LIKE :titre";
			}
		}
		stringQuery += " ORDER BY f.titre";

		LOGGER.debug(stringQuery);

		query = entityManager.createQuery(stringQuery);
		query.setFirstResult(firstResult);
		query.setMaxResults(maxResults);

		if (selectedVu != null) {
			query.setParameter("vu", selectedVu);
		}
		if (selectedGenre != null) {
			query.setParameter("genre", selectedGenre);
		}
		if (filtreTitreParameter != null && !"".equals(filtreTitreParameter)) {
			query.setParameter("titre", filtreTitreParameter + "%");
		}

		return query.getResultList();
	}

	@Override
	public long count() {
		String stringQuery = "SELECT COUNT(id) FROM Film f";

		Query query = entityManager.createQuery(stringQuery);
		long value = ((Long) query.getSingleResult()).longValue();

		return value;
	}

	@Override
	public long count(Boolean selectedVu, String selectedGenre, String filtreTitreParameter) {
		Query query = null;
		String stringQuery = "SELECT COUNT(id) FROM Film f ";
		if (selectedGenre != null && selectedVu != null) {
			stringQuery += "JOIN f.genres g WHERE f.vu=:vu AND g.label=:genre";
		} else if (selectedGenre == null && selectedVu != null) {
			stringQuery += "WHERE f.vu=:vu";
		} else if (selectedGenre != null && selectedVu == null) {
			stringQuery += "JOIN f.genres g WHERE g.label=:genre";
		}
		if (filtreTitreParameter != null && !"".equals(filtreTitreParameter)) {
			if (selectedGenre != null || selectedVu != null) {
				stringQuery += " AND f.titre LIKE :titre";
			} else {
				stringQuery += "WHERE f.titre LIKE :titre";
			}
		}

		LOGGER.debug(stringQuery);

		query = entityManager.createQuery(stringQuery);

		if (selectedVu != null) {
			query.setParameter("vu", selectedVu);
		}
		if (selectedGenre != null) {
			query.setParameter("genre", selectedGenre);
		}
		if (filtreTitreParameter != null && !"".equals(filtreTitreParameter)) {
			query.setParameter("titre", filtreTitreParameter + "%");
		}

		long value = ((Long) query.getSingleResult()).longValue();

		return value;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> rechercherFilmsDansLequelPersonneEstActeur(long idPersonne) {
		String stringQuery = "SELECT f FROM Film f JOIN f.acteurs a JOIN a.personne p WHERE p.id = :idPersonne ORDER BY f.titre";

		Query query = entityManager.createQuery(stringQuery);
		LOGGER.debug(stringQuery);
		query.setParameter("idPersonne", idPersonne);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> rechercherFilmsDansLequelPersonneEstRealisateur(long idPersonne) {
		String stringQuery = "SELECT f FROM Film f JOIN f.realisateurs a WHERE a.id = :idPersonne ORDER BY f.titre";

		Query query = entityManager.createQuery(stringQuery);
		LOGGER.debug(stringQuery);
		query.setParameter("idPersonne", idPersonne);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Long> rechercherTousLesIdsDeFilm() {
		String stringQuery = "SELECT f.id FROM Film f ORDER BY f.id ASC";
		LOGGER.debug(stringQuery);
		Query query = entityManager.createQuery(stringQuery);
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> rechercherFilmsDontLeTitreCommencePar(String titre) {
		String stringQuery = "SELECT f FROM Film f WHERE f.titre LIKE :titre ORDER BY f.id ASC";
		LOGGER.debug(stringQuery);
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("titre", titre + "%");

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> rechercherFilmsParLeTitre(String titre) {
		String stringQuery = "SELECT f FROM Film f WHERE f.titre = :titre ORDER BY f.id ASC";
		LOGGER.debug(stringQuery);
		Query query = entityManager.createQuery(stringQuery);
		query.setParameter("titre", titre);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> rechercherFilmsDansLequelPersonneEstActeur(String name) {
		String stringQuery = "SELECT f FROM Film f JOIN f.acteurs a JOIN a.personne p WHERE p.nom = :name ORDER BY f.titre";

		Query query = entityManager.createQuery(stringQuery);
		LOGGER.debug(stringQuery);
		query.setParameter("name", name);

		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Film> rechercherFilmsDansLequelPersonneEstRealisateur(String name) {
		String stringQuery = "SELECT f FROM Film f JOIN f.realisateurs r WHERE r.nom = :name ORDER BY f.titre";

		Query query = entityManager.createQuery(stringQuery);
		LOGGER.debug(stringQuery);
		query.setParameter("name", name);

		return query.getResultList();
	}
}
