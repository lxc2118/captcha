package hcy.captcha;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

public class CaptchaTest extends TestCase {

	public void erzhi() {
		try {
			BufferedImage image = ImageIO.read(new File("src/main/resources/1.jpg"));
			Integer width = image.getWidth();
			Integer height = image.getHeight();
			BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					int rgb = image.getRGB(i, j);
					grayImage.setRGB(i, j, rgb);
				}
			}

			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int pixel = grayImage.getRGB(x, y);
					if (pixel != -1) {
						System.out.print(0);
						// 这里对像素值的判断依赖于gc的前景色和背景色设置，通过判断则可以对字符上的每一个像素进行操作，生成点阵坐标序列(x,
						// y)
					}else{
						System.out.print(1);
					}
				}
				System.out.println("\n");
			}

//			File newFile = new File("src/main/resources/5.jpg");
//			ImageIO.write(grayImage, "jpg", newFile);

			// int[][] img = new int[width][height];
			// for(int i=0;i<width;i++)
			// for(int j=0;j<width;j++) {
			// int[] rgb = new int[3];
			// int pixel = src.getRGB(i, j);
			// rgb[0] = (pixel & 0xff0000) >> 16;
			// rgb[1] = (pixel & 0xff00) >> 8;
			// rgb[2] = (pixel & 0xff);
			// System.out.println("("+rgb[0]+","+rgb[0]+","+rgb[0]+")");
			// }

			// ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_GRAY);
			// ColorConvertOp op = new ColorConvertOp(cs, null);
			// src = op.filter(src, null);
			// ImageIO.write(src, "JPEG", new File("F:\\1.jpg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void rmInterferingLine() throws IOException {
		BufferedImage image = ImageIO.read(new File("src/main/resources/3.jpg"));
		Integer width = image.getWidth();
		Integer height = image.getHeight();
		// int[][] img = new int[width][height];
		int[][] img = convertImageToArray(image);
		// Color c = new Color(0, 0, 0);
		// System.out.println(c.getRGB());

		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Integer colorInt = image.getRGB(x, y);
				if (colorInt < -16000000) {
					img[y][x] = -16777216;
					System.out.print(1);
				} else {
					System.out.print(0);
				}
				// if(colorInt!=-1) {
				// Color color = new Color(colorInt);
				// System.out.println("red-" + color.getRed() + ";green-"
				// +color.getGreen() + ";blue-" + color.getBlue());
				// }
				// img[x][y] = isBlack(image.getRGB(y, x), 700);
			}
			System.out.println("\n");
		}
		writeImageFromArray("src/main/resources/4.jpg", "jpg", img);
	}

	public static int isBlack(int colorInt, int whiteThreshold) {
		final Color color = new Color(colorInt);
		if (color.getRed() + color.getGreen() + color.getBlue() <= whiteThreshold) {
			return 1;
		}
		return 0;
	}

	// int[][] rgbArray1 = convertImageToArray(bf);
	// 输出图片到指定文件
	// writeImageFromArray("c:\\tmp\\2.png", "png",
	// rgbArray1);//这里写你要输出的绝对路径+文件名
	public static BufferedImage readImage(String imageFile) {
		File file = new File(imageFile);
		BufferedImage bf = null;
		try {
			bf = ImageIO.read(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return bf;
	}

	public int[][] convertImageToArray(BufferedImage bf) {
		// 获取图片宽度和高度
		int width = bf.getWidth();
		int height = bf.getHeight();
		// 将图片sRGB数据写入一维数组
		int[] data = new int[width * height];
		bf.getRGB(0, 0, width, height, data, 0, width);
		// 将一维数组转换为为二维数组
		int[][] rgbArray = new int[height][width];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				rgbArray[i][j] = data[i * width + j];
		return rgbArray;
	}

	public void writeImageFromArray(String imageFile, String type, int[][] rgbArray) {
		// 获取数组宽度和高度
		int width = rgbArray[0].length;
		int height = rgbArray.length;
		// 将二维数组转换为一维数组
		int[] data = new int[width * height];
		for (int i = 0; i < height; i++)
			for (int j = 0; j < width; j++)
				data[i * width + j] = rgbArray[i][j];
		// 将数据写入BufferedImage
		BufferedImage bf = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		bf.setRGB(0, 0, width, height, data, 0, width);
		// 输出图片
		try {
			File file = new File(imageFile);
			ImageIO.write(bf, type, file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
