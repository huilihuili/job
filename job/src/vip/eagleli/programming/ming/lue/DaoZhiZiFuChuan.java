package vip.eagleli.programming.ming.lue;

import java.util.Scanner;

public class DaoZhiZiFuChuan {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(solve2(string));
	}

	private static String solve(String string) {
		String[] strings = string.split(" ");
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = strings.length - 1; i >= 0; i--) {
			if (i < strings.length - 1) {
				stringBuilder.append(" ");
			}
			stringBuilder.append(strings[i]);
		}
		return stringBuilder.toString();
	}

	private static String solve2(String string) {
		char[] cs = string.toCharArray();
		reverse(cs, 0, cs.length - 1);
		StringBuilder stringBuilder = new StringBuilder();
		int l = 0;
		for (int i = 0; i < cs.length; i++) {
			if (cs[i] == ' ') {
				reverse(cs, l, i - 1);
				l = i + 1;
			}
		}
		reverse(cs, l, string.length() - 1);
		for (char c : cs) {
			stringBuilder.append(c);
		}
		return stringBuilder.toString();
	}

	private static void reverse(char[] cs, int l, int r) {
		while (l < r) {
			char c = cs[l];
			cs[l] = cs[r];
			cs[r] = c;
			l++;
			r--;
		}
	}
}
