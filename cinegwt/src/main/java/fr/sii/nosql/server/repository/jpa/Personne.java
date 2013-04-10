package fr.sii.nosql.server.repository.jpa;

import java.util.Arrays;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Personne {

	@Id
	private long id;

	private String nom;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 100000)
	private byte[] photo;

	private String biographie;

	@Override
	public String toString() {
		return "Personne [id=" + id + ", nom=" + nom + ", photo=" + Arrays.toString(photo) + ", biographie=" + biographie + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getBiographie() {
		return biographie;
	}

	public void setBiographie(String biographie) {
		this.biographie = biographie;
	}

}
