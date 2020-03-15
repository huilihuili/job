package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.TreeSet;

public class LeetCode300 {
	public int lengthOfLIS(int[] nums) {
		if (nums == null || nums.length < 1) {
			return 0;
		}
		int[] dp = new int[nums.length];
		dp[0] = 1;
		int ans = 1;
		for (int i = 1; i < nums.length; i++) {
			int maxVal = 0;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					maxVal = Math.max(maxVal, dp[j]);
				}
			}
			dp[i] = maxVal + 1;
			ans = Math.max(ans, dp[i]);
		}
		return ans;
	}

	public int lengthOfLIS2(int[] nums) {
		if (nums == null || nums.length < 1) {
			return 0;
		}
		int len = 1, n = nums.length;
		int[] d = new int[n + 1];
		d[len] = nums[0];
		for (int i = 1; i < n; ++i) {
			if (nums[i] > d[len]) {
				d[++len] = nums[i];
			} else {
				int l = 1, r = len;
				while (l <= r) {
					int mid = (l + r) >> 1;
					if (d[mid] < nums[i]) {
						l = mid + 1;
					} else
						r = mid - 1;
				}
				d[l] = nums[i];
			}
		}
		return len;
	}

	public int lengthOfLIS2_(int[] nums) {
		if (nums == null || nums.length == 0) {
			return 0;
		}
		int n = nums.length;
		int[] a = new int[n];
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			int idx = Arrays.binarySearch(a, 0, res, nums[i]);
			if (idx < 0) {
				idx = -idx - 1;
			}
			a[idx] = nums[i];
			if (idx == res) {
				res++;
			}
		}
		return res;
	}

	public int lengthOfLIS3(int[] nums) {
		TreeSet<Integer> set = new TreeSet<>();
		for (int i = 0; i < nums.length; i++) {
			Integer c = set.ceiling(nums[i]);
			// 如果set中没有大于nums[i]的最小数字，那么就符合最长上升子序列，加入
			// 如果有，把让那个移除那个数字，换做这个nums[i]，因为nums[i]更小
			// 其实这边先判断 c!=null代码会更为简洁一点
			if (c == null) {
				set.add(nums[i]);
			} else {
				set.remove(c);
				set.add(nums[i]);
			}
		}
		return set.size();
	}
}
