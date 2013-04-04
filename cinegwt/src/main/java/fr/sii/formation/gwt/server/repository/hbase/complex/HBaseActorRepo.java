package fr.sii.formation.gwt.server.repository.hbase.complex;

import java.util.List;

import org.springframework.stereotype.Repository;

import dataaccess.hbase.repository.HBaseComplexDataRepository;
import fr.sii.formation.gwt.shared.buisiness.Actor;
import fr.sii.formation.gwt.shared.buisiness.Movie;

@Repository
public class HBaseActorRepo extends HBaseComplexDataRepository<Long, Object, MovieListRow> {
	private CollectionView<Long, Long, Movie> movies;

	public void addMovie(Movie movie) {
		for (Actor actor : movie.getActors())
			movies.add(actor.getPerson().getId(), movie.getId(), movie);
	}

	List<Movie> getMovies(long actorId) {
		return get(actorId).getMovies();
	}
}
