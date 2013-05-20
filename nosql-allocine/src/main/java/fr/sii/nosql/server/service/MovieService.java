package fr.sii.nosql.server.service;

import java.util.List;

import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

public interface MovieService {

	boolean exists(long id) throws MovieServiceException;

	Movie findById(long id) throws MovieServiceException;

	List<Movie> findByTitle(String title) throws MovieServiceException;

	List<Movie> findByTitleLike(String string) throws MovieServiceException;

	List<Movie> findByActor(long id) throws MovieServiceException;

	List<Movie> findByActor(String name) throws MovieServiceException;

	List<Movie> findByDirector(long id) throws MovieServiceException;

	List<Movie> findByDirector(String name) throws MovieServiceException;

	void save(Movie movie, boolean isDownloadPictures) throws MovieServiceException;

	Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException;

	long countByKind(Kind kind) throws MovieServiceException;

	void setMovieRepository(MovieRepository jpaRepository);
}
