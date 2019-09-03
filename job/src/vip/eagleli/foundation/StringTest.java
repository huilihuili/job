package vip.eagleli.foundation;

import org.junit.Test;

public class StringTest {
	public static void main(String[] args) {
		String str = null;
		System.out.println(str);
	}

	@Test
	public void compareToTest() {
		String str1 = "ÄãºÃ°¡";
		String str2 = "ÎÒÊÇË­";
		for (int i = 0; i < str1.length(); i++) {
			System.out.println(str1.charAt(i));
			System.out.println(str2.charAt(i));
			System.out.println(str1.charAt(i) - str2.charAt(i));
		}
	}
}
