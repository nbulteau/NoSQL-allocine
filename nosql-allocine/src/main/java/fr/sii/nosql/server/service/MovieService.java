package fr.sii.nosql.server.service;

import java.util.List;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

public interface MovieService {

	boolean exists(long id);

	Movie findById(long id);

	List<Movie> findByTitle(String title);

	List<Movie> findByTitleLike(String string);

	List<Movie> findByActor(long id);

	List<Movie> findByActor(String name);

	List<Movie> findByDirector(long id);

	List<Movie> findByDirector(String name);

	void save(Movie movie, boolean isDownloadPictures) throws MovieServiceException;

	Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException;

	long countByKind(Kind kind);

	void setMovieRepository(MovieRepository jpaRepository);
}
