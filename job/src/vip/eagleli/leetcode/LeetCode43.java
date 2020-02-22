package vip.eagleli.leetcode;

public class LeetCode43 {

	public static void main(String[] args) {
		System.out.println(new LeetCode43().multiply3("123", "456"));
	}

	public String multiply(String num1, String num2) {
		if (num1 == null || num2 == null || num1.equals("") || num2.equals("") || num1.equals("0")
				|| num2.equals("0")) {
			return "0";
		}

		if (num1.length() < num2.length()) {
			return multiply(num2, num1);
		}

		int maxLength = num1.length() + num2.length();
		int[][] outcomes = new int[num2.length()][maxLength];
		for (int i = num2.length() - 1; i >= 0; i--) {
			compute(num1, num2.charAt(i), outcomes[num2.length() - 1 - i], num2.length() - 1 - i);
		}
		for (int i = 1; i < outcomes.length; i++) {
			add(outcomes[i - 1], outcomes[i], outcomes[i]);
		}

		return splitJoin(outcomes[outcomes.length - 1]);
	}

	public String multiply2(String num1, String num2) {
		if (num1 == null || num2 == null || num1.equals("") || num2.equals("") || num1.equals("0")
				|| num2.equals("0")) {
			return "0";
		}

		if (num1.length() < num2.length()) {
			return multiply(num2, num1);
		}

		int maxLength = num1.length() + num2.length();
		int[][] outcomes = new int[2][maxLength];
		int index = 0;
		for (int i = num2.length() - 1; i >= 0; i--) {
			for (int j = outcomes[index].length - 1 - (num2.length() - 1 - i); j < outcomes[index].length; j++) {
				outcomes[index][j] = 0;
			}
			compute(num1, num2.charAt(i), outcomes[index], num2.length() - 1 - i);
			add(outcomes[1 - index], outcomes[index], outcomes[index]);
			index = 1 - index;
		}
		return splitJoin(outcomes[1 - index]);
	}

	public String multiply3(String num1, String num2) {
		if (num1 == null || num2 == null || num1.equals("") || num2.equals("") || num1.equals("0")
				|| num2.equals("0")) {
			return "0";
		}

		String res = "0";
		for (int i = num2.length() - 1; i >= 0; i--) {
			String multiple = multiple(num1, num2.charAt(i), num2.length() - 1 - i);
			res = add(res, multiple);
		}
		return new StringBuilder(res).reverse().toString();
	}

	public String multiply4(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		int[] res = new int[num1.length() + num2.length()];
		for (int i = num1.length() - 1; i >= 0; i--) {
			int n1 = num1.charAt(i) - '0';
			for (int j = num2.length() - 1; j >= 0; j--) {
				int n2 = num2.charAt(j) - '0';
				int sum = (res[i + j + 1] + n1 * n2);
				res[i + j + 1] = sum % 10;
				res[i + j] += sum / 10;
			}
		}
		return splitJoin(res);
	}

	public String multiple(String num1, char c, int zeroNum) {
		StringBuilder ans = new StringBuilder();
		for (int j = 0; j < zeroNum; j++) {
			ans.append(0);
		}
		int n2 = c - '0';
		int carry = 0;
		for (int j = num1.length() - 1; j >= 0; j--) {
			int multiple = (num1.charAt(j) - '0') * n2 + carry;
			ans.append(multiple % 10);
			carry = multiple / 10;
		}
		return carry == 0 ? ans.toString() : ans.append(carry).toString();
	}

	public String add(String num1, String num2) {
		StringBuilder ans = new StringBuilder();
		int carry = 0;
		for (int i = 0, j = 0; i < num1.length() || j < num2.length() || carry != 0; i++, j++) {
			int x = i >= num1.length() ? 0 : num1.charAt(i) - '0';
			int y = j >= num2.length() ? 0 : num2.charAt(j) - '0';
			int sum = x + y + carry;
			ans.append(sum % 10);
			carry = sum / 10;
		}
		return ans.toString();
	}

	public void compute(String num, char c, int[] outcome, int start) {
		int mark = 0;
		for (int i = num.length() - 1; i >= 0; i--) {
			int multiple = (num.charAt(i) - '0') * (c - '0') + mark;
			outcome[outcome.length - 1 - start - (num.length() - 1 - i)] = multiple % 10;
			mark = multiple / 10;
		}
		outcome[outcome.length - 1 - start - num.length()] = mark;
	}

	public void add(int[] num1, int[] num2, int[] outcome) {
		int mark = 0;
		for (int i = num1.length - 1; i >= 0; i--) {
			int sum = num1[i] + num2[i] + mark;
			outcome[i] = sum % 10;
			mark = sum / 10;
		}
	}

	public String splitJoin(int[] num) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < num.length; i++) {
			if (i == 0 && num[i] == 0) {
				continue;
			}
			sb.append(num[i]);
		}
		return sb.toString();
	}

}
