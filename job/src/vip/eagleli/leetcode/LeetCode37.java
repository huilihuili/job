package vip.eagleli.leetcode;

import java.util.Arrays;

public class LeetCode37 {

	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };

		new LeetCode37().solveSudoku2(board);
		Arrays.stream(board).forEach(arr -> System.out.println(Arrays.toString(arr)));
	}

	public void solveSudoku(char[][] board) {
		int n = 3, N = n * n;

		boolean[][] rowUsed = new boolean[N][N + 1];
		boolean[][] colUsed = new boolean[N][N + 1];
		boolean[][][] boxUsed = new boolean[n][n][N + 1];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != '.') {
					int d = board[i][j] - '0';
					rowUsed[i][d] = true;
					colUsed[j][d] = true;
					boxUsed[i / 3][j / 3][d] = true;
				}
			}
		}

		recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, 0, 0);

	}

	private boolean recusiveSolveSudoku(char[][] board, boolean[][] rowUsed, boolean[][] colUsed, boolean[][][] boxUsed,
			int row, int col) {

		if (col == board.length) {
			row += 1;
			col = 0;
		}

		if (row == board.length) {
			return true;
		}

		if (board[row][col] == '.') {
			for (int i = 1; i <= 9; i++) {
				if (rowUsed[row][i] || colUsed[col][i] || boxUsed[row / 3][col / 3][i]) {
					continue;
				}
				rowUsed[row][i] = true;
				colUsed[col][i] = true;
				boxUsed[row / 3][col / 3][i] = true;
				board[row][col] = (char) ('0' + i);

				if (recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1)) {
					return true;
				}

				rowUsed[row][i] = false;
				colUsed[col][i] = false;
				boxUsed[row / 3][col / 3][i] = false;
				board[row][col] = '.';
			}
		} else {
			return recusiveSolveSudoku(board, rowUsed, colUsed, boxUsed, row, col + 1);
		}

		return false;
	}

	public void solveSudoku2(char[][] board) {

		int[] map = new int[10];

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != '.') {
					map[board[i][j] - '0'] |= ((1 << i) | (1 << (j + 9)) | (1 << (i / 3 * 3 + j / 3 + 18)));
				}
			}
		}

		recusiveSolveSudoku2(board, map, 0, 0);

	}

	private boolean recusiveSolveSudoku2(char[][] board, int[] map, int row, int col) {

		if (col == board.length) {
			row += 1;
			col = 0;
		}

		if (row == board.length) {
			return true;
		}

		if (board[row][col] == '.') {
			for (int i = 1; i <= 9; i++) {
				int index = (1 << row) | (1 << (col + 9)) | (1 << (row / 3 * 3 + col / 3 + 18));

				if ((map[i] & index) == 0) {
					map[i] |= index;
					board[row][col] = (char) ('0' + i);

					if (recusiveSolveSudoku2(board, map, row, col + 1)) {
						return true;
					}

					map[i] ^= index;
					board[row][col] = '.';
				}
			}
		} else {
			return recusiveSolveSudoku2(board, map, row, col + 1);
		}

		return false;
	}

}
