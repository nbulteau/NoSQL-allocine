package fr.sii.formation.gwt.server;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class MovieHelper {
	private static final double HEIGHT = 213;

	public static byte[] scale(byte[] poster) throws IOException {
		byte[] imageInByte = null;

		if (poster != null) {
			InputStream in = new ByteArrayInputStream(poster);
			BufferedImage bufferedImage = ImageIO.read(in);
			double scaleValue = HEIGHT / bufferedImage.getHeight();

			AffineTransform tx = new AffineTransform();
			tx.scale(scaleValue, scaleValue);
			AffineTransformOp op = new AffineTransformOp(tx,
					AffineTransformOp.TYPE_BILINEAR);
			BufferedImage biNew = new BufferedImage(
					(int) (bufferedImage.getWidth() * scaleValue),
					(int) (bufferedImage.getHeight() * scaleValue),
					bufferedImage.getType());

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(op.filter(bufferedImage, biNew), "jpg", baos);
			baos.flush();
			imageInByte = baos.toByteArray();
			baos.close();
		}
		return imageInByte;
	}

	/**
	 * Download the picture
	 * 
	 * @param href
	 *            picture URL
	 * @return byte array of the download picture
	 * @throws IOException
	 */
	public static byte[] downloadPicture(String href) throws IOException {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		if (href != null) {
			URL url = new URL(href);
			InputStream in = url.openStream();

			byte[] buf = new byte[1024 * 1024];
			int length;
			while ((length = in.read(buf)) != -1) {
				out.write(buf, 0, length);
			}
			in.close();
			out.close();
		}
		return out.toByteArray();
	}

}
