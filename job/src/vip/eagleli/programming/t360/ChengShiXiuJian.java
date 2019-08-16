package vip.eagleli.programming.t360;

import java.util.Scanner;

public class ChengShiXiuJian {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long[][] arr = new long[n][2];
		for (int i = 0; i < n; i++) {
			arr[i] = new long[2];
			arr[i][0] = scanner.nextLong();
			arr[i][1] = scanner.nextLong();
		}
		System.out.println(solve(n, arr));
	}

	private static long solve(int n, long[][] arr) {
		long xmin = Long.MAX_VALUE, xmax = Long.MIN_VALUE, ymin = Long.MAX_VALUE, ymax = Long.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			xmin = Math.min(xmin, arr[i][0]);
			xmax = Math.max(xmax, arr[i][0]);

			ymin = Math.min(ymin, arr[i][1]);
			ymax = Math.max(ymax, arr[i][1]);
		}

		long max = Math.max(xmax - xmin, ymax - ymin);
		return max * max;
	}
}
