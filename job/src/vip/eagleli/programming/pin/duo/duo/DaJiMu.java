package vip.eagleli.programming.pin.duo.duo;

import java.util.Arrays;
import java.util.Scanner;

public class DaJiMu {
	static class Node implements Comparable<Node> {
		int l;
		int w;

		@Override
		public int compareTo(Node o) {
			if (l == o.l) {
				return o.w - w;
			}
			return o.l - l;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		Node[] nodes = new Node[n];
		for (int i = 0; i < n; i++) {
			nodes[i] = new Node();
			nodes[i].l = scanner.nextInt();
		}
		for (int i = 0; i < n; i++) {
			nodes[i].w = scanner.nextInt();
		}
		System.out.println(solve(nodes));
	}

	private static int solve(Node[] nodes) {
		Arrays.sort(nodes);
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < nodes.length; i++) {
			int w = nodes[i].w * 7;
			int height = i + 1;
			for (int j = i + 1; j < nodes.length && w >= nodes[j].w; j++) {
				w -= nodes[j].w;
				height++;
			}
			res = Math.min(res, height);
		}
		return res;
	}
}
