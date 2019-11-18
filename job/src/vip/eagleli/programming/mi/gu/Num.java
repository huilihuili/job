package vip.eagleli.programming.mi.gu;

import java.util.Scanner;

public class Num {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNextInt()) {
			int num = sc.nextInt();
			for (int i = 0; i <= num; i++) {
				if (i > 0) {
					System.out.print(",");
				}
				System.out.print(solve(i));
			}
			System.out.println();
		}
	}

	private static int solve(int n) {
		int res = 0;
		while (n != 0) {
			res += (n & 1) == 1 ? 1 : 0;
			n >>>= 1;
		}
		return res;
	}
}
