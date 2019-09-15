package vip.eagleli.concurrent;

import org.junit.Test;

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

	@Test
	public void threadRunTest() {
		Thread thread = new Thread() {
			@Override
			public void run() {
				System.out.println("=====");
			}
		};
		thread.start();
		System.out.println("hhhh");
	}

}
