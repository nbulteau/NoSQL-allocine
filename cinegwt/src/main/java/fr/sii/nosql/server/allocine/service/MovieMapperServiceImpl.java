package fr.sii.nosql.server.allocine.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.sii.nosql.server.allocine.buisiness.CastMember;
import fr.sii.nosql.server.allocine.buisiness.Genre;
import fr.sii.nosql.server.allocine.buisiness.Movie;
import fr.sii.nosql.shared.buisiness.Actor;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Person;

@Service("movieMapperService")
public class MovieMapperServiceImpl implements MovieMapperService {
	private final static Logger LOGGER = LoggerFactory.getLogger(MovieMapperServiceImpl.class);

	private static final int ACTOR = 8001;

	private static final int DIRECTOR = 8002;

	public MovieMapperServiceImpl() {
		super();
	}

	@Override
	public fr.sii.nosql.shared.buisiness.Movie convertToBuisinessObject(Movie alloCineMovie) throws ConvertException {
		LOGGER.debug("Convert movie : {}", alloCineMovie.getTitle());

		fr.sii.nosql.shared.buisiness.Movie movie = new fr.sii.nosql.shared.buisiness.Movie(alloCineMovie.getCode(), alloCineMovie.getTitle(),
				alloCineMovie.getOriginalTitle());

		// releasedate
		Date releaseDate = null;
		String releaseDateString = null;
		if (alloCineMovie.getRelease() != null) {
			releaseDateString = alloCineMovie.getRelease().getReleaseDate();
		}
		if (releaseDateString != null) {
			SimpleDateFormat sdf = null;
			switch (releaseDateString.length()) {
			case 4:
				sdf = new SimpleDateFormat("yyyy");
				break;
			case 7:
				sdf = new SimpleDateFormat("yyyy-MM");
				break;
			default:
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			}
			try {
				releaseDate = sdf.parse(releaseDateString);
			} catch (ParseException e) {
				throw new ConvertException("releasedate convertion failed : " + releaseDateString, e);
			}
		}
		movie.setReleasedate(releaseDate);

		// duration
		movie.setDuration(alloCineMovie.getRuntime());

		// directors
		List<Person> directors = new ArrayList<Person>();
		if (alloCineMovie.getCastMember() != null) {
			for (CastMember castMember : alloCineMovie.getCastMember()) {
				if (castMember.getActivity().getCode() == DIRECTOR) {
					directors.add(retrievePerson(castMember));
				}
			}
		}
		movie.getDirectors().clear();
		movie.getDirectors().addAll(directors);

		// actors
		List<Actor> actors = new ArrayList<Actor>();
		if (alloCineMovie.getCastMember() != null) {
			for (CastMember castMember : alloCineMovie.getCastMember()) {
				if (castMember.getActivity().getCode() == ACTOR) {
					actors.add(retrieveActor(castMember));
				}
			}
		}
		movie.getActors().clear();
		movie.getActors().addAll(actors);

		// kinds
		List<Kind> kinds = new ArrayList<Kind>();
		if (alloCineMovie.getGenre() != null) {
			for (Genre genre : alloCineMovie.getGenre()) {
				kinds.add(Kind.getKindByLabel(genre.get$()));
			}
		}
		movie.getKinds().addAll(kinds);

		// synopsis
		movie.setSynopsis(alloCineMovie.getSynopsis());

		// poster
		if (alloCineMovie.getPoster() != null) {
			String href = alloCineMovie.getPoster().getHref();
			if (href != null) {
				movie.setPosterHref(href);
			}
		}

		// pressRating
		movie.setPressRating(alloCineMovie.getStatistics().getPressRating());

		// userRating
		movie.setUserRating(alloCineMovie.getStatistics().getUserRating());

		return movie;
	}

	/**
	 * 
	 * @param castMember
	 * @return
	 */
	private Actor retrieveActor(CastMember castMember) {
		Actor actor = null;
		Person person = retrievePerson(castMember);
		if (person != null) {
			actor = new Actor(person, castMember.getRole());
		}
		return actor;
	}

	/**
	 * 
	 * @param castMember
	 * @return
	 */
	private Person retrievePerson(CastMember castMember) {
		Person person = new Person(castMember.getPerson().getCode(), castMember.getPerson().getName());
		// picture
		if (castMember.getPicture() != null) {
			String href = castMember.getPicture().getHref();
			if (href != null) {
				person.setPictureHref(href);
			}
		}

		return person;
	}
}
