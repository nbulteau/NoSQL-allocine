package fr.sii.formation.gwt.shared.buisiness;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
public class Photo extends Picture {

	private static final long serialVersionUID = 1L;

	public Photo() {
		super();
	}

	public Photo(Long id, byte[] picture) {
		super(id, picture);
	}

}
