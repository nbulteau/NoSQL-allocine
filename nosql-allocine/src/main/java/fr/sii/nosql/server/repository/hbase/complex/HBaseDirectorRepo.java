package fr.sii.nosql.server.repository.hbase.complex;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import dataaccess.hbase.repository.HBaseComplexDataRepository;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Profile("hbase")
@Repository
public class HBaseDirectorRepo extends HBaseComplexDataRepository<Long, Object, MovieListRow> {
	private CollectionView<Long, Long, Movie> movies;

	public void addMovie(Movie movie) {
		for (Person person : movie.getDirectors())
			movies.add(person.getId(), movie.getId(), movie);
	}

	List<Movie> getMovies(long dirId) {
		return get(dirId).getMovies();
	}
}
