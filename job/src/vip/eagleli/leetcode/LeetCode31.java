package vip.eagleli.leetcode;

import java.util.Arrays;

public class LeetCode31 {
	public static void main(String[] args) {
		new LeetCode31().nextPermutation(new int[] { 2, 3, 1 });
	}

	/**
	 * 1.先找出最大的索引 k 满足 nums[k] < nums[k+1]，如果不存在，就翻转整个数组；
	 * 2.再找出另一个最大索引 l 满足 nums[l] > nums[k]；
	 * 3.交换 nums[l] 和 nums[k]；
	 * 4.最后翻转 nums[k+1:]。
	 * 
	 * @param nums
	 */
	public void nextPermutation(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}

		int k = nums.length - 2;
		while (k >= 0 && nums[k] >= nums[k + 1]) {
			k--;
		}

		if (k == -1) {
			Arrays.sort(nums);
			return;
		}

		int l = nums.length - 1;
		while (l >= 0 && nums[l] <= nums[k]) {
			l--;
		}

		swap(nums, k, l);
		reverse(nums, k + 1, nums.length - 1);
	}

	public void nextPermutation_(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}
		int firstIndex = -1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (nums[i] < nums[i + 1]) {
				firstIndex = i;
				break;
			}
		}
		if (firstIndex == -1) {
			reverse(nums, 0, nums.length - 1);
			return;
		}
		int secondIndex = -1;
		for (int i = nums.length - 1; i >= 0; i--) {
			if (nums[i] > nums[firstIndex]) {
				secondIndex = i;
				break;
			}
		}
		swap(nums, firstIndex, secondIndex);
		reverse(nums, firstIndex + 1, nums.length - 1);
	}

	public void nextPermutation__(int[] nums) {
		if (nums == null || nums.length < 2) {
			return;
		}

		int k = nums.length - 2;
		while (k >= 0 && nums[k] >= nums[k + 1]) {
			k--;
		}

		if (k >= 0) {
			int l = nums.length - 1;
			while (l >= 0 && nums[l] <= nums[k]) {
				l--;
			}
			swap(nums, k, l);
		}
		reverse(nums, k + 1, nums.length - 1);
	}

	private void reverse(int[] x, int start, int end) {
		while (start < end) {
			swap(x, start++, end--);
		}
	}

	private void swap(int[] x, int a, int b) {
		int t = x[a];
		x[a] = x[b];
		x[b] = t;
	}
}
