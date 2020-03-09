package vip.eagleli.leetcode;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode59 {

	public static void main(String[] args) {
		MaxQueue maxQueue = new MaxQueue();
		// ["","","","",","","","","","","","","","pop_front","max_value",
		System.out.println(maxQueue.max_value());
		System.out.println(maxQueue.pop_front());
		System.out.println(maxQueue.max_value());
		maxQueue.push_back(46);
		System.out.println(maxQueue.max_value());
		System.out.println(maxQueue.pop_front());
		System.out.println(maxQueue.max_value());
		System.out.println(maxQueue.pop_front());
		maxQueue.push_back(868);
		System.out.println(maxQueue.pop_front());
		System.out.println(maxQueue.pop_front());
		System.out.println(maxQueue.pop_front());
		maxQueue.push_back(525);
		System.out.println(maxQueue.pop_front());
		System.out.println(maxQueue.max_value());
	}

	static class MaxQueue {
		Queue<Integer> q;
		Deque<Integer> d;

		public MaxQueue() {
			q = new LinkedList<>();
			d = new LinkedList<>();
		}

		public int max_value() {
			return d.isEmpty() ? -1 : d.peekFirst();
		}

		public void push_back(int value) {
			while (!d.isEmpty() && d.peekLast() < value) {
				d.pollLast();
			}
			d.offerLast(value);
			q.offer(value);
		}

		public int pop_front() {
			if (q.isEmpty()) {
				return -1;
			}
			if (q.peek().equals(d.peekFirst())) {
				d.pollFirst();
			}
			return q.poll();
		}
	}

}
