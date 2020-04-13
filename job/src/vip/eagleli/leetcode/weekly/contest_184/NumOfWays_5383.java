package vip.eagleli.leetcode.weekly.contest_184;

public class NumOfWays_5383 {
	public static void main(String[] args) {
		NumOfWays_5383 numOfWays_5383 = new NumOfWays_5383();
		numOfWays_5383.numOfWays(1);
	}

	int ans = 0;

	public int numOfWays(int n) {
		int[][] grid = new int[n][3];
		numOfWays(n, 0, 0, grid);
		return ans;
	}

	public void numOfWays(int n, int r, int c, int[][] grid) {
		if (c == 3) {
			r++;
			c = 0;
		}

		if (r == n) {
			ans++;
			ans %= 1_000_000_007;
			return;
		}

		for (int i = 1; i <= 3; i++) {
			if (r > 0 && grid[r - 1][c] == i) {
				continue;
			}
			if (c > 0 && grid[r][c - 1] == i) {
				continue;
			}
			grid[r][c] = i;
			numOfWays(n, r, c + 1, grid);
			grid[r][c] = 0;
		}
	}

}
