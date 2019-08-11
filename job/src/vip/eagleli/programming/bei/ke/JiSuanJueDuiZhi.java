package vip.eagleli.programming.bei.ke;

import java.util.Scanner;

public class JiSuanJueDuiZhi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextLong();
		}
		long[] res = solve(n, arr);
		System.out.println(res[0] + " " + res[1]);
	}

	private static long[] solve(int n, long[] arr) {
		long[] res = new long[2];
		long min = Long.MAX_VALUE;
		for (int i = 0; i < n - 1; i++) {
			long diff = Math.abs(arr[i + 1] - arr[i]);
			if (diff < min) {
				res[0] = arr[i];
				res[1] = arr[i + 1];
				min = diff;
			}
		}
		return res;
	}
}
