package vip.eagleli.programming.zi.jie.tiao.dong;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class PaoBu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
		for (int i = 0; i < n - 1; i++) {
			int u = scanner.nextInt(), v = scanner.nextInt();
			if (graph.get(u) == null) {
				graph.put(u, new LinkedList<>());
			}

			if (graph.get(v) == null) {
				graph.put(v, new LinkedList<>());
			}

			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		int[] res = solve(graph, n);
		System.out.println(res[0] + " " + res[1] + " " + res[2]);
	}

	private static int[] solve(HashMap<Integer, LinkedList<Integer>> graph, int n) {
		int[][] distance = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			distance[i] = distance(graph, n, i);
		}
		int[] res = new int[3];
		for (int i = 1; i <= n; i++) {
			for (int j = i + 1; j <= n; j++) {
				res[distance[j][i] % 3] += (distance[j][i] % (1e9 + 7));
				res[distance[j][i] % 3] %= (1e9 + 7);
			}
		}
		return res;
	}

	private static int[] distance(HashMap<Integer, LinkedList<Integer>> graph, int n, int index) {
		int[] dis = new int[n + 1];
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(index);
		while (!queue.isEmpty()) {
			Integer cur = queue.poll();
			for (Integer integer : graph.get(cur)) {
				if (dis[integer] != 0 || integer == index) {
					continue;
				}
				dis[integer] = dis[cur] + 1;
				queue.offer(integer);
			}
		}
		return dis;
	}
}
