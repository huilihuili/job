package vip.eagleli.leetcode;

public class LeetCode28 {
	/**
	 * 暴力
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr(String haystack, String needle) {
		if (haystack == null) {
			return -1;
		}

		if (needle == null || needle.isEmpty()) {
			return 0;
		}

		int i = 0, j = 0;
		while (i < haystack.length()) {
			while (i < haystack.length() && j < needle.length() && haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
			}

			while (j == needle.length()) {
				return i - j;
			}
			i -= (j - 1);
			j = 0;
		}
		return -1;
	}

	/**
	 * 暴力
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr2(String haystack, String needle) {
		if (haystack == null) {
			return -1;
		}

		if (needle == null || needle.isEmpty()) {
			return 0;
		}

		for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
			int j = 0;
			while (j < needle.length()) {
				if (haystack.charAt(i + j) != needle.charAt(j)) {
					break;
				}
				j++;
			}
			if (j == needle.length()) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 暴力
	 * 
	 * @param haystack
	 * @param needle
	 * @return
	 */
	public int strStr3(String haystack, String needle) {
		if (haystack == null) {
			return -1;
		}

		if (needle == null || needle.isEmpty()) {
			return 0;
		}

		int i = 0, j = 0;
		while (i < haystack.length() && j < needle.length()) {
			if (haystack.charAt(i) == needle.charAt(j)) {
				i++;
				j++;
			} else {
				i = i - j + 1;
				j = 0;
			}
		}
		if (j == needle.length()) {
			return i - j;
		}
		return -1;
	}

	/**
	 * 参照String的indexOf写法
	 * 
	 * @param haystack
	 * @param needle
	 * @see java.lang.String#indexOf(char[], int, int, char[], int, int, int)
	 * @return
	 */
	public int strStr4(String source, String target) {
		if (source == null) {
			return -1;
		}
		if (target == null || target.isEmpty()) {
			return 0;
		}

		char first = target.charAt(0);
		int max = source.length() - target.length();

		for (int i = 0; i <= max; i++) {

			/* Look for first character. */
			if (source.charAt(i) != first) {
				while (++i <= max && source.charAt(i) != first)
					;
			}

			/* Found first character, now look at the rest of v2 */
			if (i <= max) {
				int j = i + 1;
				int end = j + target.length() - 1;
				for (int k = 1; j < end && source.charAt(j) == target.charAt(k); j++, k++)
					;

				if (j == end) {
					/* Found whole string. */
					return i;
				}
			}
		}
		return -1;
	}
}
