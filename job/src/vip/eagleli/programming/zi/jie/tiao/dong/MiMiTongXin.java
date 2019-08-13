package vip.eagleli.programming.zi.jie.tiao.dong;

import java.util.Scanner;

public class MiMiTongXin {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), k = scanner.nextInt();
		String str = scanner.next();
		System.out.println(solve(n, k, str));
	}

	private static String solve(int n, int k, String str) {
		int[] is = new int[str.length()];
		for (int i = 0; i < str.length(); i++) {
			is[i] = str.charAt(i) - '0';
		}
		int l = 0, r = str.length() - 1;
		int[] m = new int[n];
		for (int i = 0; i < n; i++) {
			m[i] = is[i];
			for (int j = Math.max(0, i - k + 1); j < i; j++) {
				m[i] ^= m[j];
			}
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < n; i++) {
			sb.append(m[i]);
		}
		return sb.toString();
	}
}
