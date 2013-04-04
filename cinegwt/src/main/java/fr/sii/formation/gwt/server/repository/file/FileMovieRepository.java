package fr.sii.formation.gwt.server.repository.file;

import java.util.Collection;

import fr.sii.formation.gwt.server.allocine.buisiness.AlloCineMovie;
import fr.sii.formation.gwt.shared.buisiness.Movie;

public interface FileMovieRepository {
	Collection<Movie> findAll();

	Movie findOne(Long id);

	long count();

	Iterable<Movie> all();

	void add(long id, String body);

	void add(long id, AlloCineMovie movie);
}
