package vip.eagleli.leetcode.spring2020.team;

import java.util.Arrays;

public class ExpectNumber {
	public static void main(String[] args) {
		ExpectNumber expectNumber = new ExpectNumber();
		int[] scores = { 1, 2, 3 };
		expectNumber.expectNumber(scores);
	}

	public int expectNumber(int[] scores) {
		Arrays.sort(scores);
		int ans = 0;
		for (int i = scores.length - 1, j = 0; i >= 0; i--, j++) {
			ans += scores[i] * j;
		}
		return ans;
	}
}
