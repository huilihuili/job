package vip.eagleli.niu.ke;

import java.util.Arrays;
import java.util.Scanner;

public class QingZhu61 {
	static int res = Integer.MAX_VALUE;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = sc.nextInt();
		}
		System.out.println(solve(n, arr));
	}

	private static int solve(int n, int[] arr) {
		solve(n, arr, 0);
		return res;
	}

	private static void solve(int n, int[] arr, int k) {
		if (k == arr.length - 1) {
			res = Math.min(res, computeHigh(arr));
		} else {
			for (int i = k; i < arr.length; i++) {
				swap(k, i, arr);
				solve(n, arr, k + 1);
				swap(k, i, arr);
			}
		}
	}

	private static void swap(int i, int j, int[] arr) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

	private static int computeHigh(int[] arr) {
		int res = Math.abs(arr[0] - arr[arr.length - 1]);
		for (int i = 0; i < arr.length - 1; i++) {
			res = Math.max(Math.abs(arr[i] - arr[i + 1]), res);
		}
		return res;
	}
}
