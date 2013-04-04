package fr.sii.formation.gwt.shared.buisiness;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posters")
public class Poster extends Picture {

	private static final long serialVersionUID = 1L;

	public Poster() {
		super();
	}

	public Poster(Long id, byte[] picture) {
		super(id, picture);
	}

}
