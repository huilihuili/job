package vip.eagleli.programming.jd;

import java.util.Arrays;
import java.util.Scanner;

public class HeChangDuiXing {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.println(sovle(n, arr));
	}

	private static int sovle(int n, int[] arr) {
		int[] newArr = Arrays.copyOf(arr, arr.length);
		int[] location = new int[n];
		Arrays.sort(newArr);
		boolean[] visited = new boolean[n];
		for (int i = 0; i < n; i++) {
			location[i] = findIndex(arr[i], newArr, visited);
		}
		int pre = location[0];
		int res = 1;
		for (int i = 1; i < n; i++) {
			if (pre > location[i]) {
				continue;
			}
			pre = location[i];
			res++;
		}
		return res;
	}

	private static int findIndex(int value, int[] arr, boolean[] visited) {
		int l = 0, r = arr.length - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (arr[mid] == value) {
				int index = mid > 0 ? mid - 1 : mid;
				while (index > 0 && arr[index] == value) {
					index--;
				}
				index = arr[index] == value ? index : index + 1;
				for (int i = index; i < arr.length; i++) {
					if (!visited[i] && arr[i] == value) {
						visited[i] = true;
						return i;
					}
				}
			} else if (arr[mid] < value) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return -1;
	}
}
