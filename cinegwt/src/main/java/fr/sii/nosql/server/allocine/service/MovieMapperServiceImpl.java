package fr.sii.nosql.server.allocine.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import fr.sii.nosql.server.allocine.buisiness.AlloCineCastMember;
import fr.sii.nosql.server.allocine.buisiness.AlloCineGenre;
import fr.sii.nosql.server.allocine.buisiness.AlloCineMovie;
import fr.sii.nosql.shared.buisiness.CastMember;
import fr.sii.nosql.shared.buisiness.Kind;
import fr.sii.nosql.shared.buisiness.Movie;
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
	public Movie convertToBuisinessObject(AlloCineMovie alloCineMovie) throws ConvertException {
		LOGGER.debug("Convert movie : {}", alloCineMovie.getTitle());

		Movie movie = new Movie(alloCineMovie.getCode(), alloCineMovie.getTitle(), alloCineMovie.getOriginalTitle());

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
		Set<Person> directors = new HashSet<Person>();
		if (alloCineMovie.getCastMember() != null) {
			for (AlloCineCastMember alloCineCastMember : alloCineMovie.getCastMember()) {
				if (alloCineCastMember.getActivity().getCode() == DIRECTOR) {
					directors.add(retrievePerson(alloCineCastMember));
				}
			}
		}
		movie.getDirectors().clear();
		movie.getDirectors().addAll(directors);

		// actors
		Set<CastMember> castMembers = new HashSet<>();
		if (alloCineMovie.getCastMember() != null) {
			for (AlloCineCastMember alloCineCastMember : alloCineMovie.getCastMember()) {
				if (alloCineCastMember.getActivity().getCode() == ACTOR) {
					castMembers.add(retrieveCastMember(alloCineCastMember));
				}
			}
		}
		movie.getCastMembers().clear();
		movie.getCastMembers().addAll(castMembers);

		// kinds
		List<Kind> kinds = new ArrayList<Kind>();
		if (alloCineMovie.getGenre() != null) {
			for (AlloCineGenre alloCineGenre : alloCineMovie.getGenre()) {
				kinds.add(Kind.getKindByLabel(alloCineGenre.get$()));
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
	 * @param alloCineCastMember
	 * @return
	 */
	private CastMember retrieveCastMember(AlloCineCastMember alloCineCastMember) {
		CastMember castMember = null;
		Person person = retrievePerson(alloCineCastMember);
		if (person != null) {
			castMember = new CastMember(person, alloCineCastMember.getRole());
		}
		return castMember;
	}

	/**
	 * 
	 * @param alloCineCastMember
	 * @return
	 */
	private Person retrievePerson(AlloCineCastMember alloCineCastMember) {
		Person person = new Person(alloCineCastMember.getPerson().getCode(), alloCineCastMember.getPerson().getName());
		// picture
		if (alloCineCastMember.getPicture() != null) {
			String href = alloCineCastMember.getPicture().getHref();
			if (href != null) {
				person.setPictureHref(href);
			}
		}

		return person;
	}
}
