package vip.eagleli.leetcode.weekly.contest_183;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinSubsequence_5376 {
	public List<Integer> minSubsequence(int[] nums) {
		int sum = 0;
		Arrays.sort(nums);
		for (int i : nums) {
			sum += i;
		}
		List<Integer> ans = new ArrayList<Integer>();
		int cur = 0;
		for (int i = nums.length - 1; i >= 0; i--) {
			ans.add(nums[i]);
			cur += nums[i];
			if (cur > sum - cur) {
				break;
			}
		}
		return ans;
	}
}
