package fr.sii.nosql.server.service.jpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.jpa.JpaMovieRepository;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;

@Profile("jpa")
@Service("jpaMovieService")
public class JPAMovieServiceImpl implements MovieService {

	private final static Logger LOGGER = LoggerFactory.getLogger(JPAMovieServiceImpl.class);

	private final JpaMovieRepository jpaMovieRepository;

	private final AlloCineService alloCineService;

	@Autowired(required = true)
	public JPAMovieServiceImpl(JpaMovieRepository jpaMovieRepository, AlloCineService alloCineService) {
		super();
		this.jpaMovieRepository = jpaMovieRepository;
		this.alloCineService = alloCineService;
	}

	@Override
	public boolean exists(long id) {
		return jpaMovieRepository.exists(id);
	};

	@Override
	@Transactional(readOnly = true)
	public Movie findById(long id) {
		return jpaMovieRepository.findOne(id);
	}

	@Override
	public List<Movie> findAll() {
		return Lists.newArrayList(jpaMovieRepository.findAll());
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByTitle(String title) {
		return jpaMovieRepository.findByTitle(title);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByTitleLike(String title) {
		return jpaMovieRepository.findByTitleLike(title);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByActor(long id) {
		return jpaMovieRepository.findByActor(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByActor(String name) {
		return jpaMovieRepository.findByActor(name);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByDirector(long id) {
		return jpaMovieRepository.findByDirector(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByDirector(String name) {
		return jpaMovieRepository.findByDirector(name);
	}

	@Override
	@Transactional
	public void save(Movie movie) throws MovieServiceException {
		LOGGER.info("saveMovie : {}", movie.getTitle());

		if (movie.getId() == 0) {
			throw new MovieServiceException("movie id == 0");
		}

		// TODO : poster
		// try {
		// if (movie.getPosterHref() != null) {
		// byte[] poster = MovieHelper.downloadPicture(movie.getPosterHref());
		// // TODO
		// }
		// } catch (IOException e) {
		// LOGGER.error("Unable to retrieve poster for {} : {}",
		// movie.getTitle(), e.getMessage());
		// }

		// TODO : actors picture
		// for (CastMember castMember : movie.getCastMembers()) {
		// Person person = castMember.getPerson();
		// Long personId = person.getId();
		// Personne personne = personneDAO.loadById(personId);
		// if (personne != null && person.getPictureHref() != null) {
		// try {
		// byte[] photo = MovieHelper.downloadPicture(person.getPictureHref());
		// personne.setPhoto(photo);
		// } catch (IOException e) {
		// LOGGER.error("Unable to retrieve picture for {} : {}",
		// person.getName(), e.getMessage());
		// }
		// }
		// }

		jpaMovieRepository.save(movie);
	}

	@Override
	@Transactional
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
	@Transactional(readOnly = true)
	public long countByKind(Kind kind) {
		// TODO : implements getMoviesCount with filter
		return jpaMovieRepository.count();
	}

}
