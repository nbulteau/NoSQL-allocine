package fr.sii.nosql.server.repository.jpa;

import java.util.List;

public interface PersonneDAO extends GenericDAO<Personne, Long> {
	List<Personne> findAllSorted(int firstResult, int maxResults, String propertyOrder, String order, String filtreNomParameter);

	long countNbElementsInTable(String filtreNomParameter);

}
