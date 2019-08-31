package vip.eagleli.leetcode;

public class LeetCode50 {
	public double myPow(double x, int n) {
		boolean flag = false;
		if (n < 0) {
			n = -1 * n;
			flag = true;
		}
		return flag ? 1 / fastPow(x, n) : fastPow(x, n);
	}

	public double fastPow(double x, int n) {
		if (n == 0) {
			return 1;
		}
		double half = fastPow(x, n / 2);
		if ((n & 1) == 0) {
			return half * half;
		}
		return half * half * x;
	}
}
