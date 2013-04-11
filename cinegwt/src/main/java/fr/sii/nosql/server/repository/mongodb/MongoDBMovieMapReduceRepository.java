package fr.sii.nosql.server.repository.mongodb;

import java.util.List;

import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

public interface MongoDBMovieMapReduceRepository {

	long countWithQuery(Kind kind);

	long countWithQueryMR(Kind kind);

	int averageDurationWithQueryMR(Kind kind);

	int averageDuration(Kind kind);

	List<Movie> findByKindMR(Kind kind);

	List<Movie> findByKind(Kind kind);

	List<Movie> findByActorMR(long id);

}