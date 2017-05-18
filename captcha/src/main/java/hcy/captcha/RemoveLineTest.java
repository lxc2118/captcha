package hcy.captcha;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import junit.framework.TestCase;

public class RemoveLineTest extends TestCase {

	
	private int [][] a = new int [300][80];
	private int[] b = new int [300];
	private int M = 300;
	private int N = 80;
	private int threshold = 250;
	private int offset = 75;
	private BufferedImage renderImg;
	
	public void cut() {
		a = getImg();
		this.genLine(0);
//		for(int y=0;y<80;y++) {
//			for(int x=0;x<300;x++)
//				System.out.print(array[y][x]);
//			System.out.println("\n");
//		}
		
		try {
			final BufferedImage img = ImageIO.read(new File("src/main/resources/5.jpg"));
			this.renderImg = img;
			ImageIO.write(this.renderImg, "JPG", new File("src/main/resources/noiseRender.jpg"));
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}
	

	public void genLine(int n) {
		if (n < this.offset) {
			this.b[n] = -1;
			this.genLine(n + 1);
		}
		if (n == this.M) {
			for (int i = 0; i < this.M; i++) {
				System.out.print(this.b[i] + " ");

			}
			System.out.println("");
		}
		if (n == this.offset) {
			for (int j = 0; j < this.N; j++) {
				if (this.a[this.offset][j] == 1) {
					this.b[this.offset] = j;
					this.genLine(n + 1);
				}
			}
		}
		if (n > 0 && n < this.M) {
			int hasMore = 0;
			if (this.b[n - 1] > 0 && this.b[n - 1] < this.N && this.a[n][this.b[n - 1]] == 1) {
				this.b[n] = this.b[n - 1];
				hasMore = 1;
				this.genLine(n + 1);
			} else {
				if (this.b[n - 1] > 0 && this.a[n][this.b[n - 1] - 1] == 1) {
					this.b[n] = this.b[n - 1] - 1;
					hasMore = 1;
					this.genLine(n + 1);
				}
				if (this.b[n - 1] < this.N - 1 && this.a[n][this.b[n - 1] + 1] == 1) {
					this.b[n] = this.b[n - 1] + 1;
					hasMore = 1;
					this.genLine(n + 1);
				}
			}
			if (n - this.offset > this.threshold && hasMore == 0) {
				for (int i = 0; i < n; i++) {
					if (this.b[i] > 0) {
						this.renderImg.setRGB(this.b[i], i, Color.RED.getRGB());
					}
				}
			}
		}

	}
	
	public int[][] getImg() {
		BufferedImage image;
		try {
			image = ImageIO.read(new File("src/main/resources/1.jpg"));
			Integer width = image.getWidth();
			Integer height = image.getHeight();
			BufferedImage grayImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_BINARY);
			for (int i = 0; i < width; i++) {
				for (int j = 0; j < height; j++) {
					int rgb = image.getRGB(i, j);
					grayImage.setRGB(i, j, rgb);
				}
			}
			int[][] array = new int[height][width];
			for (int y = 0; y < height; y++) {
				for (int x = 0; x < width; x++) {
					int pixel = grayImage.getRGB(x, y);
					if (pixel != -1) {
//						System.out.print(0);
						array[y][x]=1;
						// 这里对像素值的判断依赖于gc的前景色和背景色设置，通过判断则可以对字符上的每一个像素进行操作，生成点阵坐标序列(x,
						// y)
					}else{
//						System.out.print(1);
						array[y][x]=0;
					}
				}
//				System.out.println("\n");
				
			}
			return array;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
