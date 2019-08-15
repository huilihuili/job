package vip.eagleli.leetcode;

public class LeetCode29 {
	public static void main(String[] args) {
		System.out.println(divide(-2147483648, -1));
	}

	public static int divide(int dividend, int divisor) {
		boolean sign = (dividend > 0 && divisor > 0) || (dividend < 0 && divisor < 0);
		long dt = Math.abs((long) dividend);
		long di = Math.abs((long) divisor);
		System.out.println(dt + " " + di);
		long res = 0;
		while (dt >= di) {
			res++;
			dt -= di;
		}
		if (res > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		return sign ? (int) res : (int) (-1 * res);
	}
}
