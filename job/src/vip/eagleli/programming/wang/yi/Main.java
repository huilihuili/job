package vip.eagleli.programming.wang.yi;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		while (in.hasNextInt()) {
			int k = in.nextInt();
			while (k-- != 0) {
				int n = in.nextInt();
				ArrayList<Integer> list = new ArrayList<>();
				for (int i = 0; i < n; i++) {
					list.add(in.nextInt());
				}
				int left = 0, right = 1;
				int sum = list.get(0);
				int max = 1;
				while (right != n) {
					if (list.get(right) >= sum) {
						sum += list.get(right);
						right++;
					} else {
						while (list.get(right) < sum) {
							sum -= list.get(left);
							left++;
						}
					}
					max = Math.max(max, right - left);
				}
				System.out.println(max);
			}
		}
	}
}
