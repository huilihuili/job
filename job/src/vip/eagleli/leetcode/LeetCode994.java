package vip.eagleli.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LeetCode994 {

	public static void main(String[] args) {
		LeetCode994 leetCode994 = new LeetCode994();
		int[][] grid = { { 2, 1, 1 }, { 1, 1, 0 }, { 0, 1, 1 } };
		System.out.println(leetCode994.orangesRotting(grid));

		grid = new int[][] { { 2, 1, 1 }, { 0, 1, 1 }, { 1, 0, 1 } };
		System.out.println(leetCode994.orangesRotting(grid));

		grid = new int[][] { { 0, 2 } };
		System.out.println(leetCode994.orangesRotting(grid));

		grid = new int[][] { { 1, 1, 2, 0, 2, 0 } };
		System.out.println(leetCode994.orangesRotting(grid));

		grid = new int[][] { { 1, 2, 1, 1, 2, 1, 1 } };
		System.out.println(leetCode994.orangesRotting(grid));

		grid = new int[][] { { 1 }, { 2 } };
		System.out.println(leetCode994.orangesRotting(grid));
	}

	static int EMPTY = 0;
	static int FRESH = 1;
	static int ROTTEN = 2;

	public int orangesRotting(int[][] grid) {
		Queue<Integer> queue = new LinkedList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int R = grid.length, C = grid[0].length;
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		int ans = 0;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == ROTTEN) {
					int code = i * C + j;
					queue.offer(code);
					map.put(code, 0);
				}
			}
		}

		while (!queue.isEmpty()) {
			int code = queue.poll();
			int r = code / C, c = code % C;
			for (int i = 0; i < dr.length; i++) {
				int nr = r + dr[i], nc = c + dc[i];
				if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == FRESH) {
					grid[nr][nc] = ROTTEN;
					int ncode = nr * C + nc;
					queue.add(ncode);
					map.put(ncode, map.get(code) + 1);
					ans = map.get(ncode);
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == FRESH) {
					return -1;
				}
			}
		}
		return ans;
	}

	public int orangesRotting2(int[][] grid) {
		Queue<Integer> queue = new LinkedList<Integer>();
		int R = grid.length, C = grid[0].length;
		int[] dr = { -1, 0, 1, 0 };
		int[] dc = { 0, 1, 0, -1 };
		int ans = -1;
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == ROTTEN) {
					int code = i * C + j;
					queue.offer(code);
				}
			}
		}

		while (!queue.isEmpty()) {
			ans++;
			int curCount = queue.size();
			while (curCount-- > 0) {
				int code = queue.poll();
				int r = code / C, c = code % C;
				for (int i = 0; i < dr.length; i++) {
					int nr = r + dr[i], nc = c + dc[i];
					if (nr >= 0 && nr < R && nc >= 0 && nc < C && grid[nr][nc] == FRESH) {
						grid[nr][nc] = ROTTEN;
						int ncode = nr * C + nc;
						queue.add(ncode);
					}
				}
			}
		}
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (grid[i][j] == FRESH) {
					return -1;
				}
			}
		}
		return ans == -1 ? 0 : ans;
	}
}
