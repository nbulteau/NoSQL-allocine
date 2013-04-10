package fr.sii.formation.gwt.server.allocine.service;

import fr.sii.formation.gwt.server.allocine.buisiness.Movie;

public interface MovieMapperService {

	/**
	 * 
	 * @param alloCineMovie
	 * @return
	 * @throws ConvertException
	 */
	fr.sii.formation.gwt.shared.buisiness.Movie convertToBuisinessObject(Movie alloCineMovie) throws ConvertException;

}
