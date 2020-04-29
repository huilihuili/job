package vip.eagleli.leetcode.mian.shi.ti;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class P56_1 {
	public static void main(String[] args) {
		P56_1 p56_1 = new P56_1();
		int[] nums = { 1, 2, 5, 2 };
		int[] ans = p56_1.singleNumbers2(nums);
		System.out.println(Arrays.toString(ans));
	}

	/**
	 * 不符合题意
	 * 
	 * 题意要求时间复杂度是O(n)，空间复杂度是O(1)
	 * @param nums
	 * @return
	 */
	public int[] singleNumbers(int[] nums) {
		Set<Integer> set = new HashSet<>();
		for (int num : nums) {
			if (set.contains(num)) {
				set.remove(num);
			} else {
				set.add(num);
			}
		}
		Iterator<Integer> iterator = set.iterator();
		int[] ans = new int[2];
		ans[0] = iterator.next();
		ans[1] = iterator.next();
		return ans;
	}

	public int[] singleNumbers2(int[] nums) {
		int ans = 0;
		for (int num : nums) {
			ans ^= num;
		}
		int div = 1;
		while ((div & ans) == 0) {
			div <<= 1;
		}
		int a = 0, b = 0;
		for (int num : nums) {
			if ((num & div) > 0) {
				a ^= num;
			} else {
				b ^= num;
			}
		}
		return new int[] { a, b };
	}

	// 位运算 xor & （-xor）
	public int[] singleNumbers2_(int[] nums) {
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
