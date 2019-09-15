package vip.eagleli.programming.five.eight;

import java.util.Arrays;
import java.util.Scanner;

public class FenBingGan {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println(solve(arr));
	}

	private static int solve(int[] arr) {
		int sum = 0;
		int[] arr1 = new int[arr.length];
		int[] arr2 = new int[arr.length];
		Arrays.fill(arr1, 1);
		Arrays.fill(arr2, 1);
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] > arr[i - 1]) {
				arr1[i] = arr1[i - 1] + 1;
			}
		}
		for (int i = arr.length - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				arr2[i] = arr2[i + 1] + 1;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			sum += Math.max(arr1[i], arr2[i]);
		}
		return sum;
	}
}
