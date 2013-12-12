package fr.sii.nosql.server.repository.mongodb;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Transactional
public interface MongoDBMovieRepository extends MongoRepository<Movie, Long>, MovieRepository {

	@Override
	List<Movie> findByTitle(String title);

	@Override
	List<Movie> findByTitleLike(String string);

	@Override
	List<Movie> findByKinds(Kind kind);

	@Override
	@Query(value = "{'castMembers.person._id':?0}.sort({title:1}")
	List<Movie> findByActor(long id);

	@Override
	@Query(value = "{'castMembers.person.name':?0}.sort({title:1}")
	List<Movie> findByActor(String name);

	@Override
	@Query(value = "{'directors._id':?0}.sort({title:1}")
	List<Movie> findByDirector(long id);

	@Override
	@Query(value = "{'directors.name':?0}.sort({title:1}")
	List<Movie> findByDirector(String name);
}
