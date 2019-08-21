package vip.eagleli.programming.bilibili;

import java.util.Arrays;
import java.util.Scanner;

public class ShuZuPaiChengZuiXiaoDeShu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.next();
		System.out.println(solve(string));
	}

	private static String solve(String string) {
		if (string == null || string.length() == 0) {
			return "0";
		}
		String[] strings = string.split(",");
		Arrays.sort(strings, (str1, str2) -> {
			return Long.compare(Long.valueOf(str1 + str2), Long.valueOf(str2 + str1));
		});
		StringBuilder stringBuilder = new StringBuilder();
		for (String str : strings) {
			stringBuilder.append(str);
		}
		return stringBuilder.toString();
	}
}
