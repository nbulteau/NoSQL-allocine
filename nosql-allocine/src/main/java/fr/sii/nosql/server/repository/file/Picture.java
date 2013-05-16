package fr.sii.nosql.server.repository.file;

public class Picture {

	private Long pictureId;

	private byte[] picture;

	public Long getPictureId() {
		return pictureId;
	}

	public byte[] getPicture() {
		return picture;
	}

	public Picture(Long pictureId, byte[] picture) {
		this.pictureId = pictureId;
		this.picture = picture;
	}

}
