package fr.sii.formation.gwt.server.repository.hbase.complex;

import java.util.List;

import org.springframework.stereotype.Repository;

import dataaccess.hbase.repository.HBaseComplexDataRepository;
import fr.sii.formation.gwt.shared.buisiness.Kind;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@Repository
public class HBaseKindRepo extends HBaseComplexDataRepository<String, Object, MovieListRow> {
	private CollectionView<String, Long, Movie> movies;

	public void addMovie(Movie movie) {
		for (Kind k : movie.getKinds())
			movies.add(k.name(), movie.getId(), movie);
	}

	List<Movie> getMovies(String name) {
		return get(name).getMovies();
	}

	long count(String name) {
		return movies.count(name);
	}
}
