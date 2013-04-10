package fr.sii.nosql.server.allocine.service;

import fr.sii.nosql.server.allocine.buisiness.Movie;

public interface MovieMapperService {

	/**
	 * 
	 * @param alloCineMovie
	 * @return
	 * @throws ConvertException
	 */
	fr.sii.nosql.shared.buisiness.Movie convertToBuisinessObject(Movie alloCineMovie) throws ConvertException;

}
