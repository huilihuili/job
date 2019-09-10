package vip.eagleli.programming.wang.yi;

import java.util.Scanner;

public class KaFei {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			int k = scanner.nextInt();
			int m = scanner.nextInt();
			int[] arr = new int[m];
			for (int j = 0; j < m; j++) {
				arr[j] = scanner.nextInt();
			}
			System.out.println(solve(k, m, arr));
		}
	}

	private static int solve(int k, int m, int[] arr) {
		if (k == 0) {
			return 30;
		}
		if (m == 0) {
			return value(1, 30, k);
		}
		int res = m;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i - 1] + 1 == arr[i]) {
				continue;
			}
			res += value(arr[i - 1], arr[i], k);
		}
		res += value(1, arr[0], k);
		return res;
	}

	private static int value(int start, int end, int step) {
		return (end - start - 1) / (step + 1);
	}
}
