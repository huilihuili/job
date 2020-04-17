package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCode542 {
	public static void main(String[] args) {
		LeetCode542 leetCode542 = new LeetCode542();
		int[][] matrix = { { 0, 0, 0 }, { 0, 1, 0 }, { 0, 0, 0 } };
		leetCode542.updateMatrix(matrix);
	}

	/**
	 * 超时
	 * 
	 * @param matrix
	 * @return
	 */
	public int[][] updateMatrix(int[][] matrix) {
		int[][] ans = new int[matrix.length][];
		for (int i = 0; i < matrix.length; i++) {
			ans[i] = new int[matrix[i].length];
			Arrays.fill(ans[i], Integer.MAX_VALUE);
		}
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] == 0) {
					ans[i][j] = 0;
					bfs(matrix, ans, i, j);
				}
			}
		}
		return ans;
	}

	private void bfs(int[][] matrix, int[][] ans, int r, int c) {
		Queue<int[]> queue = new LinkedList<>();
		queue.offer(new int[] { r, c });

		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			int[] dx = { 0, 1, 0, -1 };
			int[] dy = { 1, 0, -1, 0 };
			for (int i = 0; i < 4; i++) {
				int x = cur[0], y = cur[1];
				int nx = x + dx[i], ny = y + dy[i];
				if (nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[r].length || matrix[nx][ny] == 0
						|| ans[nx][ny] <= ans[x][y] + 1) {
					continue;
				}
				ans[nx][ny] = ans[x][y] + 1;
				queue.offer(new int[] { nx, ny });
			}
		}
	}

	static final int[][] DIRS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	public int[][] updateMatrix2(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[][] ans = new int[m][n];
		boolean[][] seen = new boolean[m][n];
		Queue<int[]> queue = new LinkedList<>();

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					queue.offer(new int[] { i, j });
					seen[i][j] = true;
				}
			}
		}
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			for (int i = 0; i < 4; i++) {
				int x = cur[0], y = cur[1];
				int nx = x + DIRS[i][0], ny = y + DIRS[i][1];
				if (nx < 0 || ny < 0 || nx >= matrix.length || ny >= matrix[0].length || seen[nx][ny]) {
					continue;
				}
				ans[nx][ny] = ans[x][y] + 1;
				seen[nx][ny] = true;
				queue.offer(new int[] { nx, ny });
			}
		}
		return ans;
	}

	public int[][] updateMatrix3(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[][] dist = new int[m][n];

		for (int i = 0; i < m; i++) {
			dist[i] = new int[n];
			Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					dist[i][j] = 0;
				}
			}
		}

		// 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
				}
				if (j - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
				}
			}
		}
		// 只有 水平向左移动 和 竖直向下移动，注意动态规划的计算顺序
		for (int i = m - 1; i >= 0; --i) {
			for (int j = 0; j < n; ++j) {
				if (i + 1 < m) {
					dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
				}
				if (j - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
				}
			}
		}
		// 只有 水平向右移动 和 竖直向上移动，注意动态规划的计算顺序
		for (int i = 0; i < m; ++i) {
			for (int j = n - 1; j >= 0; --j) {
				if (i - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
				}
				if (j + 1 < n) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
				}
			}
		}
		// 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
		for (int i = m - 1; i >= 0; --i) {
			for (int j = n - 1; j >= 0; --j) {
				if (i + 1 < m) {
					dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
				}
				if (j + 1 < n) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
				}
			}
		}

		return dist;
	}

	public int[][] updateMatrix4(int[][] matrix) {
		int m = matrix.length, n = matrix[0].length;
		int[][] dist = new int[m][n];

		for (int i = 0; i < m; i++) {
			dist[i] = new int[n];
			Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					dist[i][j] = 0;
				}
			}
		}

		// 只有 水平向左移动 和 竖直向上移动，注意动态规划的计算顺序
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if (i - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
				}
				if (j - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
				}
			}
		}

		// 只有 水平向右移动 和 竖直向下移动，注意动态规划的计算顺序
		for (int i = m - 1; i >= 0; --i) {
			for (int j = n - 1; j >= 0; --j) {
				if (i + 1 < m) {
					dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
				}
				if (j + 1 < n) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
				}
			}
		}

		return dist;
	}
	
	public int[][] updateMatrix4_(int[][] matrix) {

		int m = matrix.length, n = matrix[0].length;
		int[][] dist = new int[m][n];

		for (int i = 0; i < m; i++) {
			dist[i] = new int[n];
			Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == 0) {
					dist[i][j] = 0;
				}
			}
		}


		// 只有 水平向左移动 和 竖直向下移动，注意动态规划的计算顺序
		for (int i = m - 1; i >= 0; --i) {
			for (int j = 0; j < n; ++j) {
				if (i + 1 < m) {
					dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
				}
				if (j - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
				}
			}
		}
		// 只有 水平向右移动 和 竖直向上移动，注意动态规划的计算顺序
		for (int i = 0; i < m; ++i) {
			for (int j = n - 1; j >= 0; --j) {
				if (i - 1 >= 0) {
					dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
				}
				if (j + 1 < n) {
					dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
				}
			}
		}

		return dist;
	}

}
