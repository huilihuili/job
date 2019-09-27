package vip.eagleli.programming.wang.yi;

import java.util.Scanner;

public class NiXuDui {
	
	static class Node {
		int index;
		int value;

		public Node(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}
	}

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
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				if (arr[i] > arr[j]) {
					res += j - i;
				}
			}
		}
		return res;
	}

	private static int mergeSort(Node[] arr, int left, int right) {
		int sum = 0;
		if (left < right) {
			int mid = (left + right) / 2;
			sum += mergeSort(arr, left, mid);
			sum += mergeSort(arr, mid + 1, right);
			sum += mergeArray(arr, left, right);
		}
		return sum;
	}

	private static int mergeArray(Node[] arr, int left, int right) {
		int mid = (left + right) / 2;
		int i = left;
		int j = mid + 1;
		int k = 0;
		int temp[] = new int[right - left + 1];
		int count = 0;
		while (i <= mid && j <= right) {
			if (arr[i].value <= arr[j].value) {
				temp[k++] = arr[i++].value;
			} else {
				for (int m = i; m <= mid; m++) {
					count += arr[j].index - arr[m].index + 1;
				}
				temp[k++] = arr[j++].value;
			}
		}
		while (i <= mid) {
			temp[k++] = arr[i++].value;
		}
		while (j <= right) {
			temp[k++] = arr[j++].value;
		}
		for (int f = 0; f < k; f++) {
			arr[left++].value = temp[f];
		}
		return count;
	}

}
