package vip.eagleli.leetcode;

public class LeetCode289 {

	public void gameOfLife(int[][] board) {
		int[][] ans = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int numOfLive = getNumOfLive(board, i, j);
				ans[i][j] = board[i][j] == 0 ? numOfLive == 3 ? 1 : 0 : (numOfLive == 2 || numOfLive == 3) ? 1 : 0;
			}
		}
		for (int i = 0; i < board.length; i++) {
			System.arraycopy(ans[i], 0, board[i], 0, ans[i].length);
		}
	}

	public void gameOfLife_(int[][] board) {
		int[][] ans = new int[board.length][board[0].length];
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				int numOfLive = getNumOfLive(board, i, j);
				ans[i][j] = board[i][j];
				if (board[i][j] == 0 && numOfLive == 3) {
					ans[i][j] = 1;
				}
				if (board[i][j] == 1 && (numOfLive < 2 || numOfLive > 3)) {
					ans[i][j] = 0;
				}
			}
		}
		for (int i = 0; i < board.length; i++) {
			System.arraycopy(ans[i], 0, board[i], 0, ans[i].length);
		}
	}

	int[] dx = { -1, 1, 0, 0, -1, -1, 1, 1 };
	int[] dy = { 0, 0, -1, 1, -1, 1, -1, 1 };
	int[][] board;
	int m, n;

	public void gameOfLife2(int[][] board) {
		this.board = board;
		// 特判
		if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) {
			return;
		}
		this.m = board.length;
		this.n = board[0].length;
		// 遍历
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				// 拿到当前位置周围活细胞数量
				int cnt = countAlive(i, j);
				// 1. 活细胞周围八个位置有两个或三个活细胞，下一轮继续活
				if (board[i][j] == 1 && (cnt == 2 || cnt == 3)) {
					board[i][j] = 0b11;
				}
				// 2. 死细胞周围有三个活细胞，下一轮复活了
				if (board[i][j] == 0 && cnt == 3) {
					board[i][j] = 0b10;
				}
			}
		}

		// 更新结果
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				board[i][j] >>= 1;
			}
		}
	}

	private int countAlive(int x, int y) {
		int cnt = 0;
		for (int k = 0; k < 8; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
				continue;
			}
			// 如果这个位置为 0，代表当前轮是死的，不需要算进去
			// 如果这个位置为 1，代表当前轮是活得，需要算进去
			// 如果这个位置为 2，代表当前轮是死的（状态10，下一轮是活的），不需要算进去
			// 如果这个位置为 3，代表是当前轮是活的（状态11，下一轮也是活的），需要算进去
			cnt += (board[nx][ny] & 1);
		}
		return cnt;
	}

	private int getNumOfLive(int[][] board, int i, int j) {
		int ans = 0;
		int[] dx = { 0, 1, 0, -1, -1, 1, -1, 1 };
		int[] dy = { 1, 0, -1, 0, -1, -1, 1, 1 };
		for (int k = 0; k < 8; k++) {
			int nx = i + dx[k], ny = j + dy[k];
			if (nx < 0 || ny < 0 || nx >= board.length || ny >= board[0].length || board[nx][ny] == 0) {
				continue;
			}
			ans++;
		}
		return ans;
	}
}
