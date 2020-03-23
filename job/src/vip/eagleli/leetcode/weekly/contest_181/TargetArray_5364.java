package vip.eagleli.leetcode.weekly.contest_181;

public class TargetArray_5364 {
	public int[] createTargetArray(int[] nums, int[] index) {
		int[] ans = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			System.arraycopy(ans, index[i], ans, index[i] + 1, ans.length - index[i] - 1);
			ans[index[i]] = nums[i];
		}
		return ans;
	}
}
