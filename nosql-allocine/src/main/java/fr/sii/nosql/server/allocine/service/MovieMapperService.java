package fr.sii.nosql.server.allocine.service;

import fr.sii.nosql.server.allocine.buisiness.AlloCineMovie;
import fr.sii.nosql.shared.buisiness.Movie;

public interface MovieMapperService {

	/**
	 * 
	 * @param alloCineMovie
	 * @return
	 * @throws ConvertException
	 */
	Movie convertToBuisinessObject(AlloCineMovie alloCineMovie) throws ConvertException;

}
