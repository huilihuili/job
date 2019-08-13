package vip.eagleli.programming.zi.jie.tiao.dong;

import java.util.Scanner;

public class NaoZhongJiaoXingQuShangKe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] h = new int[n], m = new int[n];
		for (int i = 0; i < n; i++) {
			h[i] = scanner.nextInt();
			m[i] = scanner.nextInt();
		}
		int x = scanner.nextInt(), a = scanner.nextInt(), b = scanner.nextInt();
		int res = solve(n, h, m, x, a, b);
		System.out.println(h[res] + " " + m[res]);
	}

	private static int solve(int n, int[] h, int[] m, int x, int a, int b) {
		int classTime = a * 60 + b - x;
		int min = Integer.MIN_VALUE;
		int res = 0;
		for (int i = 0; i < n; i++) {
			int cur = h[i] * 60 + m[i];
			if (cur <= classTime && cur > min) {
				min = cur;
				res = i;
			}
		}
		return res;
	}
}
