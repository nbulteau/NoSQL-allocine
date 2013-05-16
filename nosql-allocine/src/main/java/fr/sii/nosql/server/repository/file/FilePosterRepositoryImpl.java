package fr.sii.nosql.server.repository.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

@Repository("filePosterRepository")
public class FilePosterRepositoryImpl extends FileRepository<Picture> implements
		FilePosterRepository {

	private final static Logger LOG = LoggerFactory
			.getLogger(FilePosterRepositoryImpl.class);

	@Autowired
	public FilePosterRepositoryImpl(
			@Value("${fileposters.path}") String repositoryPath,
			@Value("${fileposters.suffix}") String suffix) {
		super(repositoryPath, suffix);
	}

	@Override
	public byte[] getPoster(long posterId) {
		byte[] poster = null;
		File file = getFile(posterId);

		try {
			FileInputStream fis = new FileInputStream(file);
			fis.read(poster);
			fis.close();
		} catch (IOException e) {
			LOG.error("Was loading " + file.getName(), e);
			return null;
		}
		return poster;
	}

	@Override
	public void save(Picture poster) {
		File file = getFile(poster.getPictureId());
		try {
			FileOutputStream fos = new FileOutputStream(file);
			fos.write(poster.getPicture());
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Picture load(File file) {
		Picture picture = null;

		Long posterId = extractId(file);
		byte[] poster = getPoster(posterId);
		if (poster != null) {
			picture = new Picture(posterId, poster);
		}
		return picture;
	}
}
