package FileColorizer;

import java.io.File;

public class FileColorizer {

	int imageWidth;
	int imageHeight;

	int numberOfFullPixels;		// Number of pixels that won't need any padding in RGB.
	int leftOverBytes;			// Number of bytes needed to make final pixel complete (0, 1, or 2).
	int totalPixels;			// Number of pixels, including extra bytes needed to get a full final pixel.

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

		System.out.println(this.imageWidth);
		System.out.println(imageHeight);
		System.out.println(totalPixels);

	}


}