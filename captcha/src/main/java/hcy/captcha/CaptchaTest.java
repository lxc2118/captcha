package hcy.captcha;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class CaptchaTest {

	public static void main(String[] args) {
		try {
            BufferedImage image = ImageIO.read(new File("src/main/resources/1.jpg"));
            Integer width = image.getWidth();
            Integer height = image.getHeight();
            BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
            for(int i= 0 ; i < width ; i++){  
                for(int j = 0 ; j < height; j++){  
                int rgb = image.getRGB(i, j);  
                grayImage.setRGB(i, j, rgb);  
                }  
            }  
              
            File newFile = new File("src/main/resources/2.jpg");  
            ImageIO.write(grayImage, "jpg", newFile);  
            

            
//            int[][] img = new int[width][height];
//            for(int i=0;i<width;i++)
//            	for(int j=0;j<width;j++) {
//            		int[] rgb = new int[3];
//            		int pixel = src.getRGB(i, j);
//                    rgb[0] = (pixel & 0xff0000) >> 16;  
//                    rgb[1] = (pixel & 0xff00) >> 8;  
//                    rgb[2] = (pixel & 0xff); 
//                    System.out.println("("+rgb[0]+","+rgb[0]+","+rgb[0]+")");
//            	}
            		
            		
//            ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
//            ColorConvertOp op = new ColorConvertOp(cs, null);
//            src = op.filter(src, null);
//            ImageIO.write(src, "JPEG", new File("F:\\1.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
