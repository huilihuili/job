package vip.eagleli.leetcode;

public class LeetCode8 {
	public static void main(String[] args) {
		System.out.println(new LeetCode8().myAtoi2("-2147483648"));
	}

	/**
	 * ����д��
	 * 
	 * ���ֵ��Գ��Ľ��
	 * 
	 * @param str
	 * @return
	 */
	public int myAtoi(String str) {
		if (str == null || (str = str.trim()).length() == 0) {
			return 0;
		}

		int ans = 0, start = 0, n = str.length();
		boolean negative = false;
		if (str.charAt(0) == '-') {
			negative = true;
			start++;
		} else if (str.charAt(0) == '+') {
			start++;
		}

		if (start < n && (str.charAt(start) < '0' || str.charAt(start) > '9')) {
			return 0;
		}

		while (start < n) {
			int cur = str.charAt(start) - '0';
			if (cur < 0 || cur > 9) {
				return ans;
			}
			cur = negative ? -1 * cur : cur;

			if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && cur > Integer.MAX_VALUE % 10)) {
				return Integer.MAX_VALUE;
			}

			if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && cur < Integer.MIN_VALUE % 10)) {
				return Integer.MIN_VALUE;
			}

			ans = ans * 10 + cur;
			start++;
		}
		return ans;
	}

	/**
	 * ����һ���߼�
	 * 
	 * ����������һ��
	 * 
	 * @param str
	 * @return
	 */
	public int myAtoi2(String str) {
		if (str == null || (str = str.trim()).length() == 0) {
			return 0;
		}

		int ans = 0, index = 0;
		boolean negative = false;

		if (str.charAt(index) == '-') {
			negative = true;
		}
		if (str.charAt(index) == '+' || str.charAt(index) == '-') {
			index++;
		}

		while (index < str.length() && Character.isDigit(str.charAt(index))) {
			int pop = str.charAt(index) - '0';

			// ��Ϊ�����͸�����ĩλ�ǽ����ŵ� ���Կ������ϳ�һ�� ��Ȼ��Ҫ�������͸������ж�һ��
			if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
				return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
			}

			ans = ans * 10 + pop;
			index++;
		}
		return negative ? -ans : ans;
	}
}
