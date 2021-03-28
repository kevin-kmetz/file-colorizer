package FileColorizer;

import java.awt.Color;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;

public class FileColorizer {

	private int imageWidth;
	private int imageHeight;
	private int imagePixels;			// Simply imageWidth * imageHeight.

	private int numberOfFullPixels;	// Number of pixels to be extracted from file that won't need any padding in RGB.
	private int leftOverBytes;		// Number of bytes needed to make final pixel complete (0, 1, or 2).
	private int totalPixels;			// Number of pixels to be extracted from file, including extra bytes needed to get a full final pixel.

	public static void main(String[] args) {

		if (args.length == 1) {

			FileColorizer colorizer = new FileColorizer(args[0]);

		} else if (args.length == 2) {

			FileColorizer colorizer = new FileColorizer(args[0], Integer.parseInt(args[1]));

		}

	}

	FileColorizer(String fileName) {

		this(fileName, -1);

	}


	FileColorizer(String fileName, int imageWidth) {

		try {

			File file = new File(fileName);
			int fileSize = Math.toIntExact(file.length());

			numberOfFullPixels = fileSize / 3;
			leftOverBytes = fileSize % 3;

			totalPixels = numberOfFullPixels;

			if (leftOverBytes > 0) {

				totalPixels++;

			}

			this.imageWidth = imageWidth;

			if (imageWidth <= 0) {

				this.imageWidth = (int) Math.ceil(Math.sqrt((double) totalPixels));

			}

			imageHeight = totalPixels / this.imageWidth;

			if (totalPixels % this.imageWidth != 0) {
				imageHeight++;
			}

			imagePixels = imageWidth * imageHeight;

			System.out.println(this.imageWidth);
			System.out.println(imageHeight);
			System.out.println(totalPixels);

			int y = 0;
			int x = 0;
			int currentPixel = 0;

			int redValue;
			int greenValue;
			int blueValue;

			FileInputStream fileReader = new FileInputStream(file);
			BufferedImage fileAsImage = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);

			while (y < imageHeight && currentPixel < numberOfFullPixels) {

				while (x < imageWidth && currentPixel < numberOfFullPixels) {

					redValue = fileReader.read();
					greenValue = fileReader.read();
					blueValue = fileReader.read();

					Color color = new Color(redValue, greenValue, blueValue);

					fileAsImage.setRGB(x, y, color.getRGB());

					x++;
					currentPixel++;

				}

				y++;

			}

		} catch (Exception e) {

			System.out.println("Error in program execution!");
			e.printStackTrace();

		}

	}


}