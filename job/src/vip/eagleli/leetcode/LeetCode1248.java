package vip.eagleli.leetcode;

public class LeetCode1248 {
	public static void main(String[] args) {
		LeetCode1248 leetCode1248 = new LeetCode1248();
		int[] nums = { 1, 1, 2, 1, 1 };
		int k = 3;
		leetCode1248.numberOfSubarrays(nums, k);
	}

	public int numberOfSubarrays(int[] nums, int k) {
		int n = nums.length;
		int[] odd = new int[n + 2];
		int ans = 0, cnt = 0;
		for (int i = 0; i < n; ++i) {
			if ((nums[i] & 1) == 1) {
				odd[++cnt] = i;
			}
		}
		odd[0] = -1;
		odd[++cnt] = n;
		for (int i = 1; i + k <= cnt; ++i) {
			ans += (odd[i] - odd[i - 1]) * (odd[i + k] - odd[i + k - 1]);
		}
		return ans;
	}

	public int numberOfSubarrays2(int[] nums, int k) {
		int n = nums.length;
		int[] cnt = new int[n + 1];
		int odd = 0, ans = 0;
		cnt[0] = 1;
		for (int i = 0; i < n; ++i) {
			odd += nums[i] & 1;
			ans += odd >= k ? cnt[odd - k] : 0;
			cnt[odd] += 1;
		}
		return ans;
	}
}
