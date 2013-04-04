package fr.sii.formation.gwt.server.repository.mongodb;

import java.util.List;

import fr.sii.formation.gwt.shared.MovieFilter;
import fr.sii.formation.gwt.shared.buisiness.Movie;

public interface MongoDBMovieMapReduceRepository {

	long countWithQuery(MovieFilter movieFilter);

	long countWithQueryMR(MovieFilter movieFilter);

	int averageDurationWithQueryMR(MovieFilter movieFilter);

	int averageDuration(MovieFilter movieFilter);

	List<Movie> findByKindMR(MovieFilter movieFilter);

	List<Movie> findByKind(MovieFilter movieFilter);

	List<Movie> findByActorMR(long id);

}