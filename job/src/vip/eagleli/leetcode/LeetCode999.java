package vip.eagleli.leetcode;

public class LeetCode999 {
	public int numRookCaptures(char[][] board) {
		int ans = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if (board[i][j] == 'R') {
					for (int k = i; k < board.length && board[k][j] != 'B'; k++) {
						if (board[k][j] == 'p') {
							ans++;
							break;
						}
					}
					for (int k = j; k < board.length && board[i][k] != 'B'; k++) {
						if (board[i][k] == 'p') {
							ans++;
							break;
						}
					}
					for (int k = i; k >= 0 && board[k][j] != 'B'; k--) {
						if (board[k][j] == 'p') {
							ans++;
							break;
						}
					}
					for (int k = j; k >= 0 && board[i][k] != 'B'; k--) {
						if (board[i][k] == 'p') {
							ans++;
							break;
						}
					}
					return ans;
				}
			}
		}
		return ans;
	}

	public int numRookCaptures2(char[][] board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j] == 'R') {
					return capture(board, i, j);
				}
			}
		}
		return 0;
	}

	public int capture(char[][] board, int x, int y) {
		int sum = 0;
		int[] dx = { 0, 0, 1, -1 };
		int[] dy = { 1, -1, 0, 0 };
		for (int i = 0; i < 4; i++) {
			int xx = x;
			int yy = y;
			while (true) {
				xx += dx[i];
				yy += dy[i];
				if (xx < 0 || xx >= 8 || yy < 0 || yy >= 8 || board[xx][yy] == 'B') {
					break;
				}
				if (board[xx][yy] == 'p') {
					sum += 1;
					break;
				}
			}
		}
		return sum;
	}

}
