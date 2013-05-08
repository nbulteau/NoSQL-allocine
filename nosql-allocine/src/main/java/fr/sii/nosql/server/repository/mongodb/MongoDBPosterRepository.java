package fr.sii.nosql.server.repository.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import fr.sii.nosql.shared.buisiness.Poster;

@Transactional
public interface MongoDBPosterRepository extends MongoRepository<Poster, Long> {
}
