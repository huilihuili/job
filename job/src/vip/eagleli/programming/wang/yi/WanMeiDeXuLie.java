package vip.eagleli.programming.wang.yi;

import java.util.Scanner;

public class WanMeiDeXuLie {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int t = scanner.nextInt();
		for (int i = 0; i < t; i++) {
			int n = scanner.nextInt();
			int[] arr = new int[n];
			for (int j = 0; j < n; j++) {
				arr[j] = scanner.nextInt();
			}
			System.out.println(solve(n, arr));
		}
	}

	private static int solve(int n, int[] arr) {
		if (arr == null || arr.length == 0) {
			return 0;
		}

		if (n == 1) {
			return 1;
		}

		int[] dp = new int[n], sum = new int[n];
		int res = 0;
		dp[0] = 1;
		sum[0] = arr[0];
		for (int i = 1; i < n; i++) {
			if (arr[i] >= sum[i - 1]) {
				dp[i] = dp[i - 1] + 1;
				sum[i] = sum[i - 1] + arr[i];
			} else {
				dp[i] = 1;
				sum[i] = arr[i];
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
