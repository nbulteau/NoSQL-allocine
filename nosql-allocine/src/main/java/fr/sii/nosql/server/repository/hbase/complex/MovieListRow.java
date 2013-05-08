package fr.sii.nosql.server.repository.hbase.complex;

import java.util.List;

import fr.sii.nosql.shared.buisiness.Movie;

public class MovieListRow {
	private List<Movie> movies;

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
}
