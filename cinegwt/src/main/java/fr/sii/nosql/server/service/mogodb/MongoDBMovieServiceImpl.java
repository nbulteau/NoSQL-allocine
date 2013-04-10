package fr.sii.nosql.server.service.mogodb;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.MovieHelper;
import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.mongodb.MongoDBMovieRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBPhotoRepository;
import fr.sii.nosql.server.repository.mongodb.MongoDBPosterRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.MovieFilter;
import fr.sii.nosql.shared.buisiness.Actor;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;
import fr.sii.nosql.shared.buisiness.Photo;
import fr.sii.nosql.shared.buisiness.Poster;

@Profile("mongodb")
@Service("mongoDBMovieService")
public class MongoDBMovieServiceImpl implements MovieService {

	private final static Logger LOGGER = LoggerFactory.getLogger(MongoDBMovieServiceImpl.class);

	private final MongoDBMovieRepository mongoDBMovieRepository;

	private final MongoDBPosterRepository mongoDBPosterRepository;

	private final MongoDBPhotoRepository mongoDBPhotoRepository;

	private final AlloCineService alloCineService;

	@Autowired
	public MongoDBMovieServiceImpl(MongoDBMovieRepository mongoDBMovieRepository, MongoDBPosterRepository mongoDBPosterRepository,
			MongoDBPhotoRepository mongoDBPhotoRepository, AlloCineService alloCineService) {
		super();
		this.mongoDBMovieRepository = mongoDBMovieRepository;
		this.mongoDBPosterRepository = mongoDBPosterRepository;
		this.mongoDBPhotoRepository = mongoDBPhotoRepository;
		this.alloCineService = alloCineService;
	}

	@Override
	@Transactional
	public void save(Movie movie) throws MovieServiceException {
		LOGGER.info("saveMovie : {}", movie.getTitle());

		if (movie.getId() == 0) {
			throw new MovieServiceException("movie id == 0");
		}
		// poster
		try {
			long posterId = movie.getId();
			if (!mongoDBPosterRepository.exists(posterId) && movie.getPosterHref() != null) {
				byte[] poster = MovieHelper.downloadPicture(movie.getPosterHref());
				mongoDBPosterRepository.save(new Poster(posterId, poster));
			}
		} catch (IOException e) {
			LOGGER.error("Unable to retrieve poster for {} : {}", movie.getTitle(), e.getMessage());
		}

		List<Photo> photoListToSave = new ArrayList<Photo>();
		// actors picture
		for (Actor actor : movie.getActors()) {
			Person person = actor.getPerson();
			Long pictureId = person.getId();
			if (!mongoDBPhotoRepository.exists(pictureId) && person.getPictureHref() != null) {
				try {
					photoListToSave.add(new Photo(pictureId, MovieHelper.downloadPicture(person.getPictureHref())));
				} catch (IOException e) {
					LOGGER.error("Unable to retrieve photo for {} : {}", person.getName(), e.getMessage());
				}
			}
		}
		// directors picture
		for (Person director : movie.getDirectors()) {
			long pictureId = director.getId();
			if (!mongoDBPhotoRepository.exists(pictureId) && director.getPictureHref() != null) {
				try {
					photoListToSave.add(new Photo(pictureId, MovieHelper.downloadPicture(director.getPictureHref())));
				} catch (IOException e) {
					LOGGER.error("Unable to retrieve photo for {} : {}", director.getName(), e.getMessage());
				}
			}
		}
		// insert pictures into db
		mongoDBPhotoRepository.save(photoListToSave);

		// insert movie into db
		mongoDBMovieRepository.save(movie);
	}

	@Override
	public boolean exists(long id) {
		return mongoDBMovieRepository.exists(id);
	}

	@Override
	public Movie findById(long id) {
		return mongoDBMovieRepository.findOne(id);
	}

	@Override
	public List<Movie> findAll() {
		return mongoDBMovieRepository.findAll();
	}

	@Override
	public List<Movie> findByTitle(String title) {
		return mongoDBMovieRepository.findByTitle(title);
	}

	@Override
	public List<Movie> findByTitleLike(String title) {
		return mongoDBMovieRepository.findByTitleLike(title);
	}

	@Override
	public List<Movie> findByActor(long id) {
		return mongoDBMovieRepository.findByActor(id);
	}

	@Override
	public List<Movie> findByActor(String name) {
		return mongoDBMovieRepository.findByActor(name);
	}

	@Override
	public List<Movie> findByDirector(long id) {
		return mongoDBMovieRepository.findByDirector(id);
	}

	@Override
	public List<Movie> findByDirector(String name) {
		return mongoDBMovieRepository.findByDirector(name);
	}

	@Override
	public Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException {
		Movie movie = alloCineService.retrieveMovie(alloCineMovieId);
		if (movie != null) {
			movie.setViewed(viewed);
			// save the movie
			save(movie);
		}
		return movie;
	}

	@Override
	public List<Movie> fetchMovies(int start, int length, MovieFilter movieFilter) {
		// TODO : implements fetchMovies with filter

		Page<Movie> page = mongoDBMovieRepository.findAll(new PageRequest(start, length));

		return new ArrayList<Movie>(page.getContent());
	}

	@Override
	public long getMoviesCount(MovieFilter movieFilter) {
		// TODO : implements getMoviesCount with filter
		return mongoDBMovieRepository.count();
	}
}
