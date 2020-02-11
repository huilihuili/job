package vip.eagleli.leetcode;

import java.util.LinkedList;

public class LeetCode32 {

	/**
	 * 暴力法
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses(String s) {
		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			for (int j = i + 2; j <= s.length(); j += 2) {
				if (isValid(s.substring(i, j))) {
					ans = Math.max(ans, j - i);
				}
			}
		}
		return ans;
	}

	public boolean isValid(String s) {
		LinkedList<Character> stack = new LinkedList<Character>();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push('(');
			} else if (!stack.isEmpty()) {
				stack.pop();
			} else {
				return false;
			}
		}
		return stack.isEmpty();
	}

	/**
	 * 动态规划
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses2(String s) {
		int maxans = 0;
		int dp[] = new int[s.length()];
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == ')') {
				if (s.charAt(i - 1) == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
					dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
				}
				maxans = Math.max(maxans, dp[i]);
			}
		}
		return maxans;
	}

	/**
	 * 栈
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses3(String s) {
		int maxans = 0;
		LinkedList<Integer> stack = new LinkedList<Integer>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					maxans = Math.max(maxans, i - stack.peek());
				}
			}
		}
		return maxans;
	}

	/**
	 * 利用括号的个数
	 * 
	 * @param s
	 * @return
	 */
	public int longestValidParentheses4(String s) {
		int left = 0, right = 0, ans = 0;
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}

			if (left == right) {
				ans = Math.max(ans, left + right);
			} else if (right > left) {
				left = right = 0;
			}
		}
		left = right = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '(') {
				left++;
			} else {
				right++;
			}

			if (left == right) {
				ans = Math.max(ans, left + right);
			} else if (left > right) {
				left = right = 0;
			}
		}

		return ans;
	}

}
