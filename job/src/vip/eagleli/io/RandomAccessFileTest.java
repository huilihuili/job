package vip.eagleli.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileTest {
	public static void main(String[] args) throws IOException {
		RandomAccessFile randomAccessFile = new RandomAccessFile("abc", "rw");
		randomAccessFile.writeChar(12);
		randomAccessFile.writeInt(96);
		randomAccessFile.writeInt(13);
		randomAccessFile.seek(6);
		System.out.println(randomAccessFile.readInt());
	}
}
