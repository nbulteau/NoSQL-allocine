package fr.sii.formation.gwt.client.activityplace;

import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;
import com.google.inject.Inject;
import com.google.inject.Provider;

import fr.sii.formation.gwt.client.mvp.ActivityPlace;

/**
 * 
 * A Contact Place that extends ActivityPlace
 * 
 * @author nbulteau
 * 
 */
public class MoviePlace extends ActivityPlace<MovieActivity>
{

	@Inject
	public MoviePlace(MovieActivity activity)
	{
		super(activity);
	}

	@Prefix("movies")
	public static class Tokenizer implements PlaceTokenizer<MoviePlace>
	{

		// Since the place is injectable, we'll let Gin do the construction.
		private final Provider<MoviePlace> placeProvider;

		@Inject
		public Tokenizer(Provider<MoviePlace> placeProvider)
		{
			this.placeProvider = placeProvider;
		}

		@Override
		public String getToken(MoviePlace place)
		{
			return place.getPlaceName();
		}

		@Override
		public MoviePlace getPlace(String token)
		{
			MoviePlace place = placeProvider.get();
			place.setPlaceName(token);
			return place;
		}
	}
}
