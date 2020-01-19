package vip.eagleli.leetcode;

public class LeetCode10 {
	public static void main(String[] args) {
		new LeetCode10().isMatch4("bbbba", ".*a*a");
	}

	/**
	 * 仅凭印象 
	 * 
	 * 通过调试 把它做出来了
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		return isMatch(s, p, 0, 0);
	}

	public boolean isMatch(String s, String p, int i, int j) {
		while (j < p.length()) {
			if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
				if (i < s.length() && (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i))) {
					return isMatch(s, p, i, j + 2) || isMatch(s, p, i + 1, j);
				}
				return isMatch(s, p, i, j + 2);
			}

			if (i < s.length() && (p.charAt(j) == '.' || (s.charAt(i) == p.charAt(j)))) {
				i++;
				j++;
				continue;
			}
			return false;
		}
		if (i == s.length() && j == p.length()) {
			return true;
		}
		return false;
	}

	/**
	 * 官方答案
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch2(String s, String p) {
		if (p.isEmpty()) {
			return s.isEmpty();
		}

		boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');

		if (p.length() > 1 && p.charAt(1) == '*') {
			return isMatch2(s, p.substring(2)) || (firstMatch && isMatch2(s.substring(1), p));
		} else {
			return firstMatch && isMatch2(s.substring(1), p.substring(1));
		}
	}

	public boolean isMatch22(String s, String p) {
		return isMatch2(s, p, 0, 0);
	}

	public boolean isMatch2(String s, String p, int i, int j) {
		if (j == p.length()) {
			return i == s.length();
		}

		boolean firstMatch = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');

		if (j + 1 < p.length() && p.charAt(j + 1) == '*') {
			return isMatch2(s, p, i, j + 2) || (firstMatch && isMatch2(s, p, i + 1, j));
		} else {
			return firstMatch && isMatch2(s, p, i + 1, j + 1);
		}
	}

	enum Result {
		TRUE, FALSE
	}

	Result[][] memo;

	/**
	 * 官方答案
	 * 
	 * 动态规划 
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch3(String text, String pattern) {
		memo = new Result[text.length() + 1][pattern.length() + 1];
		return dp(0, 0, text, pattern);
	}

	public boolean dp(int i, int j, String text, String pattern) {
		if (memo[i][j] != null) {
			return memo[i][j] == Result.TRUE;
		}

		boolean ans;
		if (j == pattern.length()) {
			ans = i == text.length();
		} else {
			boolean firstMatch = (i < text.length()
					&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.'));

			if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
				ans = (dp(i, j + 2, text, pattern) || firstMatch && dp(i + 1, j, text, pattern));
			} else {
				ans = firstMatch && dp(i + 1, j + 1, text, pattern);
			}
		}
		memo[i][j] = ans ? Result.TRUE : Result.FALSE;
		return ans;
	}

	/**
	 * 官方答案
	 * 
	 * 动态规划 
	 * 
	 * 自底向上的方法
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch4(String text, String pattern) {
		boolean[][] dp = new boolean[text.length() + 1][pattern.length() + 1];
		dp[text.length()][pattern.length()] = true;

		for (int i = text.length(); i >= 0; i--) {
			for (int j = pattern.length() - 1; j >= 0; j--) {
				boolean firstMatch = i < text.length()
						&& (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');
				if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') {
					dp[i][j] = dp[i][j + 2] || firstMatch && dp[i + 1][j];
				} else {
					dp[i][j] = firstMatch && dp[i + 1][j + 1];
				}
			}
		}
		return dp[0][0];
	}
}
