package vip.eagleli.programming.qi.niu.yun;

import java.util.Scanner;

public class ShuZiShengXu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		int target = scanner.nextInt();
		int[] res = solve(n, arr, target);
		System.out.println(res[0] + " " + res[1]);
	}

	private static int[] solve(int n, int[] arr, int target) {
		if (arr == null || n < 1) {
			return new int[] { -1, -1 };
		}
		int l = 0, r = n - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (arr[mid] == target) {
				int[] res = { mid, mid };
				for (int i = mid + 1; i < n; i++) {
					if (arr[i] != target) {
						break;
					}
					res[1] = i;
				}
				for (int i = mid - 1; i > -1; i--) {
					if (arr[i] != target) {
						break;
					}
					res[0] = i;
				}
				return res;
			}
			if (arr[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return new int[] { -1, -1 };
	}
}
