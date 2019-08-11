package vip.eagleli.programming.bei.ke;

import java.util.Scanner;

public class TeShuDeCeShi {
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
		int l = n - 1;
		for (int i = 0; i < n - 1; i++) {
			if (arr[i] > arr[i + 1]) {
				l = i;
				break;
			}
		}
		int r = 0;
		for (int i = n - 1; i > 0; i--) {
			if (arr[i] > arr[i - 1]) {
				r = i;
				break;
			}
		}
		if (l == n - 1 || r == 0) {
			return 0;
		}
		int low;
		if (arr[l] == arr[r]) {
			low = arr[l] + 1;
		} else {
			low = Math.max(arr[l], arr[r]);
		}
		int res = 0;
		int count = (r - l - 1) / 2;
		int lowt = low;
		for (int i = l + 1; i <= l + count + 1; i++) {
			res += lowt - arr[i];
			lowt++;
		}
		lowt = low;
		for (int i = r - 1; i > l + count + 1; i--) {
			res += lowt - arr[i];
			lowt++;
		}
		return res;
	}
}
