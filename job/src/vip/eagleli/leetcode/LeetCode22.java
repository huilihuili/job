package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode22 {
	/**
	 * 暴力法
	 * 
	 * @param n
	 * @return
	 */
	public List<String> generateParenthesis(int n) {
		List<String> ans = new ArrayList<>();
		generateAll(new char[2 * n], 0, ans);
		return ans;

	}

	private void generateAll(char[] current, int pos, List<String> ans) {
		if (pos == current.length) {
			if (valid(current)) {
				ans.add(String.valueOf(current));
			}
		} else {
			current[pos] = '(';
			generateAll(current, pos + 1, ans);

			current[pos] = ')';
			generateAll(current, pos + 1, ans);
		}
	}

	private boolean valid(char[] current) {
		int balance = 0;
		for (char c : current) {
			if (c == '(') {
				balance++;
			} else if (c == ')') {
				balance--;
			}

			if (balance < 0) {
				return false;
			}
		}
		return balance == 0;
	}

}
