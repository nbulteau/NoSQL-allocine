package fr.sii.formation.gwt.server.repository.jpa;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

@Profile("jpa")
@Repository("genreDAO")
public class GenreDAOImpl extends GenericDAOWithJPA<Genre, String> implements GenreDAO {
}
