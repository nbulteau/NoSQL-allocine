package fr.sii.nosql.server.repository.jpa;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public abstract class GenericDAOWithJPA<T, ID extends Serializable> implements GenericDAO<T, ID> {

	protected Class<T> persistentClass;

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	public GenericDAOWithJPA() {
		this.persistentClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	public Class<T> getPersistentClass() {
		return persistentClass;
	}

	@Override
	public T loadById(ID id) {
		return entityManager.find(persistentClass, id);
	}

	@Override
	public void persist(T entity) {
		entityManager.persist(entity);
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> loadAll() {
		return entityManager.createQuery("Select t from " + persistentClass.getSimpleName() + " t").getResultList();

	}

	@Override
	public void detach(T entity) {
		entityManager.detach(entity);
	}

}
