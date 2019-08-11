package vip.eagleli.programming.bei.ke;

import java.util.Scanner;

public class YueGuangBaoHeDeMiMa {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println(solve(n, arr));
	}

	private static int solve(int n, int[] arr) {
		int[] dp = new int[n];
		int res = 1;
		for (int i = 0; i < n; i++) {
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (arr[i] > arr[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}
}
