package vip.eagleli.leetcode;

public class LeetCode52 {

	public static void main(String[] args) {
	}

	public int totalNQueens(int n) {
		int[] ans = new int[1];
		backtrack(n, 0, new int[n], ans);
		return ans[0];
	}

	public void backtrack(int n, int index, int[] locations, int[] ans) {
		if (index == n) {
			ans[0]++;
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

	boolean rows[];
	boolean hills[];
	boolean dales[];
	int n;
	int ans;

	public int totalNQueens2(int n) {
		this.n = n;
		ans = 0;
		rows = new boolean[n];
		hills = new boolean[2 * n - 1];
		dales = new boolean[2 * n - 1];

		backtrack(0);
		return ans;
	}

	public void backtrack(int row) {
		if (row == n) {
			ans++;
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
		return !rows[col] && !hills[row - col + n - 1] && !dales[row + col];
	}

	public void placeQueen(int row, int col) {
		rows[col] = hills[row - col + n - 1] = dales[row + col] = true;
	}

	public void removeQueen(int row, int col) {
		rows[col] = hills[row - col + n - 1] = dales[row + col] = false;
	}

}
