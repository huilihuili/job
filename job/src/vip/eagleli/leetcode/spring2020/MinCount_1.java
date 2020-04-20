package vip.eagleli.leetcode.spring2020;

public class MinCount_1 {
	public int minCount(int[] coins) {
		int ans = 0;
		for (int i : coins) {
			ans += (i + 1) / 2;
		}
		return ans;
	}
}
