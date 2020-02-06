package vip.eagleli.leetcode;

public class LeetCode27 {
	public int removeElement(int[] nums, int val) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int size = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != val) {
				nums[size++] = nums[i];
			}
		}
		return size;
	}

	public int removeElement2(int[] nums, int val) {
		if (nums == null || nums.length == 0) {
			return 0;
		}

		int n = nums.length;
		for (int i = 0; i < n; i++) {
			if (nums[i] == val) {
				nums[i--] = nums[--n];
			}
		}
		return n;
	}
}
