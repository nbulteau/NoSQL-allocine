package fr.sii.nosql.server.service;

import fr.sii.nosql.shared.buisiness.Movie;

public interface AlloCineService {
	Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException;
}
