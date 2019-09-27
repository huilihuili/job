package vip.eagleli.programming.hua.wei;

import java.util.Scanner;

public class XuLieHeFa {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()) {
			String string = scanner.nextLine();
			String[] strings = string.split(" ");
			int[] arr = new int[strings.length];
			for (int i = 0; i < strings.length; i++) {
				arr[i] = Integer.valueOf(strings[i]);
			}
			System.out.print(solve(arr) + " ");
		}
	}

	private static boolean solve(int[] arr) {
		if (solve1(arr)) {
			return true;
		}

		if (solve2(arr)) {
			return true;
		}

		return false;
	}

	private static boolean solve1(int[] arr) {
		if (arr.length == 1) {
			return true;
		}

		int flag = solve(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			if (solve(arr[i]) == (1 - flag)) {
				flag = 1 - flag;
				continue;
			}
			return false;
		}
		return true;
	}

	private static boolean solve2(int[] arr) {
		if (arr.length == 1) {
			return true;
		}

		if (solve(arr[0]) != solve(arr[arr.length - 1])) {
			return false;
		}

		int flag = 1 - solve(arr[0]);
		for (int i = 1; i < arr.length - 1; i++) {
			if (solve(arr[i]) != flag) {
				return false;
			}
		}
		return true;
	}

	private static int solve(int i) {
		if (0 < i && i < 10) {
			return 0;
		}
		return 1;
	}
}
