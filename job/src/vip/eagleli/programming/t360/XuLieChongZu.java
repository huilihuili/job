package vip.eagleli.programming.t360;

import java.util.ArrayList;
import java.util.Scanner;

public class XuLieChongZu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int  m = scanner.nextInt(), n = scanner.nextInt();
		ArrayList<Integer> a1 = new ArrayList<>(n);
		ArrayList<Integer> a2 = new  ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			a1.add(scanner.nextInt());
		}
		for (int i = 0; i < n; i++) {
			a2.add(scanner.nextInt());
		}
		System.out.println(solve(m, n, a1, a2));
	}

	private static String solve(int m, int n, ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		StringBuilder stringBuilder = new StringBuilder();
		int pre = m - 1;
		for (int index = 0; index < n; index++) {
			int max = -1;
			int a1 = -1, a2 = -1;
			for (int i = 0; i < arr1.size(); i++) {
				for (int j = 0; j < arr2.size(); j++) {
					int t = (arr1.get(i) + arr2.get(j)) % m;
					if (t > max) {
						max = t;
						a1 = i;
						a2 = j;
					}
					if (max == pre) {
						break;
					}
				}
				if (max == pre) {
					break;
				}
			}
			pre = max;
			if (index != 0) {
				stringBuilder.append(" ");
			} 
			arr1.remove(a1);
			arr2.remove(a2);
			stringBuilder.append(max);
		}
		return stringBuilder.toString();
	}
}
