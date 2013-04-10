package fr.sii.nosql.shared;

import java.io.Serializable;

import fr.sii.nosql.shared.buisiness.Kind;

public class MovieFilter implements Serializable {

	private static final long serialVersionUID = 1L;

	private Boolean viewed;

	private Kind kind;

	private String title;

	public MovieFilter() {
		super();
	}

	public MovieFilter(Boolean viewed, Kind kind, String title) {
		super();
		this.viewed = viewed;
		this.kind = kind;
		this.title = title;
	}

	@Override
	public String toString() {
		return "MovieFilter [viewed=" + viewed + ", kind=" + kind + ", title="
				+ title + "]";
	}

	public Boolean isViewed() {
		return viewed;
	}

	public Kind getKind() {
		return kind;
	}

	public String getTitle() {
		return title;
	}

	public Boolean getViewed() {
		return viewed;
	}

	public void setViewed(Boolean viewed) {
		this.viewed = viewed;
	}

	public void setKind(Kind kind) {
		this.kind = kind;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
