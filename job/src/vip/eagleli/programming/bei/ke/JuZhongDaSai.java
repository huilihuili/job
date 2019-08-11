package vip.eagleli.programming.bei.ke;

import java.util.Arrays;
import java.util.Scanner;

public class JuZhongDaSai {
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
		int res = 0;
		Arrays.sort(arr);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] >= (arr[j] * 0.9)) {
					res++;
					continue;
				}
				break;
			}
		}
		return res;
	}
}
