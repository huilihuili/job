package vip.eagleli.leetcode;

public class LeetCode44 {
	public static void main(String[] args) {
		LeetCode44 leetCode44 = new LeetCode44();
		System.out.println(leetCode44.isMatch("aa", "a"));
		System.out.println(leetCode44.isMatch("aa", "*"));
		System.out.println(leetCode44.isMatch("cb", "?a"));
		System.out.println(leetCode44.isMatch("adceb", "*a*b"));
		System.out.println(leetCode44.isMatch("acdcb", "a*c?b"));
	}

	/**
	 * 时间超时
	 * 
	 * @param s
	 * @param p
	 * @return
	 */
	public boolean isMatch(String s, String p) {
		if (s == null || p == null) {
			return false;
		}

		if ("".equals(s) && "".equals(p)) {
			return true;
		}

		if ("*".equals(p)) {
			return true;
		}

		if (p.startsWith("**")) {
			return isMatch(s, p.substring(1));
		}

		if ("".equals(s) || "".equals(p)) {
			return false;
		}

		if (p.charAt(0) == '*') {
			boolean firstMath = s.charAt(0) == p.charAt(1) || p.charAt(1) == '?';
			return isMatch(s.substring(1), p) || (firstMath ? isMatch(s.substring(1), p.substring(2)) : firstMath);
		} else {
			boolean firstMath = s.charAt(0) == p.charAt(0) || p.charAt(0) == '?';
			return firstMath ? isMatch(s.substring(1), p.substring(1)) : firstMath;
		}
	}

	public boolean isMatch2(String s, String p) {
		int i = 0, j = 0;
		int sn = s.length(), pn = p.length();
		int start = -1, match = 0;

		while (i < sn) {
			if (j < pn && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?')) {
				i++;
				j++;
			} else if (j < pn && p.charAt(j) == '*') {
				start = j++;
				match = i;
			} else if (start != -1) {
				i = ++match;
				j = start + 1;
			} else {
				return false;
			}
		}
		while (j < pn && p.charAt(j) == '*') {
			j++;
		}
		return j == pn;
	}

	public boolean isMatch3(String s, String p) {
		boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
		dp[0][0] = true;
		for (int j = 0; j < p.length(); j++) {
			if (p.charAt(j) == '*') {
				dp[0][j + 1] = dp[0][j];
			}
		}
		for (int i = 0; i < s.length(); i++) {
			for (int j = 0; j < p.length(); j++) {
				if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
					dp[i + 1][j + 1] = dp[i][j];
				} else if (p.charAt(j) == '*') {
					dp[i + 1][j + 1] = dp[i + 1][j] || dp[i][j + 1];
				}
			}
		}
		return dp[s.length()][p.length()];
	}

}
