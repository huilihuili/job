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

	public List<String> generateParenthesis2(int n) {
		List<String> ans = new ArrayList<>();
		generateAll2(new char[2 * n], 0, 0, 0, ans);
		return ans;
	}

	private void generateAll2(char[] current, int left, int right, int pos, List<String> ans) {
		if (left + right == current.length) {
			ans.add(String.valueOf(current));
		} else {
			if (left < current.length / 2) {
				current[pos] = '(';
				generateAll2(current, left + 1, right, pos + 1, ans);
			}

			if (right < left) {
				current[pos] = ')';
				generateAll2(current, left, right + 1, pos + 1, ans);
			}
		}
	}

	List<String> res = new ArrayList<>();
	public List<String> generateParenthesis2_(int n) {
		dfs(n, n, "");
		return res;
	}
	private void dfs(int left, int right, String curStr) {
		if (left == 0 && right == 0) { // 左右括号都不剩余了，递归终止
			res.add(curStr);
			return;
		}
		if (left > 0) { // 如果左括号还剩余的话，可以拼接左括号
			dfs(left - 1, right, curStr + "(");
		}
		if (right > left) { // 如果右括号剩余多于左括号剩余的话，可以拼接右括号
			dfs(left, right - 1, curStr + ")");
		}
	}

}
