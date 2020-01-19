package vip.eagleli.leetcode;

public class LeetCode5 {

	public static void main(String[] args) {
		System.out.println(new LeetCode5().longestPalindrome4("babad"));
	}

	/**
	 * 暴力法 超时
	 * @param s
	 * @return
	 */
	public String longestPalindrome(String s) {
		String ans = "";
		int len = s.length();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				if (isPalindromic(s.substring(i, j)) && (j - i) > ans.length()) {
					ans = s.substring(i, j);
				}
			}
		}
		return ans;
	}

	private boolean isPalindromic(String s) {
		int l = 0, r = s.length() - 1;
		while (l < r) {
			if (s.charAt(l) != s.charAt(r)) {
				return false;
			}
			l++;
			r--;
		}
		return true;
	}

	/**
	 * 动态规划
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		String ans = "";
		int len = s.length();
		boolean dp[][] = new boolean[len][len];
		for (int i = 1; i <= len; i++) { // 回文长度
			for (int start = 0; start <= len - i; start++) {
				int end = start + i - 1;
				dp[start][end] = (i < 4 || dp[start + 1][end - 1]) && (s.charAt(start) == s.charAt(end));
				if (dp[start][end] && i > ans.length()) {
					ans = s.substring(start, end + 1);
				}
			}
		}
		return ans;
	}

	/**
	 * 动态规划
	 * 
	 * 二维数组变一维数组
	 * @param s
	 * @return
	 */
	public String longestPalindrome3(String s) {
		String ans = "";
		int len = s.length();
		boolean dp[] = new boolean[len];
		for (int i = len - 1; i >= 0; i--) { // 回文长度
			for (int j = len - 1; j >= i; j--) {
				dp[j] = (j - i < 2 || dp[j - 1]) && s.charAt(i) == s.charAt(j);
				if (dp[j] && j - i + 1 > ans.length()) {
					ans = s.substring(i, j + 1);
				}
			}
		}
		return ans;
	}

	/**
	 * 中心扩展法
	 * 
	 * @param s
	 * @return
	 */
	public String longestPalindrome4(String s) {
		if (s == null || s.length() < 1) {
			return "";
		}
		int start = 0, end = 0;
		for (int i = 0; i < s.length(); i++) {
			int len1 = expandAroundCenter(s, i, i);
			int len2 = expandAroundCenter(s, i, i + 1);
			int len = Math.max(len1, len2);
			if (len > end - start + 1) {
				start = i - (len - 1) / 2;
				end = i + len / 2;
			}
		}
		return s.substring(start, end + 1);
	}

	private int expandAroundCenter(String s, int l, int r) {
		while (l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return r - l - 1;
	}

}
