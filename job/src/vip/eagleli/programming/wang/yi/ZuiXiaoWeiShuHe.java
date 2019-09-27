package vip.eagleli.programming.wang.yi;

import java.util.Scanner;

public class ZuiXiaoWeiShuHe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int x = scanner.nextInt();
			System.out.println(solve(x));
		}
	}

	private static int solve(int x) {
		if (0 < x && x < 10) {
			return x;
		}

		int res = 10;
		while (true) {
			if (sum(res++) >= x) {
				return res - 1;
			}
		}
	}

	private static int sum(int num) {
		int res = 0;
		while (num > 0) {
			res += num % 10;
			num /= 10;
		}
		return res;
	}
}
