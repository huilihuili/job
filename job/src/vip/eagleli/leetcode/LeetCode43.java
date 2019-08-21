package vip.eagleli.leetcode;

import java.util.Arrays;

public class LeetCode43 {
	public static void main(String[] args) {
		System.out.println(multiply("2", "3"));
	}

	public static String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		int[] is = new int[num1.length() + num2.length()];
		Arrays.fill(is, 0);
		for (int i = num2.length() - 1; i >= 0; i--) {
			for (int j = num1.length() - 1; j >= 0; j--) {
				int temp = is[i + j + 1] + (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
				is[i + j + 1] = temp % 10;
				is[i + j] += temp / 10;
			}
		}
		StringBuilder res = new StringBuilder();
		boolean flag = true;
		for (int i = 0; i < is.length; i++) {
			if (flag && is[i] == 0) {
				continue;
			}
			flag = false;
			res.append(is[i]);
		}
		return res.toString();
	}
}
