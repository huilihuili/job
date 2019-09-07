package vip.eagleli.programming.keep;

import java.util.Scanner;

public class GouMaiHuiYuanDeZuiXiaoXiaoFei {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		String[] strings = string.split(",");
		int[] days = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			days[i] = Integer.valueOf(strings[i].trim());
		}
		String string2 = scanner.nextLine();
		String[] strings2 = string2.split(",");
		int[] costs = new int[strings2.length];
		for (int i = 0; i < strings2.length; i++) {
			costs[i] = Integer.valueOf(strings2[i].trim());
		}
		System.out.println(solve(days, costs));
	}

	private static int solve(int[] days, int[] costs) {
		return solve(days, costs, 0, 0);
	}

	private static int solve(int[] days, int[] costs, int index, int deadline) {
		if (index == days.length) {
			return 0;
		}
		if (deadline < days[index]) {
			int res1 = costs[0] + solve(days, costs, index, days[index] - 1 + 1);
			int res2 = costs[1] + solve(days, costs, index, days[index] - 1 + 7);
			int res3 = costs[2] + solve(days, costs, index, days[index] - 1 + 30);
			return Math.min(res1, Math.min(res2, res3));
		} else {
			return solve(days, costs, index + 1, deadline);
		}
	}
}
