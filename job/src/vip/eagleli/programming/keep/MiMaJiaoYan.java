package vip.eagleli.programming.keep;

import java.util.Scanner;

public class MiMaJiaoYan {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		scanner.nextLine();
		for (int i = 0; i < n; i++) {
			String string = scanner.nextLine();
			boolean res = solve(string);
			if (res) {
				System.out.println("YES");
			} else {
				System.out.println("NO");
			}
		}
	}

	private static boolean solve(String s) {
		if (s.length() < 8) {
			return false;
		}

		if (judgeNumber(s.charAt(0))) {
			return false;
		}

		int[] types = new int[3];
		for (char c : s.toCharArray()) {
			if (judgeNumber(c)) {
				types[0] = 1;
				continue;
			}

			if (judgeBigAl(c)) {
				types[1] = 1;
				continue;
			}

			if (judgeSmallAl(c)) {
				types[2] = 1;
				continue;
			}
			return false;
		}
		return types[0] + types[1] + types[2] > 1 ? true : false;
	}

	private static boolean judgeNumber(char c) {
		return '0' <= c && c <= '9';
	}

	private static boolean judgeBigAl(char c) {
		return 'A' <= c && c <= 'Z';
	}

	private static boolean judgeSmallAl(char c) {
		return 'a' <= c && c <= 'z';
	}
}
