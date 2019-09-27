package vip.eagleli.programming.hua.wei;

import java.util.Scanner;

public class ZiFuChuanChuLi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(solve(string));
	}

	private static String solve(String string) {
		StringBuilder stb = new StringBuilder();
		for (char c : string.toCharArray()) {
			if ('A' <= c && c <= 'Z') {
				stb.append(Character.toLowerCase(c));
			} else if (c == ' ') {
				stb.append("0");
			} else {
				stb.append(c);
			}
		}
		return stb.reverse().toString();
	}
}
