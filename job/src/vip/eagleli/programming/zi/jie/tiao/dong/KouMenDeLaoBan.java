package vip.eagleli.programming.zi.jie.tiao.dong;

import java.util.Arrays;
import java.util.Scanner;

public class KouMenDeLaoBan {
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
		int[] arr1 = new int[n];
		int[] arr2 = new int[n];
		Arrays.fill(arr1, 100);
		Arrays.fill(arr2, 100);
		for (int i = 1; i < n; i++) {
			if (arr[i] > arr[i - 1]) {
				arr1[i] = arr1[i - 1] + 100;
			}
		}

		for (int i = n - 2; i >= 0; i--) {
			if (arr[i] > arr[i + 1]) {
				arr2[i] = arr2[i + 1] + 100;
			}
		}

		int res = 0;
		for (int i = 0; i < n; i++) {
			res += Math.max(arr1[i], arr2[i]);
		}
		return res;
	}
}
