package fr.sii.formation.gwt.client;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.SimplePanel;

import fr.sii.formation.gwt.client.activityplace.AppPlaceFactory;
import fr.sii.formation.gwt.client.activityplace.MoviePlace;
import fr.sii.formation.gwt.client.gin.AppGinjector;
import fr.sii.formation.gwt.client.mvp.AppPlaceHistoryMapper;

public class Movie implements EntryPoint {

	private final SimplePanel appWidget = new SimplePanel();

	private final AppGinjector injector = GWT.create(AppGinjector.class);

	@Override
	public void onModuleLoad() {
		EventBus eventBus = injector.getEventBus();
		// Le PlaceController est un objet qui gère la place actuelle et la
		// navigation entre les places
		PlaceController placeController = injector.getPlaceController();

		ActivityMapper activityMapper = injector.getActivityMapper();
		ActivityManager activityManager = new ActivityManager(activityMapper,
				eventBus);
		activityManager.setDisplay(appWidget);

		AppPlaceFactory factory = injector.getAppPlaceFactory();
		MoviePlace defaultPlace = factory.getMoviePlace();

		AppPlaceHistoryMapper historyMapper = GWT
				.create(AppPlaceHistoryMapper.class);
		historyMapper.setFactory(factory);

		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(
				historyMapper);
		historyHandler.register(placeController, eventBus, defaultPlace);

		RootPanel.get().add(appWidget);

		historyHandler.handleCurrentHistory();
	}

}
