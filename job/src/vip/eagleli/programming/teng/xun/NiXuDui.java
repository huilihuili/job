package vip.eagleli.programming.teng.xun;

import java.util.Arrays;
import java.util.Scanner;

public class NiXuDui {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int n2 = (int) Math.pow(2, n);
		int[] arr = new int[n2];
		for (int i = 0; i < n2; i++) {
			arr[i] = scanner.nextInt();
		}
		int m = scanner.nextInt();
		for (int i = 0; i < m; i++) {
			int q = scanner.nextInt();
			reverse(arr, q);
			System.out.println(solve(arr));
		}
	}

	private static void reverse(int[] arr, int q) {
		int n = (int) Math.pow(2, q);
		for (int i = 0; i < arr.length; i += n) {
			int l = i, r = i + n - 1;
			while (l < r) {
				int t = arr[l];
				arr[l] = arr[r];
				arr[r] = t;
				l++;
				r--;
			}
		}
	}

	private static int solve(int[] arr) {
		int[] arrt = Arrays.copyOf(arr, arr.length);
		return mergeSort(arrt, 0, arrt.length - 1);
	}

	private static int mergeSort(int[] arr, int left, int right) {
		int sum = 0;
		if (left < right) {
			int mid = (left + right) / 2;
			sum += mergeSort(arr, left, mid);
			sum += mergeSort(arr, mid + 1, right);
			sum += mergeArray(arr, left, right);
		}
		return sum;
	}

	private static int mergeArray(int[] arr, int left, int right) {
		int mid = (left + right) / 2;
		int i = left;
		int j = mid + 1;
		int k = 0;
		int temp[] = new int[right - left + 1];
		int count = 0;
		while (i <= mid && j <= right) {
			if (arr[i] <= arr[j]) {
				temp[k++] = arr[i++];
			} else {
				count += mid - i + 1;
				temp[k++] = arr[j++];
			}
		}
		while (i <= mid) {
			temp[k++] = arr[i++];
		}
		while (j <= right) {
			temp[k++] = arr[j++];
		}
		for (int f = 0; f < k; f++) {
			arr[left++] = temp[f];
		}
		return count;
	}

}
