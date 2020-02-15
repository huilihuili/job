package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LeetCode36 {

	public static void main(String[] args) {
		char[][] board = { { '5', '3', '.', '.', '7', '.', '.', '.', '.' },
				{ '6', '.', '.', '1', '9', '5', '.', '.', '.' }, { '.', '9', '8', '.', '.', '.', '.', '6', '.' },
				{ '8', '.', '.', '.', '6', '.', '.', '.', '3' }, { '4', '.', '.', '8', '.', '3', '.', '.', '1' },
				{ '7', '.', '.', '.', '2', '.', '.', '.', '6' }, { '.', '6', '.', '.', '.', '.', '2', '8', '.' },
				{ '.', '.', '.', '4', '1', '9', '.', '.', '5' }, { '.', '.', '.', '.', '8', '.', '.', '7', '9' } };

		System.out.println(new LeetCode36().isValidSudoku3(board));
	}

	public boolean isValidSudoku(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] != '.' && !isValidSudoku(board, i, j)) {
					// System.out.println("--" + i + " " + j);
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValidSudoku(char[][] board, int m, int n) {
		for (int i = 0; i < board.length; i++) {
			if (i != n && board[m][i] != '.' && board[m][i] == board[m][n]) {
				// System.out.println("1. " + m + " " + i);
				return false;
			}
		}

		for (int i = 0; i < board.length; i++) {
			if (i != m && board[i][n] != '.' && board[i][n] == board[m][n]) {
				// System.out.println("2. " + i + " " + n);
				return false;
			}
		}

		int mod = board.length / 3;
		int row = m / 3 * mod, col = n / 3 * mod;
		for (int i = row; i < row + mod; i++) {
			for (int j = col; j < col + mod; j++) {
				if (board[i][j] != '.' && i != m && j != n && board[i][j] == board[m][n]) {
					// System.out.println("3. " + i + " " + j);
					return false;
				}
			}
		}
		return true;
	}

	public boolean isValidSudoku2(char[][] board) {
		List<Set<Character>> rows = new ArrayList<>(9);
		List<Set<Character>> cols = new ArrayList<>(9);
		List<Set<Character>> boxes = new ArrayList<>(9);

		for (int i = 0; i < 9; i++) {
			rows.add(new HashSet<>());
			cols.add(new HashSet<>());
			boxes.add(new HashSet<>());
		}

		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				Character character = board[i][j];
				int boxIndex = i / 3 * 3 + j / 3;
				if (character != '.' && (rows.get(i).contains(character) || cols.get(j).contains(character)
						|| boxes.get(boxIndex).contains(character))) {
					return false;
				}

				rows.get(i).add(character);
				cols.get(j).add(character);
				boxes.get(boxIndex).add(character);
			}
		}
		return true;
	}

	public boolean isValidSudoku3(char[][] board) {
		if (board == null || board.length != 9) {
			return false;
		}

		int[] map = new int[9];
		for (int i = 0; i < 9; i++) {
			if (board[i] == null || board[i].length != 9) {
				return false;
			}
			for (int j = 0; j < 9; j++) {
				int key = board[i][j] - '1';
				if (0 <= key && key <= 8) {
					int index = (1 << i) | (1 << (j + 9)) | (1 << (i / 3 * 3 + j / 3 + 18));
					if ((map[key] & index) != 0) {
						return false;
					}
					map[key] |= index;
				}
			}
		}
		return true;
	}

}
