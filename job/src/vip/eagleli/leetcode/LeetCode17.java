package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LeetCode17 {
	public static void main(String[] args) {
	}

	/**
	 *
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations(String digits) {
		List<String> ans = new LinkedList<>();
		if (digits == null || digits.length() == 0) {
			return ans;
		}

		char[] cs = { '2', '3', '4', '5', '6', '7', '8', '9' };
		String[] strings = { "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };
		Map<Character, String> map = new HashMap<>();
		for (int i = 0; i < cs.length; i++) {
			map.put(cs[i], strings[i]);
		}

		letterCombinations(digits, map, new StringBuilder(), 0, ans);
		return ans;
	}

	public void letterCombinations(String digits, Map<Character, String> map, StringBuilder sb, int row,
			List<String> ans) {
		if (row == digits.length()) {
			ans.add(sb.toString());
			return;
		}

		for (int i = 0; i < map.get(digits.charAt(row)).length(); i++) {
			sb.append(map.get(digits.charAt(row)).charAt(i));
			letterCombinations(digits, map, sb, row + 1, ans);
			sb.deleteCharAt(sb.length() - 1);
		}
	}

	public void letterCombinations(String digits, Map<Character, String> map, String string, int row,
			List<String> ans) {
		if (row == digits.length()) {
			ans.add(string);
			return;
		}

		for (int i = 0; i < map.get(digits.charAt(row)).length(); i++) {
			letterCombinations(digits, map, string + map.get(digits.charAt(row)).charAt(i), row + 1, ans);
		}
	}

	Map<String, String> phone = new HashMap<String, String>() {
		private static final long serialVersionUID = 8267746780166711695L;

		{
			put("2", "abc");
			put("3", "def");
			put("4", "ghi");
			put("5", "jkl");
			put("6", "mno");
			put("7", "pqrs");
			put("8", "tuv");
			put("9", "wxyz");
		}
	};
	List<String> output = new ArrayList<String>();

	/**
	 * 官方答案
	 * 
	 * @param digits
	 * @return
	 */
	public List<String> letterCombinations2(String digits) {
		if (digits.length() != 0)
			backtrack("", digits);
		return output;
	}

	public void backtrack(String combination, String nextDigits) {
		if (nextDigits.length() == 0) {
			output.add(combination);
		} else {
			String digit = nextDigits.substring(0, 1);
			String letters = phone.get(digit);
			for (int i = 0; i < letters.length(); i++) {
				String letter = phone.get(digit).substring(i, i + 1);
				backtrack(combination + letter, nextDigits.substring(1));
			}
		}
	}

}
