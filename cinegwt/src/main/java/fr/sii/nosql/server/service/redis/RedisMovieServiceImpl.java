package fr.sii.nosql.server.service.redis;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.repository.redis.RedisMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("redis")
@Service("redisMovieService")
public class RedisMovieServiceImpl implements MovieService {

	private final static Logger LOGGER = LoggerFactory.getLogger(RedisMovieServiceImpl.class);

	private final RedisMovieRepository redisMovieRepository;

	@Autowired
	public RedisMovieServiceImpl(RedisMovieRepository redisMovieRepository) {
		super();
		this.redisMovieRepository = redisMovieRepository;
	}

	@Override
	public boolean exists(long id) {
		return redisMovieRepository.exists(id);
	}

	@Override
	public Movie findById(long id) {
		return redisMovieRepository.findOne(id);
	}

	@Override
	public List<Movie> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByTitleLike(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByActor(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByActor(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByDirector(long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Movie> findByDirector(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public void save(Movie movie) throws MovieServiceException {
		LOGGER.info("saveMovie : {}", movie.getTitle());

		if (movie.getId() == 0) {
			throw new MovieServiceException("movie id == 0");
		}

		// // poster
		// try {
		// long posterId = movie.getId();
		// if (!redisPosterRepository.exists(posterId) && movie.getPosterHref()
		// != null) {
		// byte[] poster = MovieHelper.downloadPicture(movie.getPosterHref());
		// redisPosterRepository.save(new AlloCinePoster(posterId, poster));
		// }
		// } catch (IOException e) {
		// LOGGER.error("Unable to retrieve poster for {} : {}",
		// movie.getTitle(), e.getMessage());
		// }
		//
		// List<Photo> photoListToSave = new ArrayList<Photo>();
		// // actors picture
		// for (AlloCineCastMember actor : movie.getActors()) {
		// AlloCinePerson person = actor.getPerson();
		// Long pictureId = person.getId();
		// if (!redisPhotoRepository.exists(pictureId) &&
		// person.getPictureHref() != null) {
		// try {
		// photoListToSave.add(new Photo(pictureId,
		// MovieHelper.downloadPicture(person.getPictureHref())));
		// } catch (IOException e) {
		// LOGGER.error("Unable to retrieve photo for {} : {}",
		// person.getName(), e.getMessage());
		// }
		// }
		// }
		// // directors picture
		// for (AlloCinePerson director : movie.getDirectors()) {
		// long pictureId = director.getId();
		// if (!redisPhotoRepository.exists(pictureId) &&
		// director.getPictureHref() != null) {
		// try {
		// photoListToSave.add(new Photo(pictureId,
		// MovieHelper.downloadPicture(director.getPictureHref())));
		// } catch (IOException e) {
		// LOGGER.error("Unable to retrieve photo for {} : {}",
		// director.getName(), e.getMessage());
		// }
		// }
		// }
		// // insert pictures into db
		// redisPhotoRepository.save(photoListToSave);

		// insert movie into db
		redisMovieRepository.save(movie);

	}

	@Override
	public Movie retrieveAndSave(long alloCineMovieId, boolean viewed) throws MovieServiceException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long countByKind(Kind kind) {
		// TODO Auto-generated method stub
		return 0;
	}

}
