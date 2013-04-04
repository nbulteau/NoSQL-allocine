package fr.sii.formation.gwt.server.remoteservice;

import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.google.gwt.rpc.client.impl.RemoteException;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import fr.sii.formation.gwt.client.service.MovieRemoteService;
import fr.sii.formation.gwt.server.service.MovieService;
import fr.sii.formation.gwt.server.service.MovieServiceException;
import fr.sii.formation.gwt.shared.MovieFilter;
import fr.sii.formation.gwt.shared.buisiness.Movie;

public class MovieRemoteServiceImpl extends RemoteServiceServlet implements
		MovieRemoteService {

	private static final long serialVersionUID = 1L;

	private final static Logger LOGGER = LoggerFactory
			.getLogger(MovieRemoteServiceImpl.class);

	private WebApplicationContext webApplicationContext;

	public WebApplicationContext getWebApplicationContext() {
		return webApplicationContext;
	}

	@Override
	public void init(ServletConfig servletConfig) throws ServletException {
		super.init(servletConfig);
		webApplicationContext = WebApplicationContextUtils
				.getWebApplicationContext(servletConfig.getServletContext());
	}

	@Override
	public Movie findById(long id) {
		MovieService movieService = (MovieService) getWebApplicationContext()
				.getBean("movieService");
		LOGGER.debug("findById({})", id);

		return movieService.findById(id);
	}

	@Override
	public List<Movie> findByTitle(String title) {
		MovieService movieService = (MovieService) getWebApplicationContext()
				.getBean("movieService");
		LOGGER.debug("findByTitle({})", title);

		return movieService.findByTitle(title);
	}

	@Override
	public void saveMovie(Movie movie) throws RemoteException {
		MovieService movieService = (MovieService) getWebApplicationContext()
				.getBean("movieService");
		LOGGER.debug("saveMovie({})", movie);

		try {
			movieService.save(movie);
		} catch (MovieServiceException e) {
			throw new RemoteException(e);
		}
	}

	@Override
	public Movie retrieveAndSave(long alloCineMovieId, boolean viewed)
			throws RemoteException {
		Movie movie = null;
		MovieService movieService = (MovieService) getWebApplicationContext()
				.getBean("movieService");
		LOGGER.debug("retrieveAndSave({})", alloCineMovieId);

		try {
			movie = movieService.retrieveAndSave(alloCineMovieId, false);
		} catch (MovieServiceException e) {
			throw new RemoteException(e);
		}
		return movie;
	}

	@Override
	public List<Movie> fetchMovies(int start, int length,
			MovieFilter movieFilter) {
		MovieService movieService = (MovieService) getWebApplicationContext()
				.getBean("movieService");
		LOGGER.debug("fetchMovies({}, {})", start, length);

		return movieService.fetchMovies(start, length, movieFilter);
	}

	@Override
	public long getMoviesCount(MovieFilter movieFilter) {
		MovieService movieService = (MovieService) getWebApplicationContext()
				.getBean("movieService");
		LOGGER.debug("getMoviesCount()");

		return movieService.getMoviesCount(movieFilter);
	}
}
