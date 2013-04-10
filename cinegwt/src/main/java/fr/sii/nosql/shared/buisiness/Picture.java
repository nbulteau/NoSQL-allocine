package fr.sii.nosql.shared.buisiness;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;

import org.springframework.data.annotation.Id;

public abstract class Picture implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 100000)
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
