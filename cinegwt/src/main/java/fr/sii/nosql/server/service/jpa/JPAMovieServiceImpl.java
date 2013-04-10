package fr.sii.nosql.server.service.jpa;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.server.MovieHelper;
import fr.sii.nosql.server.allocine.service.AlloCineService;
import fr.sii.nosql.server.repository.jpa.Film;
import fr.sii.nosql.server.repository.jpa.FilmDAO;
import fr.sii.nosql.server.repository.jpa.Genre;
import fr.sii.nosql.server.repository.jpa.GenreDAO;
import fr.sii.nosql.server.repository.jpa.Personne;
import fr.sii.nosql.server.repository.jpa.PersonneDAO;
import fr.sii.nosql.server.service.MovieFilter;
import fr.sii.nosql.server.service.MovieService;
import fr.sii.nosql.server.service.MovieServiceException;
import fr.sii.nosql.server.service.mapper.FilmMapper;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Person;

@Profile("jpa")
@Service("jpaMovieService")
public class JPAMovieServiceImpl implements MovieService {

	private final static Logger LOGGER = LoggerFactory.getLogger(JPAMovieServiceImpl.class);

	private final FilmDAO filmDAO;

	private final GenreDAO genreDAO;

	private final PersonneDAO personneDAO;

	private final FilmMapper filmMapper;

	private final AlloCineService alloCineService;

	@Autowired(required = true)
	public JPAMovieServiceImpl(FilmDAO filmDAO, GenreDAO genreDAO, PersonneDAO personneDAO, FilmMapper filmMapper, AlloCineService alloCineService) {
		super();
		this.filmDAO = filmDAO;
		this.genreDAO = genreDAO;
		this.filmMapper = filmMapper;
		this.alloCineService = alloCineService;
		this.personneDAO = personneDAO;
	}

	@Override
	public boolean exists(long id) {
		return filmDAO.loadById(id) == null ? false : true;
	};

	@Override
	@Transactional(readOnly = true)
	public Movie findById(long id) {
		Film film = filmDAO.loadById(id);

		return filmMapper.toDto(film);
	}

	@Override
	public List<Movie> findAll() {
		List<Film> films = filmDAO.loadAll();

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByTitle(String title) {
		List<Film> films = filmDAO.rechercherFilmsParLeTitre(title);

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByTitleLike(String title) {
		List<Film> films = filmDAO.rechercherFilmsDontLeTitreCommencePar(title);

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByActor(long id) {
		List<Film> films = filmDAO.rechercherFilmsDansLequelPersonneEstActeur(id);

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByActor(String name) {
		List<Film> films = filmDAO.rechercherFilmsDansLequelPersonneEstActeur(name);

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByDirector(long id) {
		List<Film> films = filmDAO.rechercherFilmsDansLequelPersonneEstRealisateur(id);

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Movie> findByDirector(String name) {
		List<Film> films = filmDAO.rechercherFilmsDansLequelPersonneEstRealisateur(name);

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional
	public void save(Movie movie) throws MovieServiceException {
		LOGGER.info("saveMovie : {}", movie.getTitle());

		Film film = filmMapper.toModel(movie);

		List<Genre> allGenres = genreDAO.loadAll();
		for (Genre g : film.getGenres())
			if (!allGenres.contains(g))
				genreDAO.persist(g);

		// poster
		try {
			if (movie.getPosterHref() != null) {
				byte[] poster = MovieHelper.downloadPicture(movie.getPosterHref());
				film.setAffiche(poster);
			}
		} catch (IOException e) {
			LOGGER.error("Unable to retrieve poster for {} : {}", movie.getTitle(), e.getMessage());
		}
		// actors picture
		for (CastMember castMember : movie.getCastMembers()) {
			Person person = castMember.getPerson();
			Long personId = person.getId();
			Personne personne = personneDAO.loadById(personId);
			if (personne != null && person.getPictureHref() != null) {
				try {
					byte[] photo = MovieHelper.downloadPicture(person.getPictureHref());
					personne.setPhoto(photo);
				} catch (IOException e) {
					LOGGER.error("Unable to retrieve photo for {} : {}", person.getName(), e.getMessage());
				}
			}
		}

		filmDAO.update(film);
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
	public List<Movie> fetchMovies(int start, int length, MovieFilter movieFilter) {
		LOGGER.debug("fetchMovies({}, {}, {})", new Object[] { start, length, movieFilter });

		String genreLabel = null;
		if (movieFilter.getKind() != null) {
			genreLabel = movieFilter.getKind().getLabel();
		}

		List<Film> films = filmDAO.findAllSorted(start, length, movieFilter.isViewed(), genreLabel, movieFilter.getTitle());

		return filmMapper.toDto(films);
	}

	@Override
	@Transactional(readOnly = true)
	public long getMoviesCount(MovieFilter movieFilter) {
		String genreLabel = null;
		if (movieFilter.getKind() != null) {
			genreLabel = movieFilter.getKind().getLabel();
		}

		return filmDAO.count(movieFilter.isViewed(), genreLabel, movieFilter.getTitle());
	}

}
