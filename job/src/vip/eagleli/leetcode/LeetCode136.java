package vip.eagleli.leetcode;

/**
 * 
 * @author Administrator
 * @see LeetCode260
 */
public class LeetCode136 {
	public int singleNumber(int[] nums) {
		int ans = 0;
		for (int num : nums) {
			ans ^= num;
		}
		return ans;
	}
}
