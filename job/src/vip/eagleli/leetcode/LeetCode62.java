package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode62 {
	public static void main(String[] args) {
		int i = 0010;
		
		String string = "\15";
		char c = '\u0008';
		System.out.println(i);
		System.out.println(string);
		System.out.println(c);
	}

	/**
	 * 超时
	 * 
	 * @param n
	 * @param m
	 * @return
	 */
	public int lastRemaining(int n, int m) {
		List<Integer> list = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		int index = -1;
		while (list.size() != 1) {
			for (int i = 0; i < m; i++) {
				index++;
				if (index == list.size()) {
					index = 0;
				}
			}
			list.remove(index--);
		}
		return list.get(0);
	}

	public int lastRemaining2(int n, int m) {
		List<Integer> list = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			list.add(i);
		}
		int index = 0;
		while (list.size() != 1) {
			list.remove(index = (index + m - 1) % list.size());
		}
		return list.get(0);
	}

	public int lastRemaining3(int n, int m) {
		return f(n, m);
	}

	private int f(int n, int m) {
		if (n == 1) {
			return 0;
		}
		return (f(n - 1, m) + m) % n;
	}

	public int lastRemaining4(int n, int m) {
		int ans = 0;
		for (int i = 2; i <= n; i++) {
			ans = (ans + m) % i;
		}
		return ans;
	}

}