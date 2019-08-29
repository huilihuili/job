package vip.eagleli.programming.shen.ce.shu.ju;

import java.util.Scanner;

public class HenZanDeZhengShuDui {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int x = scanner.nextInt(), y = scanner.nextInt(), m = scanner.nextInt();
		System.out.println(sovle(x, y, m));
	}

	private static int sovle(int x, int y, int m) {
		if (Math.max(x, y) >= m) {
			return 0;
		}
		if (Math.max(x, y) <= 0) {
			return -1;
		}
		int res = 0;
		while (true) {
			int t = x + y;
			x = Math.max(x, y);
			y = t;
			res++;
			if (Math.max(x, y) >= m) {
				return res;
			}
		}
	}
}
