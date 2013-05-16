package fr.sii.nosql.server.repository.file;

import java.util.List;

public interface FilePhotoRepository {

	byte[] getPhoto(long photoId);

	void save(Picture photo);

	void save(List<Picture> photoListToSave);

	boolean exists(Long pictureId);
}
