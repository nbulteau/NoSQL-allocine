package fr.sii.nosql.server.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

public interface MovieRepository {

	Movie findById(Long id);

	Movie save(Movie movie);

	void delete(Movie movie);

	List<Movie> findAll(Sort sort);

	Page<Movie> findAll(Pageable pageable);

	boolean exists(Long id);

	long count();

	void delete(Long id);

	void deleteAll();

	List<Movie> findByTitle(String title);

	List<Movie> findByTitleLike(String string);

	List<Movie> findByActor(long id);

	List<Movie> findByActor(String name);

	List<Movie> findByDirector(long id);

	List<Movie> findByDirector(String name);

	List<Movie> findByKinds(Kind kind);

	// long countByKinds(Kind kind);

}
