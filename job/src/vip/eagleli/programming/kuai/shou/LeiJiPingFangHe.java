package vip.eagleli.programming.kuai.shou;

import java.util.HashSet;
import java.util.Scanner;

public class LeiJiPingFangHe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for (int i = 0; i < n; i++) {
			System.out.println(solve(scanner.nextInt()));
		}
	}

	private static boolean solve(int n) {
		HashSet<Integer> set = new HashSet<>();
		set.add(n);
		while (true) {
			n = getNumber(n);
			if (n == 1) {
				return true;
			}
			if (set.contains(n)) {
				return false;
			}
			set.add(n);
		}
	}

	private static int getNumber(int n) {
		int res = 0;
		while (n > 0) {
			res += (n % 10) * (n % 10);
			n /= 10;
		}
		return res;
	}
}
