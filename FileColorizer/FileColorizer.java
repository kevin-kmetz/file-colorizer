package FileColorizer;

import java.io.File;

public class FileColorizer {

	public static void main(String[] args) {

		FileColorizer colorizer = new FileColorizer(args[0]);

	}


	FileColorizer(String fileName) {

		File file = new File(fileName);

		System.out.println("File size: " + file.length());

	}


}