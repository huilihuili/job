package vip.eagleli.foundation;

import org.junit.Test;

public class StringTest {
	public static void main(String[] args) {
		String str1 = "abc";
		String str2 = "efg";
		String str3 = "xyz";
		String str = str1 + str2 + str3;
		System.out.println(str);

		for (int i = 0; i < 10; i++) {
			str += i;
		}
		System.out.println(str);

		// String str = null;
		// System.out.println(str);
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

	@Test
	public void stringAndStringBuilder() {
		String str1 = "abc";
		String str2 = "efg";
		String str3 = "xyz";
		String str = str1 + str2 + str3;
		System.out.println(str);
		for (int i = 0; i < 10; i++) {
			str2 += i;
		}
		System.out.println(str2);
	}
}
