package vip.eagleli.programming.di.di;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class SuanShiZhuanYi {
	static class Node {
		PriorityQueue<String> strings;
		String symbol;

		public Node(String symbol, String string) {
			this.symbol = symbol;
			strings = new PriorityQueue<>((s1, s2) -> {
				return Integer.valueOf(s1) - Integer.valueOf(s2);
			});
			strings.add(string);
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String[] strings = new String[n + n - 1];
		for (int i = 0; i < n + n - 1; i++) {
			strings[i] = scanner.next();
		}
		System.out.println(solve(strings));
	}

	private static String solve(String[] strings) {
		if (strings.length == 1) {
			return strings[0];
		}
		boolean[] visited = new boolean[strings.length];
		for (int i = 1; i < strings.length - 1; i++) {
			if (contain(strings[i])) {
				visited[i - 1] = true;
				visited[i + 1] = true;
			}
		}
		StringBuilder stringBuilder = new StringBuilder();
		LinkedList<Node> linkedList = new LinkedList<>();
		linkedList.add(new Node(strings[1], strings[0]));
		for (int i = 2; i < strings.length - 2; i += 2) {
			if (visited[i]) {
				continue;
			}
			String symbol = max(strings[i - 1], strings[i + 1]);
			if (similar(symbol, linkedList.getLast().symbol)) {
				linkedList.getLast().strings.add(strings[i]);
			} else {
				linkedList.add(new Node(symbol, strings[i]));
			}
		}
		if (!visited[strings.length - 2]) {
			String symbol = strings[strings.length - 2];
			if (similar(symbol, linkedList.getLast().symbol)) {
				linkedList.getLast().strings.add(strings[strings.length - 1]);
			} else {
				linkedList.add(new Node(symbol, strings[strings.length - 1]));
			}
		}
		int index = 0;
		for (Node node : linkedList) {
			while (!node.strings.isEmpty()) {
				if (!visited[index]) {
					strings[index] = node.strings.poll();
				}
				index += 2;
			}
		}
		for (String string : strings) {
			stringBuilder.append(string).append(" ");
		}
		return stringBuilder.toString();
	}

	private static boolean similar(String s1, String s2) {
		return s1.equals(s2);
	}

	private static boolean contain(String s1) {
		return s1.equals("-") || s1.equals("/");
	}

	private static String max(String s1, String s2) {
		int a1 = getValue(s1);
		int a2 = getValue(s2);
		return a1 > a2 ? s1 : s2;
	}

	private static int getValue(String s1) {
		return (s1.equals("+") || s1.equals("-")) ? 0 : 1;
	}
}
