package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeetCode49 {
	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, List<String>> map = new HashMap<>();
		for (String string : strs) {
			char[] cs = string.toCharArray();
			Arrays.sort(cs);
			String key = String.valueOf(cs);
			if (!map.containsKey(key)) {
				map.put(key, new LinkedList<>());
			}
			map.get(key).add(string);
		}
		return new ArrayList<>(map.values());
	}
}
