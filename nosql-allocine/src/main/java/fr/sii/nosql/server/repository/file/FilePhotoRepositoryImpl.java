package fr.sii.nosql.server.repository.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("filePhotoRepository")
public class FilePhotoRepositoryImpl extends FileRepository<Picture> implements
		FilePhotoRepository {

	private final static Logger LOG = LoggerFactory
			.getLogger(FilePhotoRepositoryImpl.class);

	@Autowired
	public FilePhotoRepositoryImpl(
			@Value("${filephotos.path}") String repositoryPath,
			@Value("${filephotos.suffix}") String suffix) {
		super(repositoryPath, suffix);
	}

	@Override
	public byte[] getPhoto(long photoId) {
		byte[] photo = null;
		File file = getFile(photoId);

		try {
			FileInputStream fis = new FileInputStream(file);
			fis.read(photo);
			fis.close();
		} catch (IOException e) {
			LOG.error("Was loading " + file.getName(), e);
			return null;
		}
		return photo;
	}

	@Override
	public void save(Picture photo) {
		File file = getFile(photo.getPictureId());
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(photo.getPicture());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void save(List<Picture> photoListToSave) {
		for (Picture picture : photoListToSave) {
			save(picture);
		}
	}

	@Override
	protected Picture load(File file) {
		Picture picture = null;

		Long photoId = extractId(file);
		byte[] photo = getPhoto(photoId);
		if (photo != null) {
			picture = new Picture(photoId, photo);
		}
		return picture;
	}
}
