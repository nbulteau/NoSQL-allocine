package fr.sii.formation.server.service;

import java.util.List;

import org.junit.Assert;

import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

public class MovieServiceTestImpl extends SimpleMovieServiceTestImpl implements
		MovieServiceTest {

	public MovieServiceTestImpl(FileMovieRepository fileMovieRepository,
			MovieService movieService) {
		super(fileMovieRepository, movieService);
	}

	@Override
	public void findByActor() throws MovieServiceException {
		String[] names = { "Bérénice Bejo", "Orlando Bloom",
				"Emmanuelle Seigner", "Joaquin Phoenix", "Tom Hanks",
				"Liam Neeson", "Brad Pitt", "Al Pacino", "Morgan Freeman",
				"Kevin Spacey", "Gary Oldman", "Emma Watson", "Harrison Ford",
				"Johnny Depp", "Winona Ryder" };
		long deb = System.currentTimeMillis();
		List<Movie> movies = null;
		int sum = 0;
		for (String name : names) {
			movies = movieService.findByActor(name);
			sum += movies.size();
		}
		long end = System.currentTimeMillis();
		Assert.assertEquals(743, sum);

		System.out.println("findByActor movies in " + (end - deb)
				/ names.length + " ms");
	}

	@Override
	public void allTests4Times() throws InterruptedException,
			MovieServiceException {
		for (int i = 0; i < 5; i++) {
			populateFromFileRepository();
			findByIdTest();
			findByActor();
		}
	}

}
