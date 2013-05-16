package fr.sii.nosql.server.repository.file;

import fr.sii.nosql.server.allocine.buisiness.MovieResult;
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Movie;

public interface FileMovieRepository extends MovieRepository {

//	void save(long id, String body);

	void save(long id, MovieResult movie);

	Iterable<Movie> all();
}
