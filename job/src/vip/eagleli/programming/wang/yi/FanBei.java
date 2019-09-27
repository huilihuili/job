package vip.eagleli.programming.wang.yi;

import java.util.Scanner;

public class FanBei {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			long a = scanner.nextLong();
			long b = scanner.nextLong();
			long p = scanner.nextLong();
			long q = scanner.nextLong();
			System.out.println(solve(a, b, p, q));
		}
	}

	private static int solve(long a, long b, long p, long q) {
		if (a + p >= b) {
			return 1;
		}
		return 1 + solve(a, b, p * q, q);
	}

	private static int solve2(long a, long b, long p, long q) {
		if (a >= b) {
			return 0;
		}
		if (a + p >= b) {
			return 1;
		}

		int res1 = 1;
		res1 += solve(a + p, b, p, q);
		int res2 = 1;
		res2 += solve(a, b, p * q, q);
		return Math.min(res1, res2);
	}

}
