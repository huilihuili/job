package vip.eagleli.programming.five.eight;

import java.util.Scanner;

public class JuXingDongTaiGuiHua {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int m = scanner.nextInt(), n = scanner.nextInt();
		int[][] arr = new int[m][n];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				arr[i][j] = scanner.nextInt();
			}
		}
		System.out.println(solve(arr));
	}

	private static int solve(int[][] arr) {
		int m = arr.length, n = arr[0].length;
		int[][] res = new int[arr.length][arr[0].length];
		res[m - 1][n - 1] = arr[m - 1][n - 1];
		for (int i = m - 2; i > -1; i--) {
			res[i][n - 1] = arr[i][n - 1] + res[i + 1][n - 1];
		}

		for (int i = n - 2; i > -1; i--) {
			res[m - 1][i] = arr[m - 1][i] + res[m - 1][i + 1];
		}

		for (int i = m - 2; i > -1; i--) {
			for (int j = n - 2; j > -1; j--) {
				res[i][j] = arr[i][j] + Math.min(res[i + 1][j], res[i][j + 1]);
			}
		}
		return res[0][0];
	}

	private static int solve(int[][] arr, int i, int j) {
		if (i == arr.length - 1 && j == arr[0].length - 1) {
			return arr[arr.length - 1][arr[0].length - 1];
		}

		if (i == arr.length - 1) {
			return arr[i][j] + solve(arr, i, j + 1);
		}

		if (j == arr.length - 1) {
			return arr[i][j] + solve(arr, i + 1, j);
		}
		return arr[i][j] + Math.min(solve(arr, i + 1, j), solve(arr, i, j + 1));
	}
}
