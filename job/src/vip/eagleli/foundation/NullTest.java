package vip.eagleli.foundation;

public class NullTest {
	public static void main(String[] args) {
		String str = null;
		try {
			str = "123";
		} catch (Exception e) {
		}
		System.out.println(str);
	}
}
