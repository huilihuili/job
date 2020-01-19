package vip.eagleli.leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LeetCode3 {

	public static void main(String[] args) {
		new LeetCode3().lengthOfLongestSubstring4("abba");
	}

	/**
	 * 鄙人实现的
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring1(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int res = 1;
		Set<Character> set = new HashSet<>();
		Queue<Character> queue = new LinkedList<>();
		int index = 0;
		while (index < s.length()) {
			if (set.contains(s.charAt(index))) {
				while (!queue.isEmpty() && !queue.peek().equals(s.charAt(index))) {
					set.remove(queue.poll());
				}
				set.remove(queue.poll());
				continue;
			}
			set.add(s.charAt(index));
			queue.add(s.charAt(index));
			res = Math.max(res, set.size());
			index++;
		}
		return res;
	}

	/**
	 * 暴力法
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		int ans = 0;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j <= n; j++) {
				if (allUnique(s, i, j)) {
					ans = Math.max(ans, j - i);
				}
			}
		}
		return ans;
	}

	public boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<>();
		for (int i = start; i < end; i++) {
			Character ch = s.charAt(i);
			if (set.contains(ch)) {
				return false;
			}
			set.add(ch);
		}
		return true;
	}

	/**
	 * 官方实现
	 * 
	 * 滑动窗口
	 * @param s
	 * @return
	 */

	public int lengthOfLongestSubstring3(String s) {
		int n = s.length();
		int i = 0, j = 0, ans = 0;
		Set<Character> set = new HashSet<>();
		while (i < n && j < n) {
			if (!set.contains(s.charAt(j))) {
				set.add(s.charAt(j++));
				ans = Math.max(ans, j - i);
			} else {
				set.remove(s.charAt(i++));
			}
		}
		return ans;
	}

	/**
	 * 官方实现
	 * 
	 * 优化的滑动窗口
	 * 使用 HashMap
	 * 
	 * @param s
	 * @return
	 */

	public int lengthOfLongestSubstring4(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0, j = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(i, map.get(s.charAt(j)));
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}

	/**
	 * 官方实现
	 * 
	 * 优化的滑动窗口
	 * 使用 数组（假设字符集为 ASCII 128）
	 * 
	 * @param s
	 * @return
	 */

	public int lengthOfLongestSubstring5(String s) {
		int n = s.length(), ans = 0;
		int[] index = new int[128];
		for (int i = 0, j = 0; j < n; j++) {
			i = Math.max(i, index[s.charAt(j)]);
			ans = Math.max(ans, j - i + 1);
			index[s.charAt(j)] = j + 1;
		}
		return ans;
	}
}
