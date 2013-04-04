package fr.sii.formation.gwt.client.gin;

import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceController;

import fr.sii.formation.gwt.client.activityplace.AppPlaceFactory;

@GinModules(AppGinModule.class)
public interface AppGinjector extends Ginjector
{
	ActivityMapper getActivityMapper();

	PlaceController getPlaceController();

	EventBus getEventBus();

	AppPlaceFactory getAppPlaceFactory();
}
