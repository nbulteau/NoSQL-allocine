package fr.sii.formation.gwt.server.repository.jpa;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.Index;

@Entity()
public class Film {
	@Id
	private long id;

	private boolean vu;

	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE })
	@JoinTable(name = "filmrealisateur", joinColumns = @JoinColumn(name = "realisateurId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "realisateurFk", referencedColumnName = "id"))
	private Set<Personne> realisateurs = new HashSet<Personne>();

	@ManyToMany(cascade = { CascadeType.ALL, CascadeType.REMOVE })
	@JoinTable(name = "filmacteur", joinColumns = @JoinColumn(name = "acteurId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "roleFk", referencedColumnName = "id"))
	private Set<Acteur> acteurs = new HashSet<Acteur>();

	@Index(name = "titreIndex")
	private String titre;

	private String titreoriginal;

	private Date datesortie;

	private int duree;

	@Column(length = 2000)
	private String synopsis;

	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(length = 100000)
	private byte[] affiche;

	@ManyToMany
	@JoinTable(name = "filmgenres", joinColumns = @JoinColumn(name = "genreId", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "genresFk", referencedColumnName = "label"))
	private Set<Genre> genres = new HashSet<Genre>();

	@Override
	public String toString() {
		return "Film [id=" + id + ", vu=" + vu + ", realisateurs=" + realisateurs + ", acteurs=" + acteurs + ", titre=" + titre + ", titreoriginal="
				+ titreoriginal + ", datesortie=" + datesortie + ", duree=" + duree + ", synopsis=" + synopsis + ", affiche=" + Arrays.toString(affiche)
				+ ", genres=" + genres + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isVu() {
		return vu;
	}

	public void setVu(boolean vu) {
		this.vu = vu;
	}

	public Set<Personne> getRealisateurs() {
		return realisateurs;
	}

	public void setRealisateurs(Set<Personne> realisateurs) {
		this.realisateurs = realisateurs;
	}

	public Set<Acteur> getActeurs() {
		return acteurs;
	}

	public void setActeurs(Set<Acteur> acteurs) {
		this.acteurs = acteurs;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getTitreoriginal() {
		return titreoriginal;
	}

	public void setTitreoriginal(String titreoriginal) {
		this.titreoriginal = titreoriginal;
	}

	public Date getDatesortie() {
		return datesortie;
	}

	public void setDatesortie(Date datesortie) {
		this.datesortie = datesortie;
	}

	public int getDuree() {
		return duree;
	}

	public void setDuree(int duree) {
		this.duree = duree;
	}

	public String getSynopsis() {
		return synopsis;
	}

	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}

	public byte[] getAffiche() {
		return affiche;
	}

	public void setAffiche(byte[] affiche) {
		this.affiche = affiche;
	}

	public Set<Genre> getGenres() {
		return genres;
	}

	public void setGenres(Set<Genre> genres) {
		this.genres = genres;
	}

}
