package vip.eagleli.programming.pin.duo.duo;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ShouDongZhiXing {
	static int n, m;

	static class Node {
		int index;
		int value;
		int du;

		public Node(int index, int value) {
			this.index = index;
			this.value = value;
		}
	}

	static ArrayList<LinkedList<Node>> graph;
	static ArrayList<Node> nodes;

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		n = scanner.nextInt();
		m = scanner.nextInt();
		graph = new ArrayList<>();
		nodes = new ArrayList<>();
		graph.add(new LinkedList<>());
		nodes.add(new Node(0, -1));
		for (int i = 1; i <= n; i++) {
			nodes.add(new Node(i, scanner.nextInt()));
			graph.add(new LinkedList<>());
		}

		for (int i = 1; i <= m; i++) {
			int a = scanner.nextInt(), b = scanner.nextInt();
			nodes.get(b).du++;
			graph.get(a).add(nodes.get(b));
		}

		List<Integer> list = solve();
		for (int i = 0; i < list.size(); i++) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}

	private static List<Integer> solve() {
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
			if (n1.value == n2.value) {
				return n1.index - n2.index;
			} 
			return n1.value - n2.value;
		});
		List<Integer> res = new ArrayList<>();
		for (int i = 1; i <= n; i++) {
			if (nodes.get(i).du == 0) {
				pq.offer(nodes.get(i));
			}
		}
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			res.add(cur.index);
			for (Node node : graph.get(cur.index)) {
				node.du--;
				if (node.du == 0) {
					pq.offer(node);
				}
			}
		}
		return res;
	}
}
