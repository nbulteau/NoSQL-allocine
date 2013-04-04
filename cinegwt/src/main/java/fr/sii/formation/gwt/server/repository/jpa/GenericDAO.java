package fr.sii.formation.gwt.server.repository.jpa;

import java.io.Serializable;
import java.util.List;

public interface GenericDAO<T, ID extends Serializable>
{
	T loadById(ID id);

	void persist(T entity);

	void update(T entity);

	void delete(T entity);

	void detach(T entity);

	List<T> loadAll();
}
