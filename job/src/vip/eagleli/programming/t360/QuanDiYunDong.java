package vip.eagleli.programming.t360;

import java.util.Scanner;

public class QuanDiYunDong {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println(solve(n, arr));
	}

	private static int solve(int n, int[] arr) {
		int sum = 0, max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			max = Math.max(max, arr[i]);
			if (i > 1) {
				if (max < (sum - max)) {
					return i + 1;
				}
			}
		}
		return -1;
	}
}
