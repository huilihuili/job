package vip.eagleli.programming.jd;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class KaoShiAnPai {
	static class Node {
		int index;
		int du;

		public Node(int index) {
			super();
			this.index = index;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), m = scanner.nextInt();
		Node[] nodes = new Node[2 * n + 1];
		HashMap<Integer, LinkedList<Integer>> edges = new HashMap<>();
		for (int i = 0; i <= 2 * n; i++) {
			nodes[i] = new Node(i);
		}
		for (int i = 0; i < m; i++) {
			int x = scanner.nextInt(), y = scanner.nextInt();
			nodes[x].du++;
			nodes[y].du++;

			if (edges.get(x) == null) {
				edges.put(x, new LinkedList<>());
			}
			if (edges.get(y) == null) {
				edges.put(y, new LinkedList<>());
			}
			edges.get(x).add(y);
			edges.get(y).add(x);
		}
		List<Integer> list = solve(nodes, edges);
		System.out.println(list.size());
		for (Integer integer : list) {
			System.out.println(integer);
		}
	}

	private static List<Integer> solve(Node[] nodes, HashMap<Integer, LinkedList<Integer>> edges) {
		List<Integer> res = new LinkedList<>();
		PriorityQueue<Node> pq = new PriorityQueue<>((n1, n2) -> {
			return n1.du == n2.du ? n1.index - n2.index : n2.du - n1.du;
		});
		for (Node node : nodes) {
			if (node.du != 0) {
				pq.offer(node);
			}
		}
		while (!pq.isEmpty()) {
			Node cur = pq.poll();
			res.add(cur.index);
			for (Integer integer : edges.get(cur.index)) {
				Node delete = nodes[integer];
				pq.remove(delete);
				delete.du--;
				if (delete.du != 0) {
					pq.offer(delete);
				}
			}
		}
		return res;
	}
}
