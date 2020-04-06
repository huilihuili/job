package vip.eagleli.leetcode.weekly.contest_183;

public class LongestDiverseString_5195 {
	public static void main(String[] args) {
		LongestDiverseString_5195 longestDiverseString_5195 = new LongestDiverseString_5195();
		System.out.println(longestDiverseString_5195.longestDiverseString(1, 1, 7));
	}

	public String longestDiverseString(int a, int b, int c) {
		StringBuilder stringBuilder = new StringBuilder();
		while (a > 1 || b > 1 || c > 1) {
			if (a > 1 && judge(stringBuilder, 'a')) {
				a -= 2;
				stringBuilder.append("aa");
			}

			if (b > 1 && judge(stringBuilder, 'b')) {
				b -= 2;
				stringBuilder.append("bb");
			}

			if (c > 1 && judge(stringBuilder, 'c')) {
				c -= 2;
				stringBuilder.append("cc");
			}
		}
		while (a > 0 || b > 0 || c > 0) {
			if (a > 0 && judge(stringBuilder, 'a')) {
				a -= 1;
				stringBuilder.append("a");
			}

			if (b > 0 && judge(stringBuilder, 'b')) {
				b -= 1;
				stringBuilder.append("b");
			}

			if (c > 0 && judge(stringBuilder, 'c')) {
				c -= 1;
				stringBuilder.append("c");
			}
		}
		return stringBuilder.toString();
	}

	private boolean judge(StringBuilder stringBuilder, char c) {
		if (stringBuilder.length() == 0 || stringBuilder.charAt(stringBuilder.length() - 1) != c) {
			return true;
		}
		return false;
	}
}
