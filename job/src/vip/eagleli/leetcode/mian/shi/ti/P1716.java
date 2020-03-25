package vip.eagleli.leetcode.mian.shi.ti;

public class P1716 {

	/**
	 * 
	 * 瞎猫碰死耗子
	 * 
	 * @param nums
	 * @return
	 */
	public int massage(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int[] dp = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			dp[i] = nums[i];
			if (i - 2 >= 0) {
				dp[i] = nums[i] + dp[i - 2];
			}
			if (i - 3 >= 0) {
				dp[i] = Math.max(dp[i], nums[i] + dp[i - 3]);
			}
		}
		return Math.max(dp[nums.length - 1], dp[nums.length - 2]);
	}

	public int massage_(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		
		int len = nums.length;
		if (len == 1) {
			return nums[0];
		}

		// dp[i][0]：区间 [0, i] 里接受预约请求，并且下标为 i 的这一天不接受预约的最大时长
		// dp[i][1]：区间 [0, i] 里接受预约请求，并且下标为 i 的这一天接受预约的最大时长
		int[][] dp = new int[len][2];
		dp[0][0] = 0;
		dp[0][1] = nums[0];

		for (int i = 1; i < len; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]);
			dp[i][1] = dp[i - 1][0] + nums[i];
		}
		return Math.max(dp[len - 1][0], dp[len - 1][1]);
	}

	public int massage2(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}
		int[] dp = new int[nums.length];
		dp[0] = nums[0];
		dp[1] = Math.max(nums[0], nums[1]);
		for (int i = 2; i < nums.length; i++) {
			dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
		}
		return dp[nums.length - 1];
	}

	public int massage3(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return nums[0];
		}

		int a = 0, b = 0;
		for (int i = 0; i < nums.length; i++) {
			int c = Math.max(b, a + nums[i]);
			a = b;
			b = c;
		}
		return b;
	}

}
