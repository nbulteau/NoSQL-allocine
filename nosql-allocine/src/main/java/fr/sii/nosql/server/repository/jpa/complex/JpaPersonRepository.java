package fr.sii.nosql.server.repository.jpa.complex;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Transactional
public interface JpaPersonRepository extends JpaRepository<Person, Long> {

	List<Movie> findByName(String name);

	List<Movie> findByNameLike(String name);
}
