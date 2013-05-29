package fr.sii.nosql.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.MovieHelper;
import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FilePhotoRepository;
import fr.sii.nosql.server.repository.file.FilePosterRepository;
import fr.sii.nosql.server.repository.file.Picture;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

public abstract class MovieServiceImpl {

	protected final static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

	protected MovieRepository movieRepository;

	protected final FilePosterRepository filePosterRepository;

	protected final FilePhotoRepository filePhotoRepository;

	protected final AlloCineService alloCineService;

	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Autowired
	public MovieServiceImpl(AlloCineService alloCineService, FilePosterRepository filePosterRepository, FilePhotoRepository filePhotoRepository) {
		super();
		this.alloCineService = alloCineService;
		this.filePosterRepository = filePosterRepository;
		this.filePhotoRepository = filePhotoRepository;
	}

	@Transactional
	public void save(Movie movie, boolean isDownloadPictures) throws MovieServiceException {
		LOGGER.info("save : {} : {}", movie.getId(), movie.getTitle());

		invariant();

		if (movie.getId() == 0) {
			throw new MovieServiceException("movie id == 0");
		}

		if (isDownloadPictures) {
			// poster
			savePosters(movie);
			// actors and directiors picture
			savePictures(movie);
		}

		// insert movie into db
		movieRepository.save(movie);
	}

	public boolean exists(long id) throws MovieServiceException {
		invariant();
		return movieRepository.exists(id);
	}

	public Movie findById(long id) throws MovieServiceException {
		invariant();
		return movieRepository.findById(id);
	}

	public List<Movie> findByTitle(String title) throws MovieServiceException {
		invariant();
		return movieRepository.findByTitle(title);
	}

	public List<Movie> findByTitleLike(String title) throws MovieServiceException {
		invariant();
		return movieRepository.findByTitleLike(title);
	}

	public List<Movie> findByActor(long id) throws MovieServiceException {
		invariant();
		return movieRepository.findByActor(id);
	}

	public List<Movie> findByActor(String name) throws MovieServiceException {
		invariant();
		return movieRepository.findByActor(name);
	}

	public List<Movie> findByDirector(long id) throws MovieServiceException {
		invariant();
		return movieRepository.findByDirector(id);
	}

	public List<Movie> findByDirector(String name) throws MovieServiceException {
		invariant();
		return movieRepository.findByDirector(name);
	}

	public Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException {
		Movie movie = alloCineService.retrieveMovie(alloCineMovieId);
		if (movie != null) {
			movie.setViewed(viewed);
			// save the movie
			save(movie, true);
		}
		return movie;
	}

	public List<Movie> findByKinds(Kind kind) {
		// TODO Auto-generated method stub
		return null;
	}

	public long countByKind(Kind kind) throws MovieServiceException {
		invariant();
		// TODO : implements getMoviesCount with filter
		return movieRepository.count();
	}

	protected void savePictures(Movie movie) {
		List<Picture> PictureListToSave = new ArrayList<Picture>();
		// actors picture
		for (CastMember castMember : movie.getCastMembers()) {
			Person person = castMember.getPerson();
			Long pictureId = person.getId();
			if (!filePhotoRepository.exists(pictureId) && person.getPictureHref() != null) {
				try {
					PictureListToSave.add(new Picture(pictureId, MovieHelper.downloadPicture(person.getPictureHref())));
				} catch (IOException e) {
					LOGGER.error("Unable to retrieve Picture for {} : {}", person.getName(), e.getMessage());
				}
			}
		}

		// directors picture
		for (Person director : movie.getDirectors()) {
			long pictureId = director.getId();
			if (!filePhotoRepository.exists(pictureId) && director.getPictureHref() != null) {
				try {
					PictureListToSave.add(new Picture(pictureId, MovieHelper.downloadPicture(director.getPictureHref())));
				} catch (IOException e) {
					LOGGER.error("Unable to retrieve Picture for {} : {}", director.getName(), e.getMessage());
				}
			}
		}

		// insert pictures into db
		filePhotoRepository.save(PictureListToSave);
	}

	protected void savePosters(Movie movie) {
		try {
			long posterId = movie.getId();
			if (!filePosterRepository.exists(posterId) && movie.getPosterHref() != null) {
				byte[] poster = MovieHelper.downloadPicture(movie.getPosterHref());
				filePosterRepository.save(new Picture(posterId, poster));
			}
		} catch (IOException e) {
			LOGGER.error("Unable to retrieve poster for {} : {}", movie.getTitle(), e.getMessage());
		}
	}

	protected void invariant() throws MovieServiceException {
		if (movieRepository == null) {
			throw new MovieServiceException("movieRepository not initialised");
		}
	}

}