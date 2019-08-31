package vip.eagleli.leetcode;

public class LeetCode55 {
	public static void main(String[] args) {
		int[] nums = { 2, 3, 1, 1, 4 };
		System.out.println(new LeetCode55().canJump(nums));
	}

	public boolean canJump(int[] nums) {
		boolean[] res = new boolean[nums.length];
		int n = nums.length;
		res[n - 1] = true;
		for (int i = n - 2; i >= 0; i--) {
			for (int j = 1; i + j < nums.length && j <= nums[i]; j++) {
				res[i] = res[i] || res[i + j];
			}
		}
		return res[0];
	}

	public boolean canJump(int[] nums, int index) {
		if (index == nums.length - 1) {
			return true;
		}
		if (index > nums.length - 1) {
			return false;
		}
		boolean res = false;
		for (int i = 1; i <= nums[index]; i++) {
			res = res || canJump(nums, index + i);
		}
		return res;
	}
}
