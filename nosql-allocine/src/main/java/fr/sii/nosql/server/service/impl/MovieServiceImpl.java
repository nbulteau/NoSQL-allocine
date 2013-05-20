package fr.sii.nosql.server.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.MovieHelper;
import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.MovieRepository;
import fr.sii.nosql.server.repository.file.FilePhotoRepository;
import fr.sii.nosql.server.repository.file.FilePosterRepository;
import fr.sii.nosql.server.repository.file.Picture;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Service("movieService")
public class MovieServiceImpl implements MovieService {

	private final static Logger LOGGER = LoggerFactory.getLogger(MovieServiceImpl.class);

	private MovieRepository movieRepository;

	private final FilePosterRepository filePosterRepository;

	private final FilePhotoRepository filePhotoRepository;

	private final AlloCineService alloCineService;

	@Autowired
	public MovieServiceImpl(AlloCineService alloCineService, FilePosterRepository filePosterRepository, FilePhotoRepository filePhotoRepository) {
		super();
		this.alloCineService = alloCineService;
		this.filePosterRepository = filePosterRepository;
		this.filePhotoRepository = filePhotoRepository;
	}

	/**
	 * Must be called before
	 */
	@Override
	public void setMovieRepository(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	@Transactional
	public void save(Movie movie, boolean isDownloadPictures) throws MovieServiceException {
		LOGGER.info("save : {}", movie.getTitle());

		invariant();

		if (movie.getId() == 0) {
			throw new MovieServiceException("movie id == 0");
		}

		// poster
		if (isDownloadPictures) {
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

		// actors and directiors picture
		if (isDownloadPictures) {
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

		// insert movie into db
		movieRepository.save(movie);
	}

	@Override
	public boolean exists(long id) throws MovieServiceException {
		invariant();
		return movieRepository.exists(id);
	}

	@Override
	public Movie findById(long id) throws MovieServiceException {
		invariant();
		return movieRepository.findById(id);
	}

	@Override
	public List<Movie> findByTitle(String title) throws MovieServiceException {
		invariant();
		return movieRepository.findByTitle(title);
	}

	@Override
	public List<Movie> findByTitleLike(String title) throws MovieServiceException {
		invariant();
		return movieRepository.findByTitleLike(title);
	}

	@Override
	public List<Movie> findByActor(long id) throws MovieServiceException {
		invariant();
		return movieRepository.findByActor(id);
	}

	@Override
	public List<Movie> findByActor(String name) throws MovieServiceException {
		invariant();
		return movieRepository.findByActor(name);
	}

	@Override
	public List<Movie> findByDirector(long id) throws MovieServiceException {
		invariant();
		return movieRepository.findByDirector(id);
	}

	@Override
	public List<Movie> findByDirector(String name) throws MovieServiceException {
		invariant();
		return movieRepository.findByDirector(name);
	}

	@Override
	public Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException {
		Movie movie = alloCineService.retrieveMovie(alloCineMovieId);
		if (movie != null) {
			movie.setViewed(viewed);
			// save the movie
			save(movie, true);
		}
		return movie;
	}

	@Override
	public long countByKind(Kind kind) throws MovieServiceException {
		invariant();
		// TODO : implements getMoviesCount with filter
		return movieRepository.count();
	}

	private void invariant() throws MovieServiceException {
		if (movieRepository == null) {
			throw new MovieServiceException("movieRepository not initialised");
		}
	}

}
