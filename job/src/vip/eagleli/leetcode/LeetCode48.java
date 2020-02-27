package vip.eagleli.leetcode;

import java.util.Arrays;

public class LeetCode48 {
	public static void main(String[] args) {
		int[][] matrix = { { 5, 1, 9, 11 }, { 2, 4, 8, 10 }, { 13, 3, 6, 7 }, { 15, 14, 12, 16 } };
		Arrays.stream(matrix).forEach((nums) -> {
			System.out.println(Arrays.toString(nums));
		});
		System.out.println();
		LeetCode48 leetCode48 = new LeetCode48();
		leetCode48.rotate2(matrix);
		Arrays.stream(matrix).forEach((nums) -> {
			System.out.println(Arrays.toString(nums));
		});
	}

	public void rotate(int[][] matrix) {
		int m = 0, n = matrix.length - 1;
		while (m < n) {
			for (int i = 0; m + i < n; i++) {
				rotate(matrix, m, n, i);
			}
			m++;
			n--;
		}
	}

	private void rotate(int[][] matrix, int m, int n, int index) {
		int temp = matrix[m][m + index];
		matrix[m][m + index] = matrix[n - index][m];
		matrix[n - index][m] = matrix[n][n - index];
		matrix[n][n - index] = matrix[m + index][n];
		matrix[m + index][n] = temp;
	}

	public void rotate2(int[][] matrix) {
		for (int i = 0, n = matrix.length; i < n; i++) {
			for (int j = i; j < n - 1 - i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][i];
				matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
				matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
				matrix[j][n - 1 - i] = temp;
			}
		}
	}

	public void rotate3(int[][] matrix) {
		int n = matrix.length;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n - i; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - j][n - 1 - i];
				matrix[n - 1 - j][n - 1 - i] = temp;
			}
		}

		for (int i = 0; i < n / 2; i++) {
			for (int j = 0; j < n; j++) {
				int temp = matrix[i][j];
				matrix[i][j] = matrix[n - 1 - i][j];
				matrix[n - 1 - i][j] = temp;
			}
		}
	}

}
