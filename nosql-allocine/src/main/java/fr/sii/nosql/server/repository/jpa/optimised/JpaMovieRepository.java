package fr.sii.nosql.server.repository.jpa.optimised;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

public interface JpaMovieRepository extends JpaRepository<Movie, Long>, MovieRepository {
	@Override
	List<Movie> findByTitle(String title);

	@Override
	@Query(value = "SELECT movie FROM Movie movie WHERE movie.title like :string%")
	List<Movie> findByTitleLike(@Param("string") String string);

	@Override
	@Query(value = "SELECT movie FROM Movie movie JOIN movie.castMembers castMember JOIN castMember.person person WHERE person.id = :id")
	List<Movie> findByActor(@Param("id") long id);

	@Override
	@Query(value = "SELECT movie FROM Movie movie JOIN movie.castMembers castMember JOIN castMember.person person WHERE person.name = :name")
	List<Movie> findByActor(@Param("name") String name);

	@Override
	@Query(value = "SELECT movie FROM Movie movie JOIN movie.directors director WHERE director.id = :id")
	List<Movie> findByDirector(@Param("id") long id);

	@Override
	@Query(value = "SELECT movie FROM Movie movie JOIN movie.directors director WHERE director.name = :name")
	List<Movie> findByDirector(@Param("name") String name);

	@Query(value = "SELECT movie FROM Movie movie JOIN movie.kinds kind WHERE kind = :kind")
	List<Movie> findByKind(@Param("kind") Kind kind);

	@Query(value = "SELECT COUNT(movie.id) FROM Movie movie JOIN movie.kinds kind WHERE kind = :kind")
	long countByKinds(@Param("kind") Kind kind);

}
