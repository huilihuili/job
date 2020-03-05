package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode54 {
	public static void main(String[] args) {
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		LeetCode54 leetCode54 = new LeetCode54();
		List<Integer> spiralOrder = leetCode54.spiralOrder(matrix);
		System.out.println(spiralOrder);
		spiralOrder = leetCode54.spiralOrder2(matrix);
		System.out.println(spiralOrder);
	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> ans = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return ans;
		}

		int row = 0, col = 0, m = matrix.length - 1, n = matrix[0].length - 1;
		while (row <= m && col <= n) {
			spiralOrder(matrix, ans, row, col, m, n);
			row++;
			col++;
			m--;
			n--;
		}
		return ans;
	}

	public void spiralOrder(int[][] matrix, List<Integer> ans, int row, int col, int m, int n) {
		if (row == m) {
			for (int i = col; i <= n; i++) {
				ans.add(matrix[row][i]);
			}
			return;
		}

		if (col == n) {
			for (int i = row; i <= m; i++) {
				ans.add(matrix[i][n]);
			}
			return;
		}

		for (int i = col; i < n; i++) {
			ans.add(matrix[row][i]);
		}

		for (int i = row; i < m; i++) {
			ans.add(matrix[i][n]);
		}

		for (int i = n; i > col; i--) {
			ans.add(matrix[m][i]);
		}

		for (int i = m; i > row; i--) {
			ans.add(matrix[i][col]);
		}
	}

	public List<Integer> spiralOrder2(int[][] matrix) {
		List<Integer> ans = new ArrayList<Integer>();
		if (matrix == null || matrix.length == 0) {
			return ans;
		}

		int r1 = 0, r2 = matrix.length - 1;
		int c1 = 0, c2 = matrix[0].length - 1;

		while (r1 <= r2 && c1 <= c2) {
			for (int c = c1; c <= c2; c++) {
				ans.add(matrix[r1][c]);
			}
			for (int r = r1 + 1; r <= r2; r++) {
				ans.add(matrix[r][c2]);
			}
			if (r1 < r2 && c1 < c2) {
				for (int c = c2 - 1; c >= c1; c--) {
					ans.add(matrix[r2][c]);
				}
				for (int r = r2 - 1; r > r1; r--) {
					ans.add(matrix[r][c1]);
				}
			}
			r1++;
			c1++;
			r2--;
			c2--;
		}
		return ans;
	}
}