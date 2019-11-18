package vip.eagleli.programming.qi.niu.yun;

import java.util.Arrays;
import java.util.Scanner;

public class PiPeiMuBiaoHe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		long[] arr = new long[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextLong();
		}
		long target = scanner.nextLong();
		int[] res = sovle2(n, arr, target);
		System.out.println(res[0] + " " + res[1]);
	}

	private static int[] sovle2(int n, long[] arr, long target) {
		if (arr == null || n < 1 || target < 0) {
			return new int[] { -1, -1 };
		}

		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] + arr[j] == target) {
					return new int[] { i, j };
				}
			}
		}
		return new int[] { -1, -1 };
	}

	private static int[] sovle(int n, long[] arr, long target) {
		if (arr == null || n < 1 || target < 0) {
			return new int[] { -1, -1 };
		}

		Arrays.sort(arr);
		int l = 0, r = n - 1;
		while (l < r) {
			long t = arr[l] + arr[r];
			if (t == target) {
				return new int[] { l, r };
			}

			if (t < target) {
				l++;
			} else {
				r--;
			}
		}
		return new int[] { -1, -1 };
	}
}
