package vip.eagleli.programming.xie.cheng;

import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class ManZuQuJianDeJiLu {

	static class Order {
		int id;
		int intime;
		int outtime;

		public Order(int id, int intime, int outtime) {
			super();
			this.id = id;
			this.intime = intime;
			this.outtime = outtime;
		}

		@Override
		public String toString() {
			return this.id + " " + this.intime + " " + this.outtime;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int time = scanner.nextInt();
		TreeSet<Order> treeSet = new TreeSet<>((o1, o2) -> {
			return o1.intime == o2.intime ? o1.id - o2.id : o1.intime - o2.intime;
		});
		for (int i = 0; i < n; i++) {
			int id = scanner.nextInt(), intime = scanner.nextInt(), outtime = scanner.nextInt();
			treeSet.add(new Order(id, intime, outtime));
		}
		Set<Integer> orders = solve(treeSet, time);
		if (orders == null) {
			System.out.println("null");
			return;
		}
		for (Integer integer : orders) {
			System.out.println(integer);
		}
	}

	private static Set<Integer> solve(TreeSet<Order> set, int time) {
		Set<Integer> res = new TreeSet<>();
		Set<Order> middleData = set.headSet(new Order(Integer.MAX_VALUE, time, 0), true);
		for (Order order : middleData) {
			if (order.outtime >= time) {
				res.add(order.id);
			}
		}
		return res.size() == 0 ? null : res;
	}
}
