package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode51 {

	public static void main(String[] args) {
		LeetCode51 leetCode51 = new LeetCode51();
		List<List<String>> solveNQueens = leetCode51.solveNQueens(5);
		solveNQueens.forEach(System.out::println);
	}

	public List<List<String>> solveNQueens(int n) {
		List<List<String>> ans = new ArrayList<List<String>>();
		backtrack(n, 0, new int[n], ans);
		return ans;
	}

	public void backtrack(int n, int index, int[] locations, List<List<String>> ans) {
		if (index == n) {
			ans.add(solution(locations));
			return;
		}

		for (int i = 1; i <= n; i++) {
			if (isNotUnderAttack(locations, index, i)) {
				locations[index] = i;
				backtrack(n, index + 1, locations, ans);
			}
		}
	}

	private boolean isNotUnderAttack(int[] locations, int index, int location) {
		if (index == 0) {
			return true;
		}

		for (int i = 0; i < index; i++) {
			if (locations[i] == location) {
				return false;
			}

			if (Math.abs(location - locations[i]) == Math.abs(index - i)) {
				return false;
			}
		}

		return true;
	}

	private List<String> solution(int[] locations) {
		int n = locations.length;
		List<String> list = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			StringBuilder stringBuilder = new StringBuilder();
			for (int j = 1; j < locations[i]; j++) {
				stringBuilder.append(".");
			}
			stringBuilder.append("Q");
			for (int j = locations[i] + 1; j <= n; j++) {
				stringBuilder.append(".");
			}
			list.add(stringBuilder.toString());
		}
		return list;
	}

	int rows[];
	int hills[];
	int dales[];
	int queens[];
	int n;
	List<List<String>> output = new ArrayList<>();

	public List<List<String>> solveNQueens2(int n) {
		this.n = n;
		rows = new int[n];
		hills = new int[2 * n - 1];
		dales = new int[2 * n - 1];
		queens = new int[n];

		backtrack(0);
		return output;
	}

	public void backtrack(int row) {
		if (row == n) {
			addSolution();
			return;
		}
		for (int col = 0; col < n; col++) {
			if (isNotUnderAttack(row, col)) {
				placeQueen(row, col);
				backtrack(row + 1);
				removeQueen(row, col);
			}
		}
	}

	public boolean isNotUnderAttack(int row, int col) {
		int res = rows[col] + hills[row - col + n - 1] + dales[row + col];
		return res == 0 ? true : false;
	}

	public void placeQueen(int row, int col) {
		queens[row] = col;
		rows[col] = 1;
		hills[row - col + n - 1] = 1;
		dales[row + col] = 1;
	}

	public void removeQueen(int row, int col) {
		queens[row] = 0;
		rows[col] = 0;
		hills[row - col + n - 1] = 0;
		dales[row + col] = 0;
	}

	public void addSolution() {
		List<String> solution = new ArrayList<String>();
		for (int i = 0; i < n; ++i) {
			int col = queens[i];
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < col; ++j) {
				sb.append(".");
			}
			sb.append("Q");
			for (int j = 0; j < n - col - 1; ++j) {
				sb.append(".");
			}
			solution.add(sb.toString());
		}
		output.add(solution);
	}

}
