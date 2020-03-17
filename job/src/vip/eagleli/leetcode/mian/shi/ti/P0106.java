package vip.eagleli.leetcode.mian.shi.ti;

public class P0106 {

	/**
	 * aabcccccaaa --> a2b1c5a3
	 * 
	 * @param S
	 * @return
	 */
	public String compressString(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		StringBuilder stringBuilder = new StringBuilder();
		char pre = s.charAt(0);
		int count = 1;
		for (int i = 1; i < s.length(); i++) {
			if (s.charAt(i) == pre) {
				count++;
			} else {
				stringBuilder.append(pre).append(count);
				count = 1;
				pre = s.charAt(i);
			}
		}
		stringBuilder.append(pre).append(count);
		return stringBuilder.length() < s.length() ? stringBuilder.toString() : s;
	}

	public String compressString2(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		StringBuilder stringBuilder = new StringBuilder();
		int l = 0, r = 1;
		while (r < s.length()) {
			if (s.charAt(r) == s.charAt(l)) {
				r++;
			} else {
				stringBuilder.append(s.charAt(l)).append(r - l);
				l = r;
				r++;
			}
		}
		stringBuilder.append(s.charAt(l)).append(r - l);
		return stringBuilder.length() < s.length() ? stringBuilder.toString() : s;
	}

	public String compressString3(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		StringBuilder sb = new StringBuilder();
		int i = 0, j = 0;
		while (i < s.length()) {
			while (j < s.length() && s.charAt(j) == s.charAt(i)) {
				j++;
			}
			sb.append(s.charAt(i)).append(j - i);
			i = j;
		}
		return sb.length() < s.length() ? sb.toString() : s;
	}

}
