package vip.eagleli.programming.shen.ce.shu.ju;

import java.util.PriorityQueue;
import java.util.Scanner;

public class LiFaShiFenPeiWenTi {
	private static class Node {
		int index;
		int time;

		public Node(int index, int time) {
			super();
			this.index = index;
			this.time = time;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int k = scanner.nextInt(), n = scanner.nextInt();
		int[] arr = new int[k];
		for (int i = 0; i < k; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println(solve(k, n, arr));
	}

	private static int solve(int k, int n, int[] arr) {
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> {
			return n1.time - n2.time;
		});
		for (int i = 0; i < k; i++) {
			priorityQueue.offer(new Node(i, arr[i]));
		}
		int res = 0;
		for (int i = 0; i < n; i++) {
			Node node = priorityQueue.poll();
			res = node.time;
			node.time += arr[node.index];
			priorityQueue.offer(node);
		}
		return res;
	}
}
