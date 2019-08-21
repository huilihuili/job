package vip.eagleli.leetcode;

import java.util.HashSet;

public class LeetCode36 {
	public static void main(String[] args) {
	}

	public boolean isValidSudoku(char[][] board) {
		HashSet<Integer>[] rows = new HashSet[9];
		HashSet<Integer>[] cols = new HashSet[9];
		HashSet<Integer>[] boxs = new HashSet[9];
		for (int i = 0; i < board.length; i++) {
			rows[i] = new HashSet<>();
			cols[i] = new HashSet<>();
			boxs[i] = new HashSet<>();
		}
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j] == '.') {
					continue;
				}
				int value = board[i][j] - '0';
				if (rows[i].contains(value) || cols[j].contains(value) || boxs[i / 3 * 3 + j / 3].contains(value)) {
					return false;
				}
				rows[i].add(value);
				cols[j].add(value);
				boxs[i / 3 * 3 + j / 3].add(value);
			}
		}
		return true;
	}
}
