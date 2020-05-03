package vip.eagleli.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LeetCode202 {
	public static void main(String[] args) {
		LeetCode202 leetCode202 = new LeetCode202();
		leetCode202.isHappy(19);
	}

	public boolean isHappy(int n) {
		Set<Integer> set = new HashSet<>();
		set.add(n);
		while (true) {
			int sum = 0;
			while (n > 0) {
				int mod = n % 10;
				sum += (mod * mod);
				n /= 10;
			}
			if (sum == 1) {
				return true;
			}
			if (set.contains(sum)) {
				return false;
			}
			set.add(sum);
			n = sum;
		}
	}

	public boolean isHappy_(int n) {
		Set<Integer> seen = new HashSet<>();
		while (n != 1 && !seen.contains(n)) {
			seen.add(n);
			n = getNext(n);
		}
		return n == 1;
	}

	private int getNext(int n) {
		int totalSum = 0;
		while (n > 0) {
			int d = n % 10;
			n = n / 10;
			totalSum += d * d;
		}
		return totalSum;
	}

}
