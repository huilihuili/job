package vip.eagleli.programming.t360;

import java.util.Scanner;

public class BiaoMianJi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), m = scanner.nextInt();
		int[][] arr = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				arr[i][j] = scanner.nextInt();
			}
		}
		System.out.println(solve(n, m, arr));
	}

	private static long solve(int n, int m, int[][] arr) {
		long a = 0, b = 0, c = 0;
		for (int i = 0; i < n; i++) {
			int temp = Integer.MIN_VALUE;
			for (int j = 0; j < m; j++) {
				if (arr[i][j] > 0) {
					a++;
				} else {
					if (j > 0) {
						b += arr[i][j - 1];
					}
					if (j < m - 1) {
						b += arr[i][j + 1];
					}
				}
				temp = Math.max(temp, arr[i][j]);
			}
			b += temp;
		}

		for (int j = 0; j < m; j++) {
			int temp = Integer.MIN_VALUE;
			for (int i = 0; i < n; i++) {
				temp = Math.max(temp, arr[i][j]);
			}
			c += temp;
		}
		return (a + b + c) * 2;
	}
}
