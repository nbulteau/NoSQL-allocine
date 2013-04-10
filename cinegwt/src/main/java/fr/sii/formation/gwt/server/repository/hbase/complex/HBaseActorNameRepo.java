package fr.sii.formation.gwt.server.repository.hbase.complex;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import dataaccess.hbase.repository.HBaseComplexDataRepository;
import fr.sii.formation.gwt.shared.buisiness.Actor;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@Profile("hbase")
@Repository
public class HBaseActorNameRepo extends HBaseComplexDataRepository<String, Object, MovieListRow> {
	private CollectionView<String, Long, Movie> movies;

	public void addMovie(Movie movie) {
		for (Actor actor : movie.getActors())
			movies.add(actor.getPerson().getName(), movie.getId(), movie);
	}

	List<Movie> getMovies(String name) {
		return get(name).getMovies();
	}
}
