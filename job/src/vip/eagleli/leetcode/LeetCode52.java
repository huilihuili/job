package vip.eagleli.leetcode;

public class LeetCode52 {

	public static void main(String[] args) {
		LeetCode52 leetCode52 = new LeetCode52();
		System.out.println(leetCode52.totalNQueens3(4));
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

	int res;

	public int totalNQueens3(int n) {
		dfs(n, 0, 0, 0, 0);
		return res;
	}

	private void dfs(int n, int row, int col, int ld, int rd) {
		if (row >= n) {
			res++;
			return;
		}
		// 将所有能放置 Q 的位置由 0 变成 1，以便进行后续的位遍历
		int bits = ~(col | ld | rd) & ((1 << n) - 1);
		while (bits > 0) {
			int pick = bits & -bits; // 注: x & -x
			dfs(n, row + 1, col | pick, (ld | pick) << 1, (rd | pick) >> 1);
			bits &= bits - 1; // 注: x & (x - 1)
		}
	}

}
