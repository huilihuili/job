package vip.eagleli.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode1 {
	/**
	 * 暴力法
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return null;
		}
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}
		}
		return null;
	}

	/**
	 * 两遍哈希表
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum2(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return null;
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(nums[i], i);
		}

		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement) && map.get(complement) != i) {
				return new int[] { i, map.get(complement) };
			}
		}

		return null;
	}

	/**
	 * 一遍哈希表
	 * 
	 * @param nums
	 * @param target
	 * @return
	 */
	public int[] twoSum3(int[] nums, int target) {
		if (nums == null || nums.length < 2) {
			return null;
		}

		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int complement = target - nums[i];
			if (map.containsKey(complement) && map.get(complement) != i) {
				return new int[] { map.get(complement), i };
			}
			map.put(nums[i], i);
		}

		return null;
	}
}
