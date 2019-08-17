package vip.eagleli.programming.zoom;

import java.util.Arrays;
import java.util.Scanner;

public class ChuZhan {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		Arrays.sort(arr);
		qpl(arr, 0);
	}

	private static void qpl(int[] arr, int index) {
		if (index == arr.length - 1) {
			for (int i = 0; i < arr.length; i++) {
				if (i != 0) {
					System.out.print(" ");
				}
				System.out.print(arr[i]);
			}
			System.out.println();
		} else {
			for (int i = index; i < arr.length; i++) {
				swap(arr, index, i);
				qpl(arr, index + 1);
				swap(arr, index, i);
			}
		}
	}

	private static void swap(int[] arr, int i, int j) {
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
