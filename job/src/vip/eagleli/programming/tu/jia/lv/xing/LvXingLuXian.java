package vip.eagleli.programming.tu.jia.lv.xing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class LvXingLuXian {
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

		@Override
		public String toString() {
			return index + " " + value;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), p = scanner.nextInt(), r = scanner.nextInt();
		int[] arr = new int[r];
		for (int i = 0; i < r; i++) {
			arr[i] = scanner.nextInt();
		}

		HashMap<Integer, ArrayList<Edge>> graph = new HashMap<>();
		for (int i = 0; i < p; i++) {
			int from = scanner.nextInt(), to = scanner.nextInt(), weight = scanner.nextInt();
			if (!graph.containsKey(from)) {
				graph.put(from, new ArrayList<>());
			}
			if (!graph.containsKey(to)) {
				graph.put(to, new ArrayList<>());
			}
			Edge edge1 = new Edge(from, to, weight);
			graph.get(from).add(edge1);

			Edge edge2 = new Edge(to, from, weight);
			graph.get(to).add(edge2);
		}
		System.out.println(solve(graph, n, arr));
	}

	private static int solve(HashMap<Integer, ArrayList<Edge>> graph, int n, int[] arr) {
		int res = 0;
		Node[] nodes = new Node[n + 1];
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
			return n1.value - n2.value;
		});
		for (int i = 1; i <= graph.size(); i++) {
			nodes[i] = new Node(i, Integer.MAX_VALUE);
		}
		nodes[arr[0]].value = 0;
		pq.offer(nodes[arr[0]]);
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
			res = Math.max(res, nodes[arr[i]].value);
		}
		return res;
	}
}
