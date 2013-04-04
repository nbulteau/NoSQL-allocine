package fr.sii.formation.gwt.client.service;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import fr.sii.formation.gwt.shared.MovieFilter;
import fr.sii.formation.gwt.shared.buisiness.Movie;

public interface MovieRemoteServiceAsync {

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see fr.sii.formation.gwt.client.service.MovieRemoteService
	 */
	void findById(long p0, AsyncCallback<fr.sii.formation.gwt.shared.buisiness.Movie> callback);

	/**
	 * GWT-RPC service asynchronous (client-side) interface
	 * 
	 * @see fr.sii.formation.gwt.client.service.MovieRemoteService
	 */
	void findByTitle(java.lang.String p0, AsyncCallback<java.util.List<Movie>> callback);

	void saveMovie(Movie movie, AsyncCallback<Void> callback);

	void retrieveAndSave(long alloCineMovieId, boolean viewed, AsyncCallback<Movie> callback);

	void fetchMovies(int start, int length, MovieFilter movieFilter, AsyncCallback<List<Movie>> callback);

	void getMoviesCount(MovieFilter movieFilter, AsyncCallback<Long> callback);

	/**
	 * Utility class to get the RPC Async interface from client-side code
	 */
	public static final class Util {
		private static MovieRemoteServiceAsync instance;

		public static final MovieRemoteServiceAsync getInstance() {
			if (instance == null) {
				instance = (MovieRemoteServiceAsync) GWT.create(MovieRemoteService.class);
				ServiceDefTarget target = (ServiceDefTarget) instance;
				target.setServiceEntryPoint(GWT.getModuleBaseURL() + "MovieRemoteService");
			}
			return instance;
		}

		private Util() {
			// Utility class should not be instanciated
		}
	}
}
