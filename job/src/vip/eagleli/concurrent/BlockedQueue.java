package vip.eagleli.concurrent;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BlockedQueue<T> {
	private Object[] items;

	private int addIndex, removeIndex, count;

	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();

	public BlockedQueue(int size) {
		items = new Object[size];
	}

	public void add(T t) throws InterruptedException {
		lock.lock();
		try {
			while (count == items.length) {
				notFull.await();
			}
			items[addIndex] = t;
			if (++addIndex == items.length) {
				addIndex = 0;
			}
			count++;
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	@SuppressWarnings("unchecked")
	public T remove() throws InterruptedException {
		lock.lock();
		try {
			while (count == 0) {
				notEmpty.await();
			}
			Object res = items[removeIndex];
			if (++removeIndex == items.length) {
				removeIndex = 0;
			}
			count--;
			notFull.signal(); 
			return (T) res;
		} finally {
			lock.unlock();
		}
	}
}
