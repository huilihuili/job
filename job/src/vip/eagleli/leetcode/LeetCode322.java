package vip.eagleli.leetcode;

import java.util.Arrays;

public class LeetCode322 {
	public static void main(String[] args) {
		LeetCode322 leetCode322 = new LeetCode322();
		int amount = 11;
		int[] coins = { 1, 2, 5 };
		System.out.println(leetCode322.coinChange2(coins, amount));

		amount = 3;
		coins = new int[] { 2 };
		System.out.println(leetCode322.coinChange2(coins, amount));

		amount = 2;
		coins = new int[] { 1, 2147483647 };
		System.out.println(leetCode322.coinChange2(coins, amount));

		amount = 6249;
		coins = new int[] { 186, 419, 83, 408 };
		System.out.println(leetCode322.coinChange4(coins, amount));

		amount = 264;
		coins = new int[] { 474, 83, 404, 3 };
		System.out.println(leetCode322.coinChange4(coins, amount));
	}

	public int coinChange(int[] coins, int amount) {
		return coinChange(coins, amount, 0);
	}

	public int coinChange(int[] coins, int amount, int idxCoin) {
		if (amount == 0) {
			return 0;
		}
		if (idxCoin < coins.length && amount > 0) {
			int maxValue = amount / coins[idxCoin];
			int minCost = Integer.MAX_VALUE;
			for (int i = 0; i <= maxValue; i++) {
				int res = coinChange(coins, amount - i * coins[idxCoin], idxCoin + 1);
				if (res != -1) {
					minCost = Math.min(minCost, i + res);
				}
			}
			return minCost == Integer.MAX_VALUE ? -1 : minCost;
		}
		return -1;
	}

	public int coinChange2(int[] coins, int amount) {
		int[][] dp = new int[coins.length + 1][amount + 1];
		for (int i = 1; i <= amount; i++) {
			dp[coins.length][i] = -1;
		}
		for (int i = coins.length - 1; i >= 0; i--) {
			for (int j = 1; j <= amount; j++) {
				int maxValue = j / coins[i];
				int minCost = Integer.MAX_VALUE;
				for (int k = 0; k <= maxValue; k++) {
					int res = dp[i + 1][j - k * coins[i]];
					if (res != -1) {
						minCost = Math.min(minCost, k + res);
					}
				}
				dp[i][j] = minCost == Integer.MAX_VALUE ? -1 : minCost;
			}
		}
		return dp[0][amount];
	}

	public int coinChange3(int[] coins, int amount) {
		return coinChange3(coins, amount, new int[amount + 1]);
	}

	public int coinChange3(int[] coins, int amount, int[] count) {
		if (amount == 0) {
			return 0;
		}

		if (amount < 0) {
			return -1;
		}

		if (count[amount] == 0) {
			int min = Integer.MAX_VALUE;
			for (int i = 0; i < coins.length; i++) {
				int res = coinChange3(coins, amount - coins[i], count);
				if (res != -1) {
					min = Math.min(min, res + 1);
				}
			}
			min = min == Integer.MAX_VALUE ? -1 : min;
			count[amount] = min;
		}
		return count[amount];
	}

	public int coinChange4(int[] coins, int amount) {
		int[] dp = new int[amount + 1];
		for (int i = 1; i <= amount; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < coins.length; j++) {
				if (i >= coins[j] && dp[i - coins[j]] != -1) {
					min = Math.min(min, dp[i - coins[j]] + 1);
				}
			}
			min = min == Integer.MAX_VALUE ? -1 : min;
			dp[i] = min;
		}
		return dp[amount];
	}

	public int coinChange5(int[] coins, int amount) {
		int max = amount + 1;
		int[] dp = new int[amount + 1];
		Arrays.fill(dp, max);
		dp[0] = 0;
		for (int i = 1; i <= amount; i++) {
			for (int j = 0; j < coins.length; j++) {
				if (coins[j] <= i) {
					dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
				}
			}
		}
		return dp[amount] > amount ? -1 : dp[amount];
	}

	int ans = Integer.MAX_VALUE;

	public int coinChange6(int[] coins, int amount) {
		Arrays.sort(coins);
		coinChange6(coins, amount, coins.length - 1, 0);
		return ans == Integer.MAX_VALUE ? -1 : ans;
	}

	public void coinChange6(int[] coins, int amount, int index, int cnt) {
		if (index < 0) {
			return;
		}

		for (int c = amount / coins[index]; c >= 0; c--) {
			int na = amount - c * coins[index];
			int ncnt = cnt + c;
			if (na == 0) {
				ans = Math.min(ans, ncnt);
				break;
			}
			if (ncnt + 1 >= ans) {
				break;
			}
			coinChange6(coins, na, index - 1, ncnt);
		}
	}
}
