package fr.sii.nosql.shared.buisiness;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Index;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.neo4j.annotation.GraphId;
import org.springframework.data.neo4j.annotation.NodeEntity;
import org.springframework.data.neo4j.annotation.RelatedTo;
import org.springframework.data.neo4j.support.index.IndexType;

/**
 * Movie
 * 
 * @author nbulteau
 * 
 */
@Document(collection = "movies")
@Entity
@NodeEntity
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.springframework.data.annotation.Id
	@javax.persistence.Id
	@GraphId
	private long id;

	@org.springframework.data.mongodb.core.index.Indexed(direction = IndexDirection.ASCENDING)
	@Index(name = "titreIndex")
	@org.springframework.data.neo4j.annotation.Indexed(indexType = IndexType.FULLTEXT, indexName = "titreIndex")
	private String title;

	private String originaltitle;

	private Date releasedate;

	/**
	 * duration in second
	 */
	private int duration;

	@ManyToMany(cascade = { CascadeType.MERGE })
	@JoinTable(name = "movie_director", joinColumns = @JoinColumn(name = "directorId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "personId", referencedColumnName = "id"))
	private Set<Person> directors = new HashSet<>();

	@OneToMany(cascade = { CascadeType.ALL })
	@RelatedTo(type="castMember")
	private Set<CastMember> castMembers = new HashSet<>();

	@ElementCollection
	@CollectionTable(name = "movie_kind")
	@RelatedTo(type="movieKind")
	private Set<Kind> kinds = new HashSet<>();

	@Column(length = 2000)
	private String synopsis;

	private float pressRating;

	private float userRating;

	private boolean viewed = false;

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
		return "Movie [id=" + id + ", title=" + title + ", originaltitle="
				+ originaltitle + ", releasedate=" + releasedate
				+ ", duration=" + duration + ", directors=" + directors
				+ ", castMembers=" + castMembers + ", kinds=" + kinds
				+ ", synopsis=" + synopsis + "]";
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

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public Set<Person> getDirectors() {
		return directors;
	}

	public void setDirectors(Set<Person> directors) {
		this.directors = directors;
	}

	public Set<CastMember> getCastMembers() {
		return castMembers;
	}

	public void setCastMembers(Set<CastMember> castMembers) {
		this.castMembers = castMembers;
	}

	public Set<Kind> getKinds() {
		return kinds;
	}

	public void setKinds(Set<Kind> kinds) {
		this.kinds = kinds;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
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

	public boolean isViewed() {
		return viewed;
	}

	public void setViewed(boolean viewed) {
		this.viewed = viewed;
	}

	public String getPosterHref() {
		return posterHref;
	}

	public void setPosterHref(String posterHref) {
		this.posterHref = posterHref;
	}

	// getter - setter

}
