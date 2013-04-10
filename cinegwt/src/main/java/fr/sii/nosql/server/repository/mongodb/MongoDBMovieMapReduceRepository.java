package fr.sii.nosql.server.repository.mongodb;

import java.util.List;

import fr.sii.nosql.server.service.MovieFilter;
import fr.sii.nosql.shared.buisiness.Movie;

public interface MongoDBMovieMapReduceRepository {

	long countWithQuery(MovieFilter movieFilter);

	long countWithQueryMR(MovieFilter movieFilter);

	int averageDurationWithQueryMR(MovieFilter movieFilter);

	int averageDuration(MovieFilter movieFilter);

	List<Movie> findByKindMR(MovieFilter movieFilter);

	List<Movie> findByKind(MovieFilter movieFilter);

	List<Movie> findByActorMR(long id);

}