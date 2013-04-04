package fr.sii.formation.gwt.server.allocine.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.sii.formation.gwt.server.allocine.buisiness.AlloCineMovie;
import fr.sii.formation.gwt.server.allocine.repository.AlloCineRepository;
import fr.sii.formation.gwt.server.allocine.repository.RetrieveException;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@Service
public class AlloCineServiceImpl implements AlloCineService {
	private final static Logger LOGGER = LoggerFactory
			.getLogger(AlloCineServiceImpl.class);

	private final MovieMapperService movieMapperService;

	private final AlloCineRepository alloCineRepository;

	@Autowired
	public AlloCineServiceImpl(MovieMapperService movieMapper,
			AlloCineRepository alloCineRepository) {
		super();
		this.movieMapperService = movieMapper;
		this.alloCineRepository = alloCineRepository;
	}

	/**
	 * @see fr.sii.formation.gwt.server.allocine.service.AlloCineService#retrieveMovie
	 *      (long)
	 */
	@Override
	public Movie retrieveMovie(long idMovie) {
		Movie movie = null;
		try {
			AlloCineMovie alloCineMovie = alloCineRepository
					.retrieveMovie(idMovie);
			movie = movieMapperService.convertToBuisinessObject(alloCineMovie);
		} catch (RetrieveException e) {
			LOGGER.error("Retrieve movie problem with movie id {} : {} ",
					idMovie);
		} catch (ConvertException e) {
			LOGGER.error("Convert movie problem with movie id {} : {} ",
					idMovie);
		}

		return movie;
	}

}
