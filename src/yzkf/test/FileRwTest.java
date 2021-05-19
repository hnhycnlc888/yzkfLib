package yzkf.test;

import java.io.File;
import java.io.IOException;

import yzkf.utils.FileRW;

public class FileRwTest {
	public static void main(String[] args) throws IOException{
//		File file = new File("F:\\testfilerw\\utf8.txt");
//		System.out.println( file.getAbsolutePath() );
//		System.out.println( file.getCanonicalPath() );
//		System.out.println( file.getFreeSpace() );
//		System.out.println( file.getName() );
//		System.out.println( file.getParent() );
//		System.out.println( file.getPath() );
//		System.out.println( file.getAbsoluteFile().getAbsolutePath() );
//		System.out.println( file.getParentFile().getAbsolutePath() );
		//FileRW.append("F:\\testfilerw\\utf8.txt", "222bbbbcccc", "utf-8");
		System.out.println(FileRW.readString("F:\\testfilerw\\utf8.txt"));
//		System.out.print("123");
//		System.out.println("456");
//		System.out.print("789");
	}
}
