package myutils.gui;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;

public class MyImageUtils {
	public static ImageIcon scaleImageIconByHeight(ImageIcon image, int height) {
		int width = (int) Math.round(image.getIconWidth() * (height * 1.0 / image.getIconHeight()));
		return new ImageIcon(image.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

	public static Image scaleImageByHeight(Image image, int height) {
		BufferedImage bufferedImage = (BufferedImage) image;
		int width = (int) Math.round(bufferedImage.getWidth() * (height * 1.0 / bufferedImage.getHeight()));
		return image.getScaledInstance(width, height, image.SCALE_SMOOTH);
	}
}
