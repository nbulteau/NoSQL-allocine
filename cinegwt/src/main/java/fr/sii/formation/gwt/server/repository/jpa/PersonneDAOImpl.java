package fr.sii.formation.gwt.server.repository.jpa;

import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("jpa")
@Repository("personneDAO")
public class PersonneDAOImpl extends GenericDAOWithJPA<Personne, Long> implements PersonneDAO {

	@Override
	public List<Personne> findAllSorted(int firstResult, int maxResults, String propertyOrder, String order, String filtreNomParameter) {

		Metamodel metamodel = entityManager.getMetamodel();
		EntityType<Personne> Personne_ = metamodel.entity(Personne.class);

		CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Personne> criteriaQuery = criteriaBuilder.createQuery(Personne.class);

		Root<Personne> personne = criteriaQuery.from(Personne.class);

		if (filtreNomParameter != null) {
			Predicate condition = criteriaBuilder.like(personne.get(Personne_.getSingularAttribute("nom", String.class)), filtreNomParameter + "%");
			criteriaQuery.where(condition);
		}

		if (propertyOrder != null && order != null) {
			if (order.equalsIgnoreCase("asc")) {
				criteriaQuery.orderBy(criteriaBuilder.asc(personne.get(Personne_.getSingularAttribute(propertyOrder, String.class))));
			}
			if (order.equalsIgnoreCase("desc")) {
				criteriaQuery.orderBy(criteriaBuilder.desc(personne.get(Personne_.getSingularAttribute(propertyOrder, String.class))));
			}
		}

		TypedQuery<Personne> typedQuery = entityManager.createQuery(criteriaQuery);

		typedQuery.setMaxResults(maxResults);
		typedQuery.setFirstResult(firstResult);

		return typedQuery.getResultList();
	}

	@Override
	public long countNbElementsInTable(String filtreNomParameter) {
		Query query = null;
		String stringQuery = "SELECT COUNT(id) FROM Personne p ";

		if (filtreNomParameter != null) {
			stringQuery += "WHERE p.nom LIKE :nom";
		}

		query = entityManager.createQuery(stringQuery);
		// query.setCacheable(true);

		if (filtreNomParameter != null) {
			query.setParameter("nom", filtreNomParameter + "%");
		}

		return ((Long) query.getSingleResult()).longValue();
	}

}
