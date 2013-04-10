package fr.sii.nosql.server.repository.file;

import java.util.Collection;

import fr.sii.nosql.server.allocine.buisiness.AlloCineMovie;
import fr.sii.nosql.shared.buisiness.Movie;

public interface FileMovieRepository {
	Collection<Movie> findAll();

	Movie findOne(Long id);

	long count();

	Iterable<Movie> all();

	void add(long id, String body);

	void add(long id, AlloCineMovie movie);
}
