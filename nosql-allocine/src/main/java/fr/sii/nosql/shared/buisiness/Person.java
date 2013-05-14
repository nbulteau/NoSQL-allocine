package fr.sii.nosql.shared.buisiness;

import java.io.Serializable;

import javax.persistence.Entity;

import org.hibernate.annotations.Index;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "persons")
@Entity
public class Person implements Serializable {

	private static final long serialVersionUID = 1L;

	@org.springframework.data.annotation.Id
	@javax.persistence.Id
	private long id;

	@Indexed(direction = IndexDirection.ASCENDING)
	@Index(name = "nameIndex")
	private String name;

	private String pictureHref;

	public Person() {
		super();
	}

	public Person(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

	// getter - setter

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPictureHref() {
		return pictureHref;
	}

	public void setPictureHref(String pictureHref) {
		this.pictureHref = pictureHref;
	}
}
