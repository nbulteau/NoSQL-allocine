package fr.sii.formation.gwt.client.service;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import fr.sii.formation.gwt.shared.MovieFilter;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@RemoteServiceRelativePath("movieService")
public interface MovieRemoteService extends RemoteService {

	Movie findById(long id);

	List<Movie> findByTitle(String title);

	void saveMovie(Movie movie);

	Movie retrieveAndSave(long alloCineMovieId, boolean viewed);

	List<Movie> fetchMovies(int start, int length, MovieFilter movieFilter);

	long getMoviesCount(MovieFilter movieFilter);
}
