package vip.eagleli.leetcode;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class LeetCode409 {
	public int longestPalindrome(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		Map<Character, Integer> map = new HashMap<Character, Integer>();
		for (char c : s.toCharArray()) {
			map.put(c, map.getOrDefault(c, 0) + 1);
		}
		int ans = 0;
		for (Integer integer : map.values()) {
			ans += integer / 2 * 2;
		}
		return ans == s.length() ? ans : ans + 1;
	}

	public int longestPalindrome2(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int[] cnt = new int[58];
		for (char c : s.toCharArray()) {
			cnt[c - 'A'] += 1;
		}

		int ans = 0;
		for (int x : cnt) {
			ans += x - (x & 1); // 等价于 (x - x % 2) OR (x / 2 * 2)
		}
		return ans < s.length() ? ans + 1 : ans;
	}

	public int longestPalindrome3(String s) {
		Map<Integer, Integer> count = s.chars().boxed().collect(Collectors.toMap(k -> k, v -> 1, Integer::sum));
		int ans = count.values().stream().mapToInt(i -> i - (i & 1)).sum();
		return ans < s.length() ? ans + 1 : ans;
	}

	public int longestPalindrome4(String s) {
		int[] freq = new int[58]; // from A to z
		for (char c : s.toCharArray()) {
			freq[c - 'A']++;
		}
		int odd = 0;
		for (int f : freq) {
			if (f % 2 == 1) {
				odd++;
			}
		}
		return s.length() - odd + (odd > 0 ? 1 : 0);
	}

	public int longestPalindrome4_(String s) {
		int odd = 0;
		long freq = 0b0L; // 64 bits > 58 chars(from A to z)
		for (char c : s.toCharArray()) {
			long ori = freq;
			freq ^= 1L << (c - 'A'); // 异或占位，1L 为 long，而非 1 的 int
			if (freq > ori) {
				odd++; // 统计后增加，说明多了一个等待者
			} else {
				odd--; // 统计后减少，说明来了相同字符凑成偶数，故奇数统计 - 1
			}
		}
		return s.length() - odd + (odd > 0 ? 1 : 0);
	}
}
