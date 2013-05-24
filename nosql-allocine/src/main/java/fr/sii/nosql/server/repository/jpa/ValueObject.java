package fr.sii.nosql.server.repository.jpa;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class ValueObject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Lob
	private String json;

	public ValueObject() {
		super();
	}

	public ValueObject(long id, String json) {
		super();
		this.id = id;
		this.json = json;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

}
