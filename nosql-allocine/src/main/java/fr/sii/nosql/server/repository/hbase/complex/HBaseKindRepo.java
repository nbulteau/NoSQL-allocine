package fr.sii.nosql.server.repository.hbase.complex;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import dataaccess.hbase.repository.HBaseComplexDataRepository;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("hbase")
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
