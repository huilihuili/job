package test.vip.eagleli;

public class TimeTest {
	public static void main(String[] args) throws InterruptedException {
		System.out.println(System.currentTimeMillis() / 1000);
		Thread.sleep(1000);
		System.out.println(System.currentTimeMillis() / 1000);
		Thread.sleep(1000);
		System.out.println(System.currentTimeMillis() / 1000);
		Thread.sleep(1000);
	}
}
