package vip.eagleli.leetcode;

public class ZZiXingBianHuan {
	public static void main(String[] args) {
		System.out.println(convert("A", 1));
	}

	public static String convert(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		char[][] cs = new char[numRows][(int) Math.ceil((double)s.length() / (numRows + numRows - 2)) * (numRows - 1)];
		int col = 0;
		int index = 0;
		while (index < s.length()) {
			index = convert(cs, s, index, col, numRows);
			col += numRows - 1;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < cs.length; i++) {
			for (int j = 0; j < cs[0].length; j++) {
				if (cs[i][j] == '\0') {
					continue;
				}
				sb.append(cs[i][j]);
			}
		}
		return sb.toString();
	}

	public static int convert(char[][] cs, String s, int index, int col, int numRows) {
		for (int i = 0; i < numRows - 1; i++,index++) {
			if (index == s.length()) {
				return index;
			}
			cs[i][col] = s.charAt(index);
		}
		for (int i = numRows - 1, j = 0; i >= 1; i--, j++, index++) {
			if (index == s.length()) {
				return index;
			}
			cs[i][col + j] = s.charAt(index);
		}
		return index;
	}
}
