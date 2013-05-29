package fr.sii.nosql.server.repository.mongodb.optimised;

import java.util.List;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

public interface MongoDBMovieRepository extends MovieRepository {

	long countWithQuery(Kind kind);

	long countWithQueryMR(Kind kind);

	int averageDurationWithQueryMR(Kind kind);

	int averageDuration(Kind kind);

	List<Movie> findByKindMR(Kind kind);

	List<Movie> findByActorMR(String name);

	List<Movie> save(List<Movie> movies);

}