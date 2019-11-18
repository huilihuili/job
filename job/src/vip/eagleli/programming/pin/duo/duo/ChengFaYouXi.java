package vip.eagleli.programming.pin.duo.duo;

import java.util.LinkedList;
import java.util.Scanner;

public class ChengFaYouXi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int a = scanner.nextInt(), b = scanner.nextInt();
			System.out.println(solve2(a, b));
		}
	}

	private static int solve2(int a, int b) {
		if (a == b) {
			return 0;
		}
		if (a > b) {
			return -1;
		}
		int res1 = solve2(a * 10 + 1, b);
		int res2 = solve2(a * 2, b);
		if (res1 == -1 && res2 == -1) {
			return -1;
		}
		if (res1 == -1) {
			return res2 + 1;
		}
		if (res2 == -1) {
			return res1 + 1;
		}
		return 1 + Math.min(res1, res2);
	}

	private static int solve(int a, int b) {
		if (a == b) {
			return 0;
		}
		int count = 0;
		while (true) {
			int t = a * 10 + 1;
			if (t == b) {
				return count + 1;
			}
			if (t < b) {
				a = t;
				count++;
				continue;
			}

			t = a * 2;
			if (t == b) {
				return count + 1;
			}
			if (t < b) {
				a = t;
				count++;
				continue;
			}
			return -1;
		}
	}
}
