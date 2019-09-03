package vip.eagleli.programming.vip.kid;

import java.util.Scanner;

public class ZhengShuErJinZhiDeGeShu {
	public static void main(String[] args) {
		System.out.println(solve(new Scanner(System.in).nextInt()));
	}

	private static int solve(int i) {
		int res = 0;
		while (i != 0) {
			res += (i & 1);
			i >>>= 1;
		}
		return res;
	}
}
