package fr.sii.formation.gwt.shared.buisiness;

import java.io.Serializable;

import org.springframework.data.annotation.Id;

public abstract class Picture implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private byte[] picture;

	public Picture() {
		super();
	}

	public Picture(long id, byte[] picture) {
		super();
		this.id = id;
		this.picture = picture;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public long getId() {
		return id;
	}

}
