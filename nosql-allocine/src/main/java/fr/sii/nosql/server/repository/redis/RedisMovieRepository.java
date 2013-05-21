package fr.sii.nosql.server.repository.redis;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;

public interface RedisMovieRepository extends MovieRepository {

	long countMoviesWithQuery(Kind kind);

	long countKinds();
}