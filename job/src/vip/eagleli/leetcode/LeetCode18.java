package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode18 {
	public static void main(String[] args) {
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> ans = new LinkedList<>();
		if (nums == null || nums.length < 4) {
			return ans;
		}

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			for (int j = i + 1; j < nums.length; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				int l = j + 1, r = nums.length - 1;
				while (l < r) {
					int sum = nums[i] + nums[j] + nums[l] + nums[r];
					if (sum == target) {
						ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
						while (l < r && nums[l] == nums[++l]) {
						}
						while (l < r && nums[r] == nums[--r]) {
						}
					} else if (sum < target) {
						while (l < r && nums[l] == nums[++l]) {
						}
					} else {
						while (l < r && nums[r] == nums[--r]) {
						}
					}
				}
			}
		}
		return ans;
	}

	public List<List<Integer>> fourSum2(int[] nums, int target) {
		List<List<Integer>> ans = new LinkedList<>();
		if (nums == null || nums.length < 4) {
			return ans;
		}

		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			if (i + 3 < nums.length && nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
				break;
			}

			if (i < nums.length - 3
					&& nums[i] + nums[nums.length - 3] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
				continue;
			}

			for (int j = i + 1; j < nums.length; j++) {
				if (j > i + 1 && nums[j] == nums[j - 1]) {
					continue;
				}
				if (j + 2 < nums.length && nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
					break;
				}

				if (j < nums.length - 2 && nums[i] + nums[j] + nums[nums.length - 2] + nums[nums.length - 1] < target) {
					continue;
				}

				int l = j + 1, r = nums.length - 1;
				while (l < r) {
					int sum = nums[i] + nums[j] + nums[l] + nums[r];
					if (sum == target) {
						ans.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
						while (l < r && nums[l] == nums[++l]) {
						}
						while (l < r && nums[r] == nums[--r]) {
						}
					} else if (sum < target) {
						while (l < r && nums[l] == nums[++l]) {
						}
					} else {
						while (l < r && nums[r] == nums[--r]) {
						}
					}
				}
			}
		}
		return ans;
	}

}
