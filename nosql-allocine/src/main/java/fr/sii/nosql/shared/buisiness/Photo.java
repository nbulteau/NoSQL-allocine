package fr.sii.nosql.shared.buisiness;

import javax.persistence.Entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "photos")
@Entity
public class Photo extends Picture {

	private static final long serialVersionUID = -8650443351356806340L;

	public Photo() {
		super();
	}

	public Photo(Long id, byte[] picture) {
		super(id, picture);
	}

}
