package vip.eagleli.programming.pin.duo.duo;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class SXShan {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), m = scanner.nextInt();
		HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
		for (int i = 0; i < m; i++) {
			int x = scanner.nextInt(), y = scanner.nextInt();
			if (x > y) {
				int t = x;
				x = y;
				y = t;
			}
			if (graph.get(x) == null) {
				graph.put(x, new LinkedList<>());
			}
			graph.get(x).add(y);
		}
		System.out.println(solve(graph, n));
	}

	private static int solve(HashMap<Integer, LinkedList<Integer>> graph, int n) {
		int res = 0;
		boolean[] visited = new boolean[n + 1];
		for (int i = 1; i <= n; i++) {
			if (visited[i]) {
				continue;
			}
			res++;
			dfs(graph, visited, i);
		}
		return res;
	}

	private static void dfs(HashMap<Integer, LinkedList<Integer>> graph, boolean[] visited, int index) {
		if (visited[index]) {
			return;
		}
		visited[index] = true;
		if (graph.get(index) == null) {
			return;
		}
		for (Integer integer : graph.get(index)) {
			dfs(graph, visited, integer);
		}
	}
}
