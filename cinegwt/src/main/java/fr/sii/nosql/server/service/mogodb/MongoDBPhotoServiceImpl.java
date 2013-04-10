package fr.sii.nosql.server.service.mogodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import fr.sii.nosql.server.repository.mongodb.MongoDBPhotoRepository;
import fr.sii.nosql.server.service.PhotoService;
import fr.sii.nosql.shared.buisiness.Photo;

@Profile("mongodb")
@Service("photoService")
public class MongoDBPhotoServiceImpl implements PhotoService {

	private final MongoDBPhotoRepository mongoDBPhotoRepository;

	@Autowired
	public MongoDBPhotoServiceImpl(MongoDBPhotoRepository mongoDBPhotoRepository) {
		this.mongoDBPhotoRepository = mongoDBPhotoRepository;
	}

	@Override
	public byte[] getPhoto(long id) {
		Photo photo = mongoDBPhotoRepository.findOne(id);
		if (photo != null) {
			return photo.getPicture();
		}
		return null;
	}

}
