package vip.eagleli.leetcode.weekly.contest_180;

import java.util.ArrayList;
import java.util.List;

public class CustomStack_5357 {
	public List<Integer> luckyNumbers(int[][] matrix) {
		List<Integer> ans = new ArrayList<Integer>();
		int[] rowMins = new int[matrix.length];
		int[] colMaxs = new int[matrix[0].length];
		for (int i = 0; i < matrix.length; i++) {
			int min = Integer.MAX_VALUE;
			for (int j = 0; j < matrix[i].length; j++) {
				min = Math.min(min, matrix[i][j]);
			}
			rowMins[i] = min;
		}
		for (int j = 0; j < matrix[0].length; j++) {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < matrix.length; i++) {
				max = Math.max(max, matrix[i][j]);
			}
			colMaxs[j] = max;
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == rowMins[i] && matrix[i][j] == colMaxs[j]) {
					ans.add(matrix[i][j]);
				}
			}
		}
		return ans;
	}

}
