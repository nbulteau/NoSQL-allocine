package fr.sii.nosql.shared.buisiness;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posters")
@Entity
public class Poster extends Picture {

	private static final long serialVersionUID = 1L;

	public Poster() {
		super();
	}

	public Poster(Long id, byte[] picture) {
		super(id, picture);
	}

}
