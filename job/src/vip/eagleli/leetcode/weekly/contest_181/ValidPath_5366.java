package vip.eagleli.leetcode.weekly.contest_181;

public class ValidPath_5366 {

	public static void main(String[] args) {
		ValidPath_5366 validPath_5366 = new ValidPath_5366();
		int[][] grid = { { 3, 4, 3, 4 }, { 2, 2, 2, 2 }, { 6, 5, 6, 5 } };
		System.out.println(validPath_5366.hasValidPath(grid));
	}

	public boolean hasValidPath(int[][] grid) {
		int m = grid.length, n = grid[0].length;
		boolean[][] visited = new boolean[m][n];
		return hasValidPath(grid, visited, m, n, 0, 0);
	}

	public boolean hasValidPath(int[][] grid, boolean[][] visited, int m, int n, int x, int y) {
		System.out.println(x + "," + y);
		if (x == m - 1 && y == n - 1) {
			return true;
		}
		visited[x][y] = true;
		int[][] moves = { { 0, 1 }, { 0, -1 }, { -1, 0 }, { 1, 0 } };
		for (int i = 0; i < moves.length; i++) {
			int x2 = x + moves[i][0], y2 = y + moves[i][1];
			if (valid(grid, x, y, x2, y2) && !visited[x2][y2]) {
				if (hasValidPath(grid, visited, m, n, x2, y2)) {
					return true;
				}
			}
		}
		visited[x][y] = false;
		return false;
	}

	public boolean valid(int[][] grid, int x1, int y1, int x2, int y2) {
		if (x2 < 0 || y2 < 0 || x2 >= grid.length || y2 >= grid[0].length) {
			return false;
		}

		int type1 = grid[x1][y1], type2 = grid[x2][y2];
		if (x1 + 1 == x2) {
			if ((type1 == 2 || type1 == 3 || type1 == 4) && (type2 == 2 || type2 == 5 || type2 == 6)) {
				return true;
			}
		} else if (y1 + 1 == y2) {
			if ((type1 == 4 || type1 == 6 || type1 == 1) && (type2 == 1 || type2 == 3 || type2 == 5)) {
				return true;
			}
		} else if (y1 - 1 == y2) {
			if ((type1 == 3 || type1 == 5 || type1 == 1) && (type2 == 1 || type2 == 4 || type2 == 6)) {
				return true;
			}
		} else if (x1 - 1 == x2) {
			if ((type1 == 5 || type1 == 6 || type1 == 2) && (type2 == 2 || type2 == 3 || type2 == 4)) {
				return true;
			}
		}
		return false;
	}
}
