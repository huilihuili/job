package vip.eagleli.leetcode;

/**
 * 
 * @author Administrator
 * @see LeetCode136
 */
public class LeetCode260 {
	public int[] singleNumbers(int[] nums) {
		if (nums == null || nums.length < 2) {
			return new int[0];
		}
		int xor = 0; // 表示两个只出现一次的数字的异或结果
		for (int num : nums) {
			xor ^= num;
		}

		xor &= -xor;
		int[] res = new int[2];
		for (int num : nums) {
			if ((xor & num) == 0) {
				res[0] ^= num;
			} else {
				res[1] ^= num;
			}
		}
		return res;
	}
}
