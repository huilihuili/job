package vip.eagleli.leetcode;

import java.util.Arrays;

public class LeetCode1103 {

	public static void main(String[] args) {
		LeetCode1103 leetCode1103 = new LeetCode1103();
		int candies = 7, numPeople = 4;
		int[] ans = leetCode1103.distributeCandies(candies, numPeople);
		System.out.println(Arrays.toString(ans));

		candies = 10;
		numPeople = 3;
		ans = leetCode1103.distributeCandies(candies, numPeople);
		System.out.println(Arrays.toString(ans));
	}

	public int[] distributeCandies(int candies, int numPeople) {
		int[] ans = new int[numPeople];
		int index = 0, count = 1;
		while (candies > 0) {
			int cur = Math.min(candies, count);
			ans[index] += cur;
			candies -= cur;
			index = index == numPeople - 1 ? 0 : index + 1;
			count++;
		}
		return ans;
	}

	public int[] distributeCandies_(int candies, int numPeople) {
		int[] ans = new int[numPeople];
		int index = 0;
		while (candies > 0) {
			ans[index % numPeople] += Math.min(candies, index + 1);
			candies -= Math.min(candies, index + 1);
			index++;
		}
		return ans;
	}
}
