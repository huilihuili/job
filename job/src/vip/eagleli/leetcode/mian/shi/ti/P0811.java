package vip.eagleli.leetcode.mian.shi.ti;

public class P0811 {
	public static void main(String[] args) {
		P0811 p0811 = new P0811();
		System.out.println(p0811.waysToChange(10));
		System.out.println(p0811.waysToChange2(10));
	}

	/**
	 * 超时
	 * 
	 * @param n
	 * @return
	 */
	public int waysToChange(int n) {
		return waysToChange(n, n);
	}

	public int waysToChange(int n, int crn) {
		if (n < 0) {
			return 0;
		}
		if (n == 0 || crn == 1) {
			return 1;
		}
		int[] coins = { 1, 5, 10, 25 };
		int ans = 0, mod = 1_000_000_007;
		for (int i = coins.length - 1; i >= 0; i--) {
			if (coins[i] <= crn) {
				ans += waysToChange(n - coins[i], coins[i]);
				ans %= mod;
			}
		}
		return ans;
	}

	public int waysToChange2(int n) {
		int[][] dp = new int[4][n + 1];
		int[] coins = { 1, 5, 10, 25 };
		int mod = 1_000_000_007;

		for (int i = 0; i <= n; i++) {
			dp[0][i] = 1;
		}
		for (int i = 0; i < 4; i++) {
			dp[i][0] = 1;
		}

		for (int i = 1; i < 4; i++) {
			for (int j = 1; j <= n; j++) {
				if (j >= coins[i]) {
					dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i]]) % mod;
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}
		return dp[coins.length - 1][n];
	}

	public int waysToChange2_(int n) {
		int[] dp = new int[n + 1];
		int[] coins = { 1, 5, 10, 25 };
		int mod = 1_000_000_007;

		for (int i = 0; i <= n; i++) {
			dp[i] = 1;
		}

		for (int i = 1; i < 4; i++) {
			for (int j = 1; j <= n; j++) {
				if (j >= coins[i]) {
					dp[j] = (dp[j] + dp[j - coins[i]]) % mod;
				}
			}
		}
		return dp[n];
	}

}
