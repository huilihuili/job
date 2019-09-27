package vip.eagleli.programming.ma.feng.wo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Scanner;

public class QianDaoChengShi {
	static class Node {
		String city;
		Integer size;

		public Node(String city, Integer size) {
			super();
			this.city = city;
			this.size = size;
		}

		@Override
		public String toString() {
			return this.city + " " + this.size;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		String[] strings = string.split(" ");
		HashMap<String, HashSet<String>> citys = new HashMap<>();
		for (String string2 : strings) {
			if (string2.trim().equals("")) {
				continue;
			}
			String[] uStrings = string2.split("-");
			String user = uStrings[0];
			String city = uStrings[1];
			if (!citys.containsKey(city)) {
				citys.put(city, new HashSet<>());
			}
			citys.get(city).add(user);
		}
		for (Node node : solve(citys)) {
			System.out.println(node);
		}
	}

	private static List<Node> solve(HashMap<String, HashSet<String>> citys) {
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> {
			return n1.size == n2.size ? n1.city.compareTo(n2.city) : n2.size - n1.size;
		});
		for (Entry<String, HashSet<String>> entry : citys.entrySet()) {
			priorityQueue.offer(new Node(entry.getKey(), entry.getValue().size()));
		}
		int n = 3;
		List<Node> res = new ArrayList<>(n);
		for (int i = 0; i < n && i < citys.size(); i++) {
			res.add(priorityQueue.poll());
		}
		return res;
	}
}
