package fr.sii.formation.gwt.server.repository.hbase.complex;

import java.util.List;

import org.springframework.stereotype.Repository;

import dataaccess.hbase.repository.HBaseComplexDataRepository;
import fr.sii.formation.gwt.shared.buisiness.Movie;
import fr.sii.formation.gwt.shared.buisiness.Person;

@Repository
public class HBaseDirectorNameRepo extends HBaseComplexDataRepository<String, Object, MovieListRow> {
	private CollectionView<String, Long, Movie> movies;

	public void addMovie(Movie movie) {
		for (Person person : movie.getDirectors())
			movies.add(person.getName(), movie.getId(), movie);
	}

	List<Movie> getMovies(String name) {
		return get(name).getMovies();
	}
}
