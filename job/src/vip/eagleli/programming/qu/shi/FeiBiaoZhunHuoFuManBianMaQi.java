package vip.eagleli.programming.qu.shi;

import java.util.HashMap;
import java.util.PriorityQueue;

public class FeiBiaoZhunHuoFuManBianMaQi {
	static class Node {
		char c;
		int count;

		public Node(char c, int count) {
			super();
			this.c = c;
			this.count = count;
		}

		@Override
		public String toString() {
			return c + " " + count;
		}
	}

	public static void main(String[] args) {
		System.out.println(solve("adsfaerwkeljfasodfjoqweirfaskdh"));
	}

	private static int solve(String string) {
		if (string == null || string.length() == 0) {
			return 0;
		}

		char[] cs = string.toCharArray();
		HashMap<Character, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < cs.length; i++) {
			if (hashMap.containsKey(cs[i])) {
				hashMap.put(cs[i], hashMap.get(cs[i]) + 1);
			} else {
				hashMap.put(cs[i], 1);
			}
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		for (Integer integer : hashMap.values()) {
			pq.offer(integer);
		}
		int res = 0, a, b, c;
		while (true) {
			a = pq.poll();
			if (pq.isEmpty()) {
				break;
			}
			b = pq.poll();
			if (pq.isEmpty()) {
				res += a + b;
				break;
			}
			c = pq.poll();
			res += a + b + c;
			pq.offer(a + b + c);
		}
		return res;
	}
}
