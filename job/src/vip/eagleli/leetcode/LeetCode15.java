package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode15 {
	public static void main(String[] args) {
		System.out.println(new LeetCode15().threeSum(new int[] { -1, 0, 1, 2, -1, -4 }));
	}

	public List<List<Integer>> threeSum(int[] nums) {
		List<List<Integer>> ans = new LinkedList<>();
		if (nums == null || nums.length < 3) {
			return ans;
		}

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				continue;
			}

			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}

			int l = i + 1, r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (sum == 0) {
					ans.add(Arrays.asList(nums[i], nums[l], nums[r]));
					while (l < r && nums[l] == nums[l + 1]) {
						l++;
					}
					while (l < r && nums[r] == nums[r - 1]) {
						r--;
					}
					l++;
					r--;
				} else if (sum < 0) {
					while (l < r && nums[l] == nums[l + 1]) {
						l++;
					}
					l++;
				} else {
					while (l < r && nums[r] == nums[r - 1]) {
						r--;
					}
					r--;
				}
			}
		}
		return ans;
	}

}
