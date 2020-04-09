package vip.eagleli.leetcode.mian.shi.ti;

public class P13 {
	public static void main(String[] args) {
		P13 p13 = new P13();
		System.out.println(p13.movingCount(2, 3, 0));
	}

	public int movingCount(int m, int n, int k) {
		return dfs(0, 0, m, n, k, new boolean[m][n]);
	}

	private int dfs(int i, int j, int m, int n, int k, boolean[][] visited) {
		if (i < 0 || j < 0 || i >= m || j >= n || !judge(i, j, k) || visited[i][j]) {
			return 0;
		}
		visited[i][j] = true;
		int ans = 1;
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };
		for (int idx = 0; idx < 4; idx++) {
			ans += dfs(i + dx[idx], j + dy[idx], m, n, k, visited);
		}
		return ans;
	}

	public int movingCount_(int m, int n, int k) {
		return dfs_(0, 0, m, n, k, new boolean[m][n]);
	}

	private int dfs_(int i, int j, int m, int n, int k, boolean[][] visited) {
		if (i < 0 || j < 0 || i >= m || j >= n || !judge(i, j, k) || visited[i][j]) {
			return 0;
		}
		visited[i][j] = true;
		int ans = 1;
		int[] dx = { 0, 1 };
		int[] dy = { 1, 0 };
		for (int idx = 0; idx < 2; idx++) {
			ans += dfs(i + dx[idx], j + dy[idx], m, n, k, visited);
		}
		return ans;
	}

	private boolean judge(int m, int n, int k) {
		int sum = 0;
		while (m > 0) {
			sum += m % 10;
			m /= 10;
		}
		while (n > 0) {
			sum += n % 10;
			n /= 10;
		}
		return sum <= k;
	}

	public int movingCount2(int m, int n, int k) {
		if (k == 0) {
			return 1;
		}
		boolean[][] vis = new boolean[m][n];
		int ans = 1;
		vis[0][0] = true;
		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				if ((i == 0 && j == 0) || get(i) + get(j) > k) {
					continue;
				}
				// 边界判断
				if (i - 1 >= 0) {
					vis[i][j] |= vis[i - 1][j];
				}
				if (j - 1 >= 0) {
					vis[i][j] |= vis[i][j - 1];
				}
				ans += vis[i][j] ? 1 : 0;
			}
		}
		return ans;
	}

	private int get(int x) {
		int res = 0;
		for (; x > 0; x /= 10) {
			res += x % 10;
		}
		return res;
	}

}
