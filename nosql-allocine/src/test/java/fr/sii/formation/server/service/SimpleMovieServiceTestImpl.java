package fr.sii.formation.server.service;

import fr.sii.nosql.server.repository.file.FileMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Movie;

public class SimpleMovieServiceTestImpl implements SimpleMovieServiceTest {

	private FileMovieRepository fileMovieRepository;

	protected MovieService movieService;

	public SimpleMovieServiceTestImpl(FileMovieRepository fileMovieRepository,
			MovieService movieService) {
		super();
		this.fileMovieRepository = fileMovieRepository;
		this.movieService = movieService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.formation.server.service.SimpleMovieServiceTest#insertMovie()
	 */
	@Override
	public void insertMovie() throws MovieServiceException {
		Movie movie = fileMovieRepository.findById(62l);
		movieService.save(movie, true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.sii.formation.server.service.SimpleMovieServiceTest#
	 * populateFromFileRepository()
	 */
	@Override
	public void populateFromFileRepository() throws InterruptedException,
			MovieServiceException {

		Iterable<Movie> iterable = fileMovieRepository.all();

		long index = 0;
		long deb = System.currentTimeMillis();
		for (Movie movie : iterable) {
			movieService.save(movie, false);
			index++;
		}

		long end = System.currentTimeMillis();

		System.out.println("populate " + index + " movies in " + (end - deb)
				+ " ms");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.sii.formation.server.service.SimpleMovieServiceTest#findByIdTest()
	 */
	@Override
	public void findByIdTest() throws MovieServiceException {
		Long[] ids = { 5091l, 203l, 42729l, 50072l, 99876l, 139957l, 185220l,
				197774l, 205895l, 221092l };
		long deb = System.currentTimeMillis();
		for (Long id : ids) {
			movieService.findById(id);
		}
		long end = System.currentTimeMillis();

		System.out.println("findById movies in " + (end - deb) / 10 + " ms");
	}
}
