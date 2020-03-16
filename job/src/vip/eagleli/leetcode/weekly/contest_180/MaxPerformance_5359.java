package vip.eagleli.leetcode.weekly.contest_180;

import java.util.Arrays;

public class MaxPerformance_5359 {
	public static void main(String[] args) {
		int n = 3;
		int[] speed = { 2, 8, 2 };
		int[] efficiency = { 2, 7, 1 };
		int k = 2;
		MaxPerformance_5359 maxPerformance_5359 = new MaxPerformance_5359();
		System.out.println(maxPerformance_5359.maxPerformance(n, speed, efficiency, k));
	}

	int ans = 0;

	public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
		maxPerformance(n, speed, efficiency, k, 0, 0, new int[k]);
		return ans;
	}

	public void maxPerformance(int n, int[] speed, int[] efficiency, int k, int indexN, int indexK, int[] cur) {
		if (indexK > k) {
			return;
		}
		if (indexK == k) {
			ans = Math.max(ans, performance(speed, efficiency, cur));
			System.out.println(Arrays.toString(cur) + " " + performance(speed, efficiency, cur));
			return;
		}

		for (int i = indexN; i < n; i++) {
			cur[indexK] = i;
			maxPerformance(n, speed, efficiency, k, i + 1, indexK + 1, cur);
		}
	}

	public int performance(int[] speed, int[] efficiency, int[] cur) {
		int speedSum = 0, efficiencySum = Integer.MAX_VALUE;
		for (int i = 0; i < cur.length; i++) {
			speedSum += speed[cur[i]];
			efficiencySum = Math.min(efficiencySum, efficiency[cur[i]]);
		}
		return speedSum * efficiencySum;
	}
}
