package yzkf.test;

import java.io.IOException;

import com.sun.image.codec.jpeg.ImageFormatException;

import yzkf.exception.ParserConfigException;
import yzkf.utils.ImageTool;

public class ImageToolTest {
	public static void main(String[] args) throws ParserConfigException{
		try {
			ImageTool.createThumbnail("f:\\search.png", 200, 200,false, "f:\\search4.png");
			//ImageTool.createThumbnail("f:\\test1.jpg", 200, 200, false, "f:\\thumb1.jpg");
			//ImageTool.createJPEGThumbnail("f:\\test1.jpg", 200, 200,false, 100, "f:\\thumb2.jpg");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
