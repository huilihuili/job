package vip.eagleli.leetcode;

import java.util.LinkedList;
import java.util.List;

public class LeetCode1071 {
	public static void main(String[] args) {
		String str1 = "ABCABC", str2 = "ABC";
		LeetCode1071 leetCode1071 = new LeetCode1071();
		System.out.println(leetCode1071.gcdOfStrings(str1, str2));

		str1 = "ABABAB";
		str2 = "ABAB";
		System.out.println(leetCode1071.gcdOfStrings(str1, str2));

		str1 = "LEET";
		str2 = "CODE";
		System.out.println(leetCode1071.gcdOfStrings(str1, str2));
	}

	public String gcdOfStrings(String str1, String str2) {
		List<String> list1 = gcdOfString(str1);
		List<String> list2 = gcdOfString(str2);
		for (String string : list1) {
			for (String string2 : list2) {
				if (string.equals(string2)) {
					return string;
				}
			}
		}
		return "";
	}

	private List<String> gcdOfString(String str) {
		int n = str.length();
		List<String> list = new LinkedList<String>();
		for (int i = str.length(); i >= 1; i--) {
			if (n % i == 0) {
				String string = str.substring(0, i);
				boolean flag = true;
				for (int j = i; j < str.length(); j += i) {
					if (!string.equals(str.subSequence(j, j + i))) {
						flag = false;
						break;
					}
				}
				if (flag) {
					list.add(string);
				}
			}
		}
		return list;
	}

	public String gcdOfStrings2(String str1, String str2) {
		int len1 = str1.length(), len2 = str2.length();
		for (int i = Math.min(len1, len2); i >= 1; --i) { // 从长度大的开始枚举
			if (len1 % i == 0 && len2 % i == 0) {
				String X = str1.substring(0, i);
				if (check(X, str1) && check(X, str2)) {
					return X;
				}
			}
		}
		return "";
	}

	/**
	 * 如果存在一个符合要求的字符串 X
	 * 那么也一定存在一个符合要求的字符串 X'
	 * 它的长度为 str1 和 str2 长度的最大公约数。
	 * 
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String gcdOfStrings3(String str1, String str2) {
		int len1 = str1.length(), len2 = str2.length();
		String T = str1.substring(0, gcd(len1, len2));
		if (check(T, str1) && check(T, str2)) {
			return T;
		}
		return "";
	}

	private boolean check(String t, String s) {
		int lenx = s.length() / t.length();
		StringBuilder ans = new StringBuilder();
		for (int i = 1; i <= lenx; ++i) {
			ans.append(t);
		}
		return ans.toString().equals(s);
	}

	/**
	 * 如果 str1 和 str2 拼接后等于 str2和 str1 拼接起来的字符串（注意拼接顺序不同）
	 * 那么一定存在符合条件的字符串 X
	 * @param str1
	 * @param str2
	 * @return
	 */
	public String gcdOfStrings4(String str1, String str2) {
		if (!(str1 + str2).equals(str2 + str1)) {
			return "";
		}
		return str1.substring(0, gcd(str1.length(), str2.length()));
	}

	private int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

}
