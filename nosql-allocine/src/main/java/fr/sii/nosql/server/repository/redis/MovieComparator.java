package fr.sii.nosql.server.repository.redis;

import java.util.Comparator;

import fr.sii.nosql.shared.buisiness.Movie;

public class MovieComparator implements Comparator<Movie> {
	@Override
	public int compare(Movie movie1, Movie movie2) {
		// this optimization is usually worthwhile, and can
		// always be added
		if (movie1 == movie2) {
			return 0;
		}
		int comparison = movie1.getTitle().compareTo(movie2.getTitle());
		if (comparison != 0) {
			return comparison;
		}
		return 0;
	}
}