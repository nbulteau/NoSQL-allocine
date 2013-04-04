package fr.sii.formation.gwt.client.view;

import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.IsWidget;

import fr.sii.formation.gwt.shared.buisiness.Movie;

public interface MovieView extends IsWidget
{

	int getClickedRow(ClickEvent event);

	void setPresenter(Presenter presenter);

	public interface Presenter
	{
		void goTo(Place place);

		void fetchMovies(int start, int length, AsyncCallback<List<Movie>> callback);

		void getMoviesCount(AsyncCallback<Long> callback);
	}
}
