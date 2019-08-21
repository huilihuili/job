package vip.eagleli.concurrent;

public class ThreadTest {
	public static void main(String[] args) {
		Thread thread = new Thread(() -> {
			System.out.println("hello, world");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("end...");
		});
		thread.start();
		thread.start();
	}
}
