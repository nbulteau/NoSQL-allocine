package fr.sii.formation.gwt.server.allocine.service;

import fr.sii.formation.gwt.server.allocine.buisiness.AlloCineMovie;
import fr.sii.formation.gwt.shared.buisiness.Movie;

public interface MovieMapperService
{

	/**
	 * 
	 * @param alloCineMovie
	 * @return
	 * @throws ConvertException
	 */
	Movie convertToBuisinessObject(AlloCineMovie alloCineMovie) throws ConvertException;

}
