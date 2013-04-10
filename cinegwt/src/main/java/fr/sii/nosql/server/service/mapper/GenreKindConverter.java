package fr.sii.nosql.server.service.mapper;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

import fr.sii.nosql.server.repository.jpa.Genre;
import fr.sii.nosql.shared.buisiness.Kind;

public class GenreKindConverter implements CustomConverter {

	@Override
	public Object convert(Object destination, Object source,
			Class<?> destClass, Class<?> sourceClass) {
		if (source == null) {
			return null;
		}
		Kind kind = null;
		if (source instanceof Genre) {
			// check to see if the object already exists
			if (destination == null) {
				kind = Kind.getKindByLabel(((Genre) source).getLabel());
			}
			return kind;
		} else if (source instanceof Kind) {
			Genre genre = new Genre();
			genre.setLabel(((Kind) source).getLabel());
			return genre;
		} else {
			throw new MappingException(
					"Converter TestCustomConverter used incorrectly. Arguments passed in were:"
							+ destination + " and " + source);
		}
	}
}
