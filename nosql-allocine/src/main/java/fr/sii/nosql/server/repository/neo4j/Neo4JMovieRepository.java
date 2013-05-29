package fr.sii.nosql.server.repository.neo4j;

import org.springframework.data.neo4j.repository.GraphRepository;

import fr.sii.nosql.shared.buisiness.Movie;

public interface Neo4JMovieRepository extends GraphRepository<Movie> {
	
}
