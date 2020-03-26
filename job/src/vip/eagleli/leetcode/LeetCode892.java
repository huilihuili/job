package vip.eagleli.leetcode;

public class LeetCode892 {
	public int surfaceArea(int[][] grid) {
		int ans = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				ans += grid[i][j] == 0 ? 0 : 6 * grid[i][j] - 2 * (grid[i][j] - 1);
				if (j - 1 >= 0) {
					ans -= 2 * Math.min(grid[i][j], grid[i][j - 1]);
				}
				if (i - 1 >= 0) {
					ans -= 2 * Math.min(grid[i][j], grid[i - 1][j]);
				}
			}
		}
		return ans;
	}

	public int surfaceArea_(int[][] grid) {
		int ans = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] > 0) {
					ans += 2 + grid[i][j] << 2; // 2个底面 + 所有的正方体都贡献了4个侧表面积
					ans += j > 0 ? Math.min(grid[i][j], grid[i][j - 1]) << 1 : 0;
					ans += i > 0 ? Math.min(grid[i][j], grid[i - 1][j]) << 1 : 0;
				}
			}
		}
		return ans;
	}

	public int surfaceArea2(int[][] grid) {
		int[] dr = new int[] { 0, 1, 0, -1 };
		int[] dc = new int[] { 1, 0, -1, 0 };

		int N = grid.length;
		int ans = 0;

		for (int r = 0; r < N; ++r) {
			for (int c = 0; c < N; ++c) {
				if (grid[r][c] > 0) {
					ans += 2;
					for (int k = 0; k < 4; ++k) {
						int nr = r + dr[k];
						int nc = c + dc[k];
						int nv = 0;
						if (0 <= nr && nr < N && 0 <= nc && nc < N) {
							nv = grid[nr][nc];
						}
						ans += Math.max(grid[r][c] - nv, 0);
					}
				}
			}
		}
		return ans;
	}

	public int surfaceArea2_(int[][] grid) {
		int N = grid.length;
		int res = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				// 上边露出的表面积
				res += i - 1 >= 0 ? Math.max(grid[i][j] - grid[i - 1][j], 0) : grid[i][j];
				// 下边露出的表面积
				res += i + 1 < N ? Math.max(grid[i][j] - grid[i + 1][j], 0) : grid[i][j];
				// 左边露出的表面积
				res += j - 1 >= 0 ? Math.max(grid[i][j] - grid[i][j - 1], 0) : grid[i][j];
				// 右边露出的表面积
				res += j + 1 < N ? Math.max(grid[i][j] - grid[i][j + 1], 0) : grid[i][j];
				// 顶部、底部的表面积
				res += 2 * (grid[i][j] > 0 ? 1 : 0);
			}
		}
		return res;
	}

}
