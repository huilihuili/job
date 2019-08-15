package vip.eagleli;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduleTest {
	static class MyRunnable implements Runnable {
		@Override
		public void run() {
			try {
				System.out.println("I'm coming...");
				Thread.sleep(5000);
				System.out.println("I'm leaving...");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) {
		ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(3);
		MyRunnable myRunnable = new MyRunnable();
		// newScheduledThreadPool.scheduleAtFixedRate(myRunnable, 0, 3000, TimeUnit.MILLISECONDS);
		newScheduledThreadPool.scheduleWithFixedDelay(myRunnable, 0, 3000, TimeUnit.MILLISECONDS);
	}	
}
