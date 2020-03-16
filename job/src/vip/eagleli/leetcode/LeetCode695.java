package vip.eagleli.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class LeetCode695 {
	/**
	 * 递归深度遍历
	 * 
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland(int[][] grid) {
		int ans = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					ans = Math.max(ans, areaOfIsland(grid, i, j));
				}
			}
		}
		return ans;
	}

	public int areaOfIsland(int[][] grid, int r, int c) {
		if (r < 0 || c < 0 || r >= grid.length || c >= grid[r].length || grid[r][c] != 1) {
			return 0;
		}
		grid[r][c] = 0;
		int res = 1;
		res += areaOfIsland(grid, r + 1, c);
		res += areaOfIsland(grid, r, c + 1);
		res += areaOfIsland(grid, r - 1, c);
		res += areaOfIsland(grid, r, c - 1);
		return res;
	}

	public int maxAreaOfIsland_(int[][] grid) {
		int ans = 0;
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				ans = Math.max(ans, areaOfIsland_(grid, i, j));
			}
		}
		return ans;
	}

	public int areaOfIsland_(int[][] grid, int r, int c) {
		if (r < 0 || c < 0 || r >= grid.length || c >= grid[r].length || grid[r][c] != 1) {
			return 0;
		}
		grid[r][c] = 0;
		int[] di = { 0, 1, 0, -1 };
		int[] dj = { 1, 0, -1, 0 };
		int res = 1;
		for (int i = 0; i < di.length; i++) {
			res += areaOfIsland_(grid, r + di[i], c + dj[i]);
		}
		return res;
	}

	/**
	 * 非递归深度遍历
	 * 
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland2(int[][] grid) {
		int ans = 0;
		int[] di = { 0, 1, 0, -1 };
		int[] dj = { 1, 0, -1, 0 };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					int cur = 0;
					LinkedList<Integer> stacki = new LinkedList<>();
					LinkedList<Integer> stackj = new LinkedList<>();
					stacki.push(i);
					stackj.push(j);
					while (!stacki.isEmpty()) {
						int r = stacki.pop();
						int c = stackj.pop();
						if (r < 0 || c < 0 || r >= grid.length || c >= grid[r].length || grid[r][c] != 1) {
							continue;
						}
						grid[r][c] = 0;
						cur++;
						for (int k = 0; k < di.length; k++) {
							stacki.push(r + di[k]);
							stackj.push(c + dj[k]);
						}
					}
					ans = Math.max(ans, cur);
				}
			}
		}
		return ans;
	}

	/**
	 * 广度遍历
	 * 
	 * @param grid
	 * @return
	 */
	public int maxAreaOfIsland3(int[][] grid) {
		int ans = 0;
		int[][] moves = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				if (grid[i][j] == 1) {
					int cur = 0;
					Queue<int[]> queue = new LinkedList<>();
					queue.offer(new int[] { i, j });
					while (!queue.isEmpty()) {
						int[] rc = queue.poll();
						int r = rc[0], c = rc[1];
						if (r < 0 || c < 0 || r >= grid.length || c >= grid[r].length || grid[r][c] != 1) {
							continue;
						}
						grid[r][c] = 0;
						cur++;
						for (int k = 0; k < moves.length; k++) {
							queue.offer(new int[] { r + moves[k][0], c + moves[k][1] });
						}
					}
					ans = Math.max(ans, cur);
				}
			}
		}
		return ans;
	}
}
