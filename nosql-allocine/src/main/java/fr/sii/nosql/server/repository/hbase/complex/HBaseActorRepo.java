package fr.sii.nosql.server.repository.hbase.complex;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import dataaccess.hbase.repository.HBaseComplexDataRepository;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("hbase")
@Repository
public class HBaseActorRepo extends HBaseComplexDataRepository<Long, Object, MovieListRow> {
	private CollectionView<Long, Long, Movie> movies;

	public void addMovie(Movie movie) {
		for (CastMember castMember : movie.getCastMembers())
			movies.add(castMember.getPerson().getId(), movie.getId(), movie);
	}

	List<Movie> getMovies(long actorId) {
		return get(actorId).getMovies();
	}
}