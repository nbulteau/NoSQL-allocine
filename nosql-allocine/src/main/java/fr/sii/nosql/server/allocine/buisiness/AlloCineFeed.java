package fr.sii.nosql.server.allocine.buisiness;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeName;

@JsonTypeName("feed")
public class AlloCineFeed {
	private Date updated;

	private int page;

	private int count;

	private int totalResults;

	private List<AlloCineMovie> alloCineMovie;

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}

	public List<AlloCineMovie> getMovie() {
		return alloCineMovie;
	}

	public void setMovie(List<AlloCineMovie> alloCineMovie) {
		this.alloCineMovie = alloCineMovie;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalResults() {
		return totalResults;
	}

	public void setTotalResults(int totalResults) {
		this.totalResults = totalResults;
	}
}
