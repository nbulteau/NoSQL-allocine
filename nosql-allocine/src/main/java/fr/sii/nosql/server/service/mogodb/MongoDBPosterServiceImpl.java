package fr.sii.nosql.server.service.mogodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.sii.nosql.server.repository.mongodb.MongoDBPosterRepository;
import fr.sii.nosql.server.service.PosterService;
import fr.sii.nosql.shared.buisiness.Poster;

@Profile("mongodb")
@Service("posterService")
public class MongoDBPosterServiceImpl implements PosterService {

	private final MongoDBPosterRepository mongoDBPosterRepository;

	@Autowired
	public MongoDBPosterServiceImpl(MongoDBPosterRepository mongoDBPosterRepository) {
		this.mongoDBPosterRepository = mongoDBPosterRepository;

	}

	@Override
	public byte[] getPoster(long id) {
		Poster poster = mongoDBPosterRepository.findOne(id);

		// DEBUG
		// AlloCinePicture picture = pictureRepository.findOne("pos" + id);
		// if (picture != null) {
		// return picture.getPicture();
		// } else {
		// return null;
		// }

		if (poster != null) {
			return poster.getPicture();
		}
		return null;
	}
}