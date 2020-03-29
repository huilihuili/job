package vip.eagleli.leetcode.weekly.contest_182;

import java.util.HashMap;
import java.util.Map;

public class FindLucky_5368 {
	public int findLucky(int[] arr) {
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i : arr) {
			map.put(i, map.getOrDefault(i, 0) + 1);
		}
		int ans = -1;
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			if (entry.getKey() == entry.getValue()) {
				ans = Math.max(ans, entry.getKey());
			}
		}
		return ans;
	}
}
