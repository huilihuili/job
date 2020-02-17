package vip.eagleli.leetcode;

public class LeetCode38 {
	public static void main(String[] args) {
		System.out.println(new LeetCode38().countAndSay(6));
	}

	public String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}

		String pre = countAndSay(n - 1);
		// System.out.println(pre);
		int num = 1;
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < pre.length() - 1; i++) {
			if (pre.charAt(i) == pre.charAt(i + 1)) {
				num++;
				continue;
			}
			stringBuilder.append(num).append(pre.charAt(i));
			num = 1;
		}
		stringBuilder.append(num).append(pre.charAt(pre.length() - 1));
		return stringBuilder.toString();
	}

	public String countAndSay2(int n) {
		if (n == 1) {
			return "1";
		}

		String pre = countAndSay(n - 1);
		// System.out.println(pre);
		int num = 1;
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 1; i < pre.length(); i++) {
			if (pre.charAt(i) == pre.charAt(i - 1)) {
				num++;
			} else {
				stringBuilder.append(num).append(pre.charAt(i - 1));
				num = 1;
			}
		}
		stringBuilder.append(num).append(pre.charAt(pre.length() - 1));
		return stringBuilder.toString();
	}

	public String countAndSay3(int n) {
		String ans = "1";
		for (int j = 2; j <= n; j++) {
			int num = 1;
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 1; i < ans.length(); i++) {
				if (ans.charAt(i) == ans.charAt(i - 1)) {
					num++;
				} else {
					stringBuilder.append(num).append(ans.charAt(i - 1));
					num = 1;
				}
			}
			stringBuilder.append(num).append(ans.charAt(ans.length() - 1));
			ans = stringBuilder.toString();
		}
		return ans;
	}
}
