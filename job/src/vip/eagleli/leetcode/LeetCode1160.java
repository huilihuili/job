package vip.eagleli.leetcode;

import java.util.HashMap;
import java.util.Map;

public class LeetCode1160 {
	public int countCharacters(String[] words, String chars) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : chars.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int ans = 0;
		for (String string : words) {
			Map<Character, Integer> curMap = new HashMap<>(map);
			boolean canSpell = true;
			for (char c : string.toCharArray()) {
				if (curMap.getOrDefault(c, 0) < 1) {
					canSpell = false;
					break;
				}
				curMap.put(c, curMap.get(c) - 1);
			}
			ans += canSpell ? string.length() : 0;
		}
		return ans;
	}

	public int countCharacters2(String[] words, String chars) {
		int[] chCount = new int[26];
		for (char c : chars.toCharArray()) {
			++chCount[c - 'a'];
		}
		int res = 0;
		search: for (String word : words) {
			int[] wordCount = new int[26];
			for (char c : word.toCharArray()) {
				if (++wordCount[c - 'a'] > chCount[c - 'a']) {
					continue search;
				}
			}
			res += word.length();
		}
		return res;
	}

}
