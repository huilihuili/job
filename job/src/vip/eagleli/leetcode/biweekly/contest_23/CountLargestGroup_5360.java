package vip.eagleli.leetcode.biweekly.contest_23;

import java.util.HashMap;
import java.util.Map;

public class CountLargestGroup_5360 {
	public int countLargestGroup(int n) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int max = 0;
		for (int i = 1; i <= n; i++) {
			int sum = sumOfNum(i);
			map.put(sum, map.getOrDefault(sum, 0) + 1);
			max = Math.max(max, map.get(sum));
		}
		int ans = 0;
		for (Integer integer : map.values()) {
			if (integer == max) {
				ans++;
			}
		}
		return ans;
	}

	private int sumOfNum(int num) {
		int sum = 0;
		while (num > 0) {
			sum += num % 10;
			num /= 10;
		}
		return sum;
	}
}
