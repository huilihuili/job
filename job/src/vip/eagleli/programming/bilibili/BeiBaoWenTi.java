package vip.eagleli.programming.bilibili;

import java.util.Scanner;

public class BeiBaoWenTi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), m = scanner.nextInt();
		int[] w = new int[n], v = new int[n];
		for (int i = 0; i < n; i++) {
			w[i] = scanner.nextInt();
		}
		for (int i = 0; i < n; i++) {
			v[i] = scanner.nextInt();
		}
		System.out.println(solve(n, m, w, v));
	}

	private static int solve(int n, int m, int[] w, int[] v) {
		int[] dp = new int[m + 1];
		for (int i = 0; i < n; i++) {
			for (int j = m; j >= 1; j--) {
				if (j >= w[i]) {
					dp[j] = Math.max(dp[j], dp[j - w[i]] + v[i]);
				}
			}
		}
		return dp[m];
	}
}
