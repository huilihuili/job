package vip.eagleli.leetcode.biweekly.contest_23;

import java.util.HashMap;
import java.util.Map;

public class CanConstruct_5362 {
	public boolean canConstruct(String s, int k) {
		if (s.length() < k) {
			return false;
		}
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int odd = 0, even = 0;
		for (Integer integer : map.values()) {
			if (integer % 2 == 0) {
				even++;
			} else {
				odd++;
			}
		}

		if (odd - k < 1) {
			return true;
		}
		if (odd - k == 0 && even != 0) {
			return true;
		}
		return false;
	}
}
