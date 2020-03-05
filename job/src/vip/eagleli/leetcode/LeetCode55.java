package vip.eagleli.leetcode;

public class LeetCode55 {
	public static void main(String[] args) {
		int[] nums = { 2, 3, 1, 1, 4 };
		LeetCode55 leetCode55 = new LeetCode55();
		System.out.println(leetCode55.canJump(nums));

		nums = new int[] { 0, 2, 1, 1, 4 };
		System.out.println(leetCode55.canJump(nums));
	}

	public boolean canJump(int[] nums) {
		int start = nums.length - 1;
		while (start > 0) {
			boolean find = false;
			for (int i = 0; i < start; i++) {
				if (i + nums[i] >= start) {
					start = i;
					find = true;
					break;
				}
			}
			if (!find) {
				return false;
			}
		}
		return true;
	}

	public boolean canJump2(int[] nums) {
		int lastPos = nums.length - 1;
		for (int i = nums.length - 2; i >= 0; i--) {
			if (i + nums[i] >= lastPos) {
				lastPos = i;
			}
		}
		return lastPos == 0;
	}

	public boolean canJump3(int[] nums) {
		int k = 0;
		for (int i = 0; i < nums.length; i++) {
			if (i > k) {
				return false;
			}
			k = Math.max(k, i + nums[i]);
		}
		return true;
	}
}