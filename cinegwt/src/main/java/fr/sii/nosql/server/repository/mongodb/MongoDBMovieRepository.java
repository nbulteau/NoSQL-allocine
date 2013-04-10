package fr.sii.nosql.server.repository.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.shared.buisiness.Movie;

@Transactional
public interface MongoDBMovieRepository extends MongoRepository<Movie, Long> {
	List<Movie> findByTitle(String title);

	List<Movie> findByTitleLike(String string);

	@Query(value = "{'actors.person._id':?0}.sort({title:1}")
	List<Movie> findByActor(long id);

	@Query(value = "{'actors.person.name':?0}.sort({title:1}")
	List<Movie> findByActor(String name);

	@Query(value = "{'directors._id':?0}.sort({title:1}")
	List<Movie> findByDirector(long id);

	@Query(value = "{'directors.name':?0}.sort({title:1}")
	List<Movie> findByDirector(String name);

}
