package FileColorizer;

import java.io.File;

public class FileColorizer {

	int imageWidth;
	int imageHeight;

	int numberOfFullPixels;
	int leftOverBytes;
	int totalPixels;

	public static void main(String[] args) {

		FileColorizer colorizer = new FileColorizer(args[0]);

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

		System.out.println(this.imageWidth);

	}


}