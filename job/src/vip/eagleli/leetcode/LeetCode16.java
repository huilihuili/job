package vip.eagleli.leetcode;

import java.util.Arrays;

public class LeetCode16 {
	public static void main(String[] args) {
	}

	public int threeSumClosest(int[] nums, int target) {
		int ans = Integer.MAX_VALUE;
		if (nums == null || nums.length < 3) {
			return ans;
		}

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				int diff = nums[l] + nums[r] + nums[i] - target;

				if (diff == 0) {
					return target;
				}

				if (Math.abs(diff) < Math.abs(ans)) {
					ans = diff;
				}

				if (diff < 0) {
					l++;
				} else {
					r--;
				}
			}
		}
		return target + ans;
	}
}
