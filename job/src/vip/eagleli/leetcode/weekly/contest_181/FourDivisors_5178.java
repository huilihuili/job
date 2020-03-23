package vip.eagleli.leetcode.weekly.contest_181;

public class FourDivisors_5178 {
	public int sumFourDivisors(int[] nums) {
		int ans = 0;
		for (int x : nums) {
			ans += sumDivisor(x);
		}
		return ans;
	}

	public int sumDivisor(int num) {
		int n = 0, ans = 1 + num;
		for (int i = 2; i <= num / 2; i++) {
			if (num % i == 0) {
				n++;
				ans += i;
			}
			if (n > 2) {
				return 0;
			}
		}
		return n == 2 ? ans : 0;
	}

}
