package fr.sii.formation.gwt.client.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Singleton;

import fr.sii.formation.gwt.client.activityplace.AppPlaceFactory;
import fr.sii.formation.gwt.client.mvp.AppActivityMapper;
import fr.sii.formation.gwt.client.view.MovieView;
import fr.sii.formation.gwt.client.view.MovieViewImpl;

public class AppGinModule extends AbstractGinModule {

	@Override
	protected void configure() {
		// bind the EventBus
		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);

		// bind the place controller
		bind(PlaceController.class).to(InjectablePlaceController.class).in(
				Singleton.class);

		bind(AppPlaceFactory.class).in(Singleton.class);

		// bind the Activitymapper
		bind(ActivityMapper.class).to(AppActivityMapper.class).in(
				Singleton.class);

		// bind the views
		bind(MovieView.class).to(MovieViewImpl.class).in(Singleton.class);
	}

}
