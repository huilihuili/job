package vip.eagleli.programming.lang.chao;

import java.util.Arrays;
import java.util.Scanner;

public class ShiTou {
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
		int[] sortArr = Arrays.copyOf(arr, arr.length);
		Arrays.sort(sortArr);
		int res = Integer.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			res = Math.min(res, solve(n, arr, sortArr, i));
		}
		return res;
	}

	private static int solve(int n, int[] arr, int[] sortArr, int index) {
		int j = 0, count = 0;
		while (index < n && j < n) {
			if (arr[index] == sortArr[j]) {
				count++;
				index++;
				j++;
			} else {
				index++;
			}
		}
		return n - count;
	}
}
