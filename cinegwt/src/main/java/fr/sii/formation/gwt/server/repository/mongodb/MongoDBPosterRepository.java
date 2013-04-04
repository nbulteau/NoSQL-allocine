package fr.sii.formation.gwt.server.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.formation.gwt.shared.buisiness.Poster;

@Transactional
public interface MongoDBPosterRepository extends MongoRepository<Poster, Long> {
}
