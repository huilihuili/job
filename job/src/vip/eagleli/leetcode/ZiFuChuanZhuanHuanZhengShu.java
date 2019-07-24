package vip.eagleli.leetcode;

public class ZiFuChuanZhuanHuanZhengShu {
	public static void main(String[] args) {
		System.out.println(myAtoi("  -42"));
	}

	public static int myAtoi(String str) {
		if (str == null || (str = str.trim()).equals("")) {
			return 0;
		}
		System.out.println(str);
		boolean isNegtive = false;
		int startIndex = 0;
		long res = 0;
		if (str.charAt(0) == '-' || str.charAt(0) == '+') {
			startIndex = 1;
			isNegtive = str.charAt(0) == '-' ? true : false;
		} else if (str.charAt(0) <= '0' || str.charAt(0) >= '9') {
			return 0;
		}
		for (int i = startIndex; i < str.length(); i++) {
			if (str.charAt(i) <= '0' || str.charAt(i) >= '9') {
				return (int) res;
			}
			res = res * 10 + str.charAt(i) - '0';
		}
		res = isNegtive ? -1 * res : res;
		if (res > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		} else if (res < Integer.MIN_VALUE) {
			return Integer.MIN_VALUE;
		} else {
			return (int) res;
		}
	}
}
