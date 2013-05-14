package fr.sii.nosql.shared.buisiness;


/**
 * Kind of the movies
 * 
 * @author nbulteau
 * 
 */
public enum Kind {

	Action, Animation, Arts_Martiaux("Arts Martiaux"), Aventure, Biopic, Bollywood, Classique, Comedie("Comédie"), Comedie_dramatique("Comédie dramatique"), Comedie_musicale(
			"Comédie musicale"), Concert, Dessin_animé("Dessin animé"), Divers, Documentaire, Drame, Epouvante_horreur("Epouvante-horreur"), Erotique, Espionnage, Famille, Fantastique, Guerre, Historique, Judiciaire, Movie_night(
			"AlloCineMovie night"), Musical, Opera, Péplum, Policier, Romance, Science_fiction("Science fiction"), Show, Sport_event("Sport event"), Thriller, Western, Non_definie(
			"Non définie");

	@javax.persistence.Id
	private String label;


	Kind() {
		this.label = this.name();
	}

	Kind(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * Get the kind with the label
	 * 
	 * @param labelToGet
	 *            label of the Kind
	 * @return
	 */
	public static Kind getKindByLabel(String labelToGet) {
		for (Kind kind : Kind.values()) {
			if (kind.getLabel().equals(labelToGet)) {
				return kind;
			}
		}
		return Kind.Non_definie;
	}

}
