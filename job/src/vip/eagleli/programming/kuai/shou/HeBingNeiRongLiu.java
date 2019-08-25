package vip.eagleli.programming.kuai.shou;

import java.util.Scanner;

public class HeBingNeiRongLiu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String str1 = scanner.nextLine();
		String str2 = scanner.nextLine();
		System.out.println(solve(str1, str2));
	}

	private static String solve(String str1, String str2) {
		String[] c1 = str1.split(" "), c2 = str2.split(" ");
		int l1 = 0, l2 = 0;
		StringBuilder stringBuilder = new StringBuilder();
		while (l1 < c1.length && l2 < c2.length) {
			if (l1 != 0 && l1 % 4 == 0) {
				stringBuilder.append(c2[l2++] + " ");
			}
			stringBuilder.append(c1[l1++] + " ");
		}
		while (l1 < c1.length) {
			stringBuilder.append(c1[l1++] + " ");
		}
		while (l2 < c2.length) {
			stringBuilder.append(c2[l2++] + " ");
		}
		return stringBuilder.toString();
	}
}
