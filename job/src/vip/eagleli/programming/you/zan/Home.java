package vip.eagleli.programming.you.zan;

import java.util.Scanner;

public class Home {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String[] strings = scanner.next().split(",");
		
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.valueOf(strings[i]);
		}
		System.out.println(solve(n, arr));
	}

	private static int solve(int n, int[] arr) {
		if (n == 2) {
			return Math.abs(arr[1] - arr[0]);
		}
		int res = Integer.MAX_VALUE;
		for (int i = 1; i < arr.length - 1; i++) {
			res = Math.min(res, solve(n, arr, i));
		}
		return res;
	}

	private static int solve(int n, int[] arr, int deleteIndex) {
		int res = 0;
		for (int i = 0; i < n - 1; i++) {
			if (i == deleteIndex) {
				continue;
			}
			if (i + 1 == deleteIndex) {
				res += Math.abs(arr[i] - arr[i + 2]);
			} else {
				res += Math.abs(arr[i] - arr[i + 1]);
			}
		}
		return res;
	}
}
