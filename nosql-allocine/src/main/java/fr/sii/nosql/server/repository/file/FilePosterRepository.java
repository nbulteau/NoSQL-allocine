package fr.sii.nosql.server.repository.file;


public interface FilePosterRepository {

	boolean exists(Long posterId);

	void save(Picture poster);

	byte[] getPoster(long posterId);

}
