package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeetCode49 {

	public static void main(String[] args) {
		LeetCode49 leetCode49 = new LeetCode49();
		String[] strs = { "eat", "tea", "tan", "ate", "nat", "bat" };
		System.out.println(leetCode49.groupAnagrams(strs));
	}

	public List<List<String>> groupAnagrams(String[] strs) {
		Map<String, Integer> map = new HashMap<>();
		List<List<String>> ans = new ArrayList<>();

		for (int i = 0; i < strs.length; i++) {
			char[] cs = strs[i].toCharArray();
			Arrays.sort(cs);
			String string = String.valueOf(cs);
			if (map.containsKey(string)) {
				ans.get(map.get(string)).add(strs[i]);
			} else {
				map.put(string, ans.size());
				ans.add(new LinkedList<>(Arrays.asList(strs[i])));
			}
		}
		return ans;
	}

	public List<List<String>> groupAnagrams_(String[] strs) {
		if (strs.length == 0) {
			return new ArrayList<>();
		}
		Map<String, List<String>> ans = new HashMap<>();
		for (String s : strs) {
			char[] ca = s.toCharArray();
			Arrays.sort(ca);
			String key = String.valueOf(ca);
			if (!ans.containsKey(key)) {
				ans.put(key, new ArrayList<String>());
			}
			ans.get(key).add(s);
		}
		return new ArrayList<>(ans.values());
	}

	public List<List<String>> groupAnagrams2(String[] strs) {
		if (strs.length == 0) {
			return new ArrayList<>();
		}
		Map<String, List<String>> ans = new HashMap<>();
		int[] count = new int[26];
		for (int j = 0; j < strs.length; j++) {
			Arrays.fill(count, 0);
			char[] ca = strs[j].toCharArray();
			for (int i = 0; i < ca.length; i++) {
				count[ca[i] - 'a']++;
			}
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < count.length; i++) {
				stringBuilder.append("#").append(count[i]);
			}
			String key = stringBuilder.toString();
			if (!ans.containsKey(key)) {
				ans.put(key, new LinkedList<String>());
			}
			ans.get(key).add(strs[j]);
		}
		return new ArrayList<>(ans.values());
	}

	public List<List<String>> groupAnagrams3(String[] strs) {
		HashMap<Long, List<String>> hash = new HashMap<>();
		// 每个字母对应一个质数
		int[] prime = { 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101,
				103 };
		for (int i = 0; i < strs.length; i++) {
			long key = 1;
			// 累乘得到 key
			for (int j = 0; j < strs[i].length(); j++) {
				key *= prime[strs[i].charAt(j) - 'a'];
			}
			if (hash.containsKey(key)) {
				hash.get(key).add(strs[i]);
			} else {
				hash.put(key, new LinkedList<>(Arrays.asList(strs[i])));
			}
		}
		return new ArrayList<List<String>>(hash.values());
	}

}
