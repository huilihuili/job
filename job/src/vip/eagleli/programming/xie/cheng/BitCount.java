package vip.eagleli.programming.xie.cheng;

import java.util.Scanner;

public class BitCount {
	public static void main(String[] args) {
		long l = new Scanner(System.in).nextLong();
		System.out.println(solve(l));
	}

	private static int solve(long l) {
		int res = 0;
		while (l != 0) {
			res += (l & 1) == 1 ? 1 : 0;
			l >>>= 1;
		}
		return res;
	}
}
