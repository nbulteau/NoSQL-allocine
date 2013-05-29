package fr.sii.formation.server.service;

import java.util.List;

import org.junit.Assert;

import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

public class SimpleMovieServiceTestImpl implements SimpleMovieServiceTest {

	private final FileMovieRepository fileMovieRepository;

	protected MovieService movieService;

	public SimpleMovieServiceTestImpl(FileMovieRepository fileMovieRepository, MovieService movieService) {
		super();
		this.fileMovieRepository = fileMovieRepository;
		this.movieService = movieService;
	}

	@Override
	public void insertMovie() throws MovieServiceException {
		Movie movie = fileMovieRepository.findById(62l);
		movieService.save(movie, true);
	}

	@Override
	public void populateFromFileRepository() throws InterruptedException, MovieServiceException {
		System.out.println("populateFromFileRepository");

		Iterable<Movie> iterable = fileMovieRepository.all();

		long index = 0;
		long deb = System.currentTimeMillis();
		for (Movie movie : iterable) {
			movieService.save(movie, false);
			index++;
		}

		long end = System.currentTimeMillis();

		System.out.println(" => populate " + index + " movies in " + (end - deb) + " ms");
	}

	@Override
	public void findMovieById() throws MovieServiceException {
		System.out.println("findByIdTest");

		Long[] ids = { 5091l, 203l, 42729l, 50072l, 99876l, 139957l, 185220l, 197774l, 205895l, 221092l };
		long deb = System.currentTimeMillis();
		for (Long id : ids) {
			movieService.findById(id);
		}
		long end = System.currentTimeMillis();

		System.out.println(" => findById movies in " + (end - deb) / ids.length + " ms");
	}

	@Override
	public void findMoviesByTitle() throws MovieServiceException {
		System.out.println("findByTitle");

		String[] titles = { "Alien, le huitième passager", "La Guerre des boutons" };
		long deb = System.currentTimeMillis();
		List<Movie> movies = null;
		int sum = 0;
		for (String title : titles) {
			movies = movieService.findByTitle(title);
			sum += movies.size();
		}
		long end = System.currentTimeMillis();
		Assert.assertEquals(743, sum);

		System.out.println(" => findByTitle movies in " + (end - deb) / titles.length + " ms");
	}

	@Override
	public void findMoviesByTitleLike() throws MovieServiceException {
		System.out.println("findByTitleLike");

		String[] titles = { "Alien, le huitième", "La Guerre des" };
		long deb = System.currentTimeMillis();
		List<Movie> movies = null;
		int sum = 0;
		for (String title : titles) {
			movies = movieService.findByTitle(title);
			sum += movies.size();
		}
		long end = System.currentTimeMillis();
		Assert.assertEquals(743, sum);

		System.out.println(" => findByTitleLike movies in " + (end - deb) / titles.length + " ms");
	}

}
