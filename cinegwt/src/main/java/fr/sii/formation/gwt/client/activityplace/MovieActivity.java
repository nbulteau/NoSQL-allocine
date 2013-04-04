package fr.sii.formation.gwt.client.activityplace;

import java.util.List;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import fr.sii.formation.gwt.client.service.MovieRemoteServiceAsync;
import fr.sii.formation.gwt.client.view.MovieView;
import fr.sii.formation.gwt.shared.buisiness.Movie;

public class MovieActivity extends AbstractActivity implements
		MovieView.Presenter {

	private final MovieRemoteServiceAsync rpcService;

	private final MovieView view;

	private final PlaceController placeController;

	@Inject
	public MovieActivity(EventBus eventBus, MovieView view,
			PlaceController placeController, MovieRemoteServiceAsync rpcService) {
		this.rpcService = rpcService;
		this.view = view;
		this.placeController = placeController;
	}

	public void init(MoviePlace place) {
	}

	@Override
	public void start(AcceptsOneWidget container, EventBus eventBus) {
		container.setWidget(view.asWidget());
		view.setPresenter(this);
	}

	/**
	 * Ask user before stopping this activity
	 */
	@Override
	public String mayStop() {
		return "Please hold on. This activity is stopping.";
	}

	/**
	 * Navigate to a new Place in the browser
	 */
	@Override
	public void goTo(Place place) {
		placeController.goTo(place);
	}

	@Override
	public void fetchMovies(int start, int length,
			AsyncCallback<List<Movie>> callback) {
		rpcService.fetchMovies(start, length, null, callback);
	}

	@Override
	public void getMoviesCount(AsyncCallback<Long> callback) {
		rpcService.getMoviesCount(null, callback);
	}
}
