package vip.eagleli.programming.lang.chao;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BeiKanDiaoDeShu {
	private static class Node {
		int index;
		int length;

		public Node(int index, int length) {
			super();
			this.index = index;
			this.length = length;
		}

		@Override
		public String toString() {
			return index + " " + length;
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

	private static String solve(int n, int[] arr) {
		int[] arr1 = new int[50];
		int[] arr2 = new int[50];
		for (int i : arr) {
			if ((i & 1) == 1) {
				arr1[i / 2] = -1;
			} else {
				arr2[i / 2 - 1] = -1;
			}
		}
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>((n1, n2) -> {
			return n1.length == n2.length ? n1.index - n2.index : n2.length - n1.length;
		});
		solve(arr1, true, priorityQueue);
		solve(arr2, false, priorityQueue);
		return priorityQueue.poll().toString();
	}

	private static void solve(int[] arr, boolean odd, PriorityQueue<Node> priorityQueue) {
		int start = 2, end = 100;
		if (odd) {
			start = 1;
			end = 99;
		}

		int index = -1;
		int length = -1;
		for (int i = start; i <= end; i += 2) {
			int t = i / 2;
			if (!odd) {
				t--;
			}
			if (arr[t] == -1) {
				if (index != -1) {
					priorityQueue.add(new Node(index, length));
				}
				index = -1;
				length = -1;
				continue;
			}

			if (index == -1) {
				index = i;
				length = 1;
				continue;
			}
			length++;
		}
		if (index != -1) {
			priorityQueue.add(new Node(index, length));
		}
	}
}
