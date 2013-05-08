package fr.sii.nosql.shared.buisiness;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Lob;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Picture implements Serializable {

	private static final long serialVersionUID = 19090729741166127L;

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
