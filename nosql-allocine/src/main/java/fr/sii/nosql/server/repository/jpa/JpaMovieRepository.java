package fr.sii.nosql.server.repository.jpa;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Transactional
public interface JpaMovieRepository extends JpaRepository<Movie, Long>, MovieRepository {
	List<Movie> findByTitle(String title);

	List<Movie> findByTitleLike(String string);

	@Query(value = "SELECT movie FROM Movie movie JOIN movie.castMembers castMember JOIN castMember.person person WHERE person.id = :id")
	List<Movie> findByActor(@Param("id") long id);

	@Query(value = "SELECT movie FROM Movie movie JOIN movie.castMembers castMember JOIN castMember.person person WHERE person.name = :name")
	List<Movie> findByActor(@Param("name") String name);

	@Query(value = "SELECT movie FROM Movie movie JOIN movie.directors director WHERE director.id = :id")
	List<Movie> findByDirector(@Param("id") long id);

	@Query(value = "SELECT movie FROM Movie movie JOIN movie.directors director WHERE director.name = :name")
	List<Movie> findByDirector(@Param("name") String name);

	@Query(value = "SELECT movie FROM Movie movie JOIN movie.kinds kind WHERE kind = :kind")
	List<Movie> findByKind(@Param("kind") Kind kind);

	@Query(value = "SELECT COUNT(movie.id) FROM Movie movie JOIN movie.kinds kind WHERE kind = :kind")
	long countByKind(@Param("kind") Kind kind);

}
