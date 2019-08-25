package vip.eagleli.programming.kuai.shou;

import java.util.Scanner;

public class BanBenShengJiPanDing {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println(solve(scanner.next(), scanner.next()));
		}
	}

	private static boolean solve(String str1, String str2) {
		int[] version1 = getVersion(str1);
		int[] version2 = getVersion(str2);
		int i1 = 0, i2 = 0;
		while (i1 < version1.length && i2 < version2.length) {
			if (version1[i1] == version2[i2]) {
				i1++;
				i2++;
				continue;
			}

			if (version1[i1] > version2[i2]) {
				return false;
			}

			if (version2[i2] > version1[i1]) {
				return true;
			}
		}
		if (i1 != version1.length) {
			return false;
		}
		if (i2 == version2.length) {
			return false;
		}
		boolean res = false;
		while (i2 < version2.length) {
			if (version2[i2] != 0) {
				res = true;
			}
			i2++;
		}
		return res;
	}

	private static int[] getVersion(String string) {
		String[] strings = string.split("\\.");
		int[] res = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			res[i] = Integer.valueOf(strings[i]);
		}
		return res;
	}
}
