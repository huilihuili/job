package vip.eagleli.leetcode;

public class LeetCode41 {
	public int firstMissingPositive(int[] nums) {
		for (int i = 0; i < nums.length; i++) {
			while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]) {
				swap(nums, i, nums[i] - 1);
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != i + 1) {
				return i + 1;
			}
		}
		return nums.length + 1;
	}

	public int firstMissingPositive2(int[] nums) {
		int contains = 0, n = nums.length;

		for (int i = 0; i < n; i++) {
			if (nums[i] == 1) {
				contains++;
			}
		}

		if (contains == 0) {
			return 1;
		}

		for (int i = 0; i < n; i++) {
			if (nums[i] <= 0 || nums[i] > n) {
				nums[i] = 1;
			}
		}

		for (int i = 0; i < n; i++) {
			int a = Math.abs(nums[i]);
			nums[a - 1] = -Math.abs(nums[a - 1]);
		}

		for (int i = 1; i < n; i++) {
			if (nums[i] > 0) {
				return i + 1;
			}
		}
		return n + 1;
	}

	public int firstMissingPositive3(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 1;
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] <= 0) {
				nums[i] = nums.length + 1;
			}
		}

		for (int i = 0; i < nums.length; i++) {
			int a = Math.abs(nums[i]);
			if (a <= nums.length) {
				nums[a - 1] = -Math.abs(nums[a - 1]);
			}
		}

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > 0) {
				return i + 1;
			}
		}

		return nums.length + 1;
	}

	private void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
