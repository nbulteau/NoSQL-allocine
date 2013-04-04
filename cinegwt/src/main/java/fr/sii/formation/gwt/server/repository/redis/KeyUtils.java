package fr.sii.formation.gwt.server.repository.redis;

/**
 * Simple class keeping all the key patterns to avoid the proliferation of
 * Strings through the code.
 * 
 */
public abstract class KeyUtils {

	public static final String MOVIES_COUNT = "movies:count";

	public static final String MOVIE = "movie:";

	public static final String MOVIENAME = "moviename:";

	public static final String ACTOR = "actor:";

	public static final String DIRECTOR = "director:";

	public static final String ACTORNAME = "actorname:";

	public static final String DIRECTORNAME = "directorname:";

	public static final String KIND = "kind:";

	public static String moviesCount() {
		return MOVIES_COUNT;
	}

	public static String movie(long id) {
		return MOVIE + id;
	}

	public static String movieName(String name) {
		return MOVIENAME + name;
	}

	public static String actor(long id) {
		return ACTOR + id;
	}

	public static String actorName(String name) {
		return ACTORNAME + name;
	}

	public static String director(long id) {
		return DIRECTOR + id;
	}

	public static String directorName(String name) {
		return DIRECTORNAME + name;
	}

	public static String kind(String label) {
		return KIND + label;
	}
}
