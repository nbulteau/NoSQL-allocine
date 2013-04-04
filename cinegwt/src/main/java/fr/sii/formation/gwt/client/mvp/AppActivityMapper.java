package fr.sii.formation.gwt.client.mvp;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.inject.Inject;

public class AppActivityMapper implements ActivityMapper
{

	@Inject
	public AppActivityMapper(EventBus eventBus, PlaceController placeController)
	{
		super();
	}

	@Override
	public Activity getActivity(Place place)
	{
		if (place instanceof ActivityPlace)
		{
			Activity activity = ((ActivityPlace<?>) place).getActivity();
			return activity;
		}

		return null;

	}

}
