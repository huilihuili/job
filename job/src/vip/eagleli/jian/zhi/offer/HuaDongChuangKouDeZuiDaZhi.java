package vip.eagleli.jian.zhi.offer;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

public class HuaDongChuangKouDeZuiDaZhi {
	public static void main(String[] args) {
		int[] num = { 2, 3, 4, 2, 6, 2, 5, 1 };
		int size = 3;
		System.out.println(maxInWindows(num, size));
	}

	public static ArrayList<Integer> maxInWindows(int[] num, int size) {
		ArrayList<Integer> res = new ArrayList<>();
		Deque<Integer> deque = new LinkedList<>();
		deque.offer(num[0]);
		if (size == 1) {
			res.add(deque.peek());
		}
		for (int i = 1; i < num.length; i++) {
			if (deque.peekFirst() < num[i]) {
				deque.pollFirst();
				deque.offerFirst(num[i]);
			} else {
				while (deque.peekLast() < num[i]) {
					deque.pollLast();
				}
				deque.offerLast(num[i]);
			}
			if (i - size >= 0 && num[i - size] == deque.peek()) {
				deque.pollFirst();
			}
			if (i >= size - 1) {
				res.add(deque.peek());
			}
		}
		return res;
	}

}
