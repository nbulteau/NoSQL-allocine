package fr.sii.nosql.shared.buisiness;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Movie
 * 
 * @author nbulteau
 * 
 */
@Document(collection = "movies")
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Indexed(direction = IndexDirection.ASCENDING)
	private String title;

	private String originaltitle;

	private Date releasedate;

	/**
	 * duration in second
	 */
	private int duration;

	private List<Person> directors = new ArrayList<Person>();

	private List<Actor> actors = new ArrayList<Actor>();

	private List<Kind> kinds = new ArrayList<Kind>();

	private String synopsis;

	private float pressRating;

	private float userRating;

	private boolean viewed = false;

	@Transient
	private String posterHref;

	public Movie() {
		super();
	}

	public Movie(long id, String title, String originaltitle) {
		super();
		this.id = id;
		this.title = title;
		this.originaltitle = originaltitle;
	}

	@Override
	public String toString() {
		return "Movie [id=" + id + ", title=" + title + ", originaltitle=" + originaltitle + ", releasedate=" + releasedate + ", duration=" + duration
				+ ", directors=" + directors + ", actors=" + actors + ", kinds=" + kinds + ", synopsis=" + synopsis + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Movie other = (Movie) obj;
		if (id != other.id) {
			return false;
		}
		if (title == null) {
			if (other.title != null) {
				return false;
			}
		} else if (!title.equals(other.title)) {
			return false;
		}
		return true;
	}

	// getter - setter
	public long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOriginaltitle() {
		return originaltitle;
	}

	public void setOriginaltitle(String originaltitle) {
		this.originaltitle = originaltitle;
	}

	public Date getReleasedate() {
		return releasedate;
	}

	public void setReleasedate(Date releasedate) {
		this.releasedate = releasedate;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public List<Person> getDirectors() {
		return directors;
	}

	public List<Actor> getActors() {
		return actors;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public List<Kind> getKinds() {
		return kinds;
	}

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public float getPressRating() {
		return pressRating;
	}

	public void setPressRating(float pressRating) {
		this.pressRating = pressRating;
	}

	public float getUserRating() {
		return userRating;
	}

	public void setUserRating(float userRating) {
		this.userRating = userRating;
	}

	public String getPosterHref() {
		return posterHref;
	}

	public void setPosterHref(String posterHref) {
		this.posterHref = posterHref;
	}

	public void setDirectors(List<Person> directors) {
		this.directors = directors;
	}

	public void setActors(List<Actor> actors) {
		this.actors = actors;
	}

	public void setKinds(List<Kind> kinds) {
		this.kinds = kinds;
	}

	public void setId(long id) {
		this.id = id;
	}
}
