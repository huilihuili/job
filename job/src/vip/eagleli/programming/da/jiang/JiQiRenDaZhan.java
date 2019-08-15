package vip.eagleli.programming.da.jiang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class JiQiRenDaZhan {
	static class Edge {
		int from;
		int to;
		int weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}
	}

	static class Node {
		int index;
		int value;

		public Node(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			int n = scanner.nextInt(), p = scanner.nextInt(), c = scanner.nextInt();
			HashMap<Integer, ArrayList<Edge>> graph = new HashMap<>();
			for (int i = 0; i < n; i++) {
				graph.put(i, new ArrayList<>());
			}
			for (int i = 0; i < p; i++) {
				int from = scanner.nextInt(), to = scanner.nextInt(), weight = scanner.nextInt();
				Edge edge = new Edge(from, to, weight);
				graph.get(from).add(edge);
			}
			int[] arr = new int[c];
			for (int i = 0; i < c; i++) {
				arr[i] = scanner.nextInt();
			}
			System.out.println(solve(graph, arr));
		}
	}

	private static int solve(HashMap<Integer, ArrayList<Edge>> graph, int[] arr) {
		int res = 0;
		Node[] nodes = new Node[graph.size()];
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
			return n1.value - n2.value;
		});
		for (int i = 0; i < graph.size(); i++) {
			nodes[i] = new Node(i, Integer.MAX_VALUE);
		}
		nodes[0].value = 0;
		pq.offer(nodes[0]);
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			for (Edge edge : graph.get(cur.index)) {
				if (nodes[edge.to].value == Integer.MAX_VALUE) {
					nodes[edge.to].value = cur.value + edge.weight;
					pq.offer(nodes[edge.to]);
				} else {
					int value = cur.value + edge.weight;
					if (value < nodes[edge.to].value) {
						pq.remove(nodes[edge.to]);
						nodes[edge.to].value = value;
						pq.offer(nodes[edge.to]);
					}
				}
			}
		}
		for (int i = 0; i < arr.length; i++) {
			res += nodes[arr[i]].value;
		}
		return res;
	}
}
