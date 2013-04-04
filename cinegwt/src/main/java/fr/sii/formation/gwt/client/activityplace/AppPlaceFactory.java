package fr.sii.formation.gwt.client.activityplace;

import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.sii.formation.gwt.client.activityplace.MoviePlace.Tokenizer;

/**
 * 
 * A place factory which knows about all the tokenizers in the application
 * 
 * @author nbulteau
 * 
 */
public class AppPlaceFactory {

	// A single instance of the tokenizer should work, since they don't have
	// state.
	@Inject
	private Tokenizer moviePlaceTokenizer;

	@Inject
	private Provider<MoviePlace> movieProvider;

	// movie place
	public MoviePlace.Tokenizer getPoviePlaceTokenizer() {
		return moviePlaceTokenizer;
	}

	public MoviePlace getMoviePlace() {
		return movieProvider.get();
	}

}
