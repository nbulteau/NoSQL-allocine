package fr.sii.formation.gwt.server.service;

public interface PopulateService {

	public abstract void populate(String inputFileName)
			throws MovieServiceException;

}