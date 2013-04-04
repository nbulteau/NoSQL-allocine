package fr.sii.formation.gwt.client.view;

import java.util.List;

import com.google.gwt.cell.client.ImageCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;

import fr.sii.formation.gwt.shared.buisiness.Movie;

public class MovieViewImpl extends Composite implements MovieView {

	private final static String PICTURE_URL = "/movieServlet?type=poster&scale&id=";

	private static final int PAGESIZE = 5;

	private static MovieViewUiBinder uiBinder = GWT.create(MovieViewUiBinder.class);

	@UiField(provided = true)
	CellTable<Movie> cellTable = new CellTable<Movie>();

	/**
	 * The pager used to change the range of data.
	 */
	@UiField(provided = true)
	SimplePager pager = new SimplePager(SimplePager.TextLocation.CENTER);

	interface MovieViewUiBinder extends UiBinder<Widget, MovieViewImpl> {
	}

	public MovieViewImpl() {
		pager.setStyleName("gwt-SimplePager");
		pager.setDisplay(cellTable);

		// Create poster column.
		Column<Movie, String> posterColumn = new Column<Movie, String>(new ImageCell()) {

			@Override
			public String getValue(Movie movie) {
				return PICTURE_URL + movie.getId();
			}
		};
		cellTable.addColumn(posterColumn);

		// Create details column.
		TextColumn<Movie> detailsColumn = new TextColumn<Movie>() {
			@Override
			public String getValue(Movie movie) {
				return movie.getTitle();
			}
		};
		cellTable.addColumn(detailsColumn);

		// Create synopsis column.
		TextColumn<Movie> synopsisColumn = new TextColumn<Movie>() {
			@Override
			public String getValue(Movie movie) {
				return movie.getSynopsis();
			}
		};
		cellTable.addColumn(synopsisColumn);
		cellTable.setPageSize(PAGESIZE);
		initWidget(uiBinder.createAndBindUi(this));
	}

	@Override
	public void setPresenter(final Presenter presenter) {
		AsyncCallback<Long> callback = new AsyncCallback<Long>() {
			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Error fetching movies");
			}

			@Override
			public void onSuccess(Long result) {
				cellTable.setRowCount(result.intValue());
			}
		};
		presenter.getMoviesCount(callback);

		// Associate an async data provider to the table
		AsyncDataProvider<Movie> provider = new AsyncDataProvider<Movie>() {
			@Override
			protected void onRangeChanged(HasData<Movie> display) {
				final int start = display.getVisibleRange().getStart();
				int length = display.getVisibleRange().getLength();

				AsyncCallback<List<Movie>> callback = new AsyncCallback<List<Movie>>() {
					@Override
					public void onFailure(Throwable caught) {
						Window.alert("Error fetching movies");
					}

					@Override
					public void onSuccess(List<Movie> result) {
						updateRowData(start, result);
					}
				};

				presenter.fetchMovies(start, length, callback);
			}
		};
		provider.addDataDisplay(cellTable);
	}

	@Override
	public int getClickedRow(ClickEvent event) {
		// TODO Auto-generated method stub
		return 0;
	}
}
