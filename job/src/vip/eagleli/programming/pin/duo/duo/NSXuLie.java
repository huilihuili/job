package vip.eagleli.programming.pin.duo.duo;

import java.util.Scanner;

public class NSXuLie {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), s = scanner.nextInt();
		System.out.println(solve(n, s));
	}

	private static int solve(int n, int s) {
		int res = 0;
		for (int i = 1; i <= s / n; i++) {
			res += solve(n, s, i, i, 1);
		}
		return res;
	}

	private static int solve(int n, int s, int cur, int curIndex, int count) {
		if (cur == s && count == n) {
			return 1;
		} else if (cur > s || count > n) {
			return 0;
		} else {
			int res = 0;
			for (int i = curIndex + 1; cur + i <= s; i++) {
				res += solve(n, s, cur + i, i, count + 1);
			}
			return res;
		}
	}
}
