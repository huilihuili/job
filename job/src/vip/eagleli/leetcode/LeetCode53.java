package vip.eagleli.leetcode;

public class LeetCode53 {
	public static void main(String[] args) {
		LeetCode53 leetCode53 = new LeetCode53();
		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(leetCode53.maxSubArray(nums));
	}

	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length < 1) {
			return 0;
		}

		int ans = nums[0], cur = nums[0];
		for (int i = 1; i < nums.length; i++) {
			cur = Math.max(nums[i], nums[i] + cur);
			ans = Math.max(ans, cur);
		}
		return ans;
	}
}
