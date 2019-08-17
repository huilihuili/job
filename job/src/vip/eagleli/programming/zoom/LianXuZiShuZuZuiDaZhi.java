package vip.eagleli.programming.zoom;

import java.util.Scanner;

public class LianXuZiShuZuZuiDaZhi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		String[] strings = string.split(",");
		double[] ds = new double[strings.length];
		for (int i = 0; i < ds.length; i++) {
			ds[i] = Double.valueOf(strings[i]);
		}
		System.out.println(solve(ds));
	}

	private static double solve(double[] arr) {
		double min = arr[0], max = arr[0], res = arr[0];
		for (int i = 1; i < arr.length; i++) {
			double cur1 = min * arr[i], cur2 = max * arr[i];
			min = Math.min(Math.min(cur1, cur2), arr[i]);
			max = Math.max(Math.max(cur1, cur2), arr[i]);
			if (max > res) {
				res = max;
			}
		}
		return res;
	}

}
