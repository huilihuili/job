package vip.eagleli.concurrent;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DeadLock {
	static CountDownLatch countDownLatch1 = new CountDownLatch(1);
	static CountDownLatch countDownLatch2 = new CountDownLatch(1);
	
	static Object lock1 = new Object();
	static Object lock2 = new Object();

	public static void main(String[] args) {
		ExecutorService threadPool = Executors.newFixedThreadPool(2);
		threadPool.execute(() -> {
			try {
				countDownLatch1.await();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (lock1) {
				countDownLatch2.countDown();
				synchronized (lock2) {
				}
			}
		});
		threadPool.execute(() -> {
			synchronized (lock2) {
				countDownLatch1.countDown();
				try {
					countDownLatch2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				synchronized (lock1) {
				}
			}
		});
		threadPool.shutdown();
	}
}
