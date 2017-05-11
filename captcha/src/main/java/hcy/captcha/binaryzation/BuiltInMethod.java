/**
 * 
 */
package hcy.captcha.binaryzation;

import java.awt.image.BufferedImage;

/**
 * @author zshongyi
 *
 */
public class BuiltInMethod implements Binaryzation {

	/* (non-Javadoc)
	 * @see hcy.captcha.binaryzation.Binaryzation#handle(java.awt.image.BufferedImage)
	 */
	public BufferedImage handle(BufferedImage image) {
		// TODO Auto-generated method stub
		return new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_BYTE_BINARY);
	}

}
