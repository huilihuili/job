package vip.eagleli.leetcode.mian.shi.ti;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class P40 {
	public static void main(String[] args) {
		P40 p40 = new P40();
		int[] arr = { 3, 2, 1 };
		int k = 2;
		System.out.println(Arrays.toString(p40.getLeastNumbers2(arr, k)));

	}

	/**
	 * 直接使用库函数的sort方法
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int[] getLeastNumbers(int[] arr, int k) {
		Arrays.sort(arr);
		return Arrays.copyOf(arr, k);
	}

	/**
	 * 选择排序
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int[] getLeastNumbers2(int[] arr, int k) {
		int[] ans = new int[k];
		for (int i = 0; i < k && i < arr.length; i++) {
			int min = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min]) {
					min = j;
				}
			}
			swap(arr, min, i);
			ans[i] = arr[i];
		}
		return ans;
	}

	/**
	 * 快速排序
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int[] getLeastNumbers3(int[] arr, int k) {
		if (arr == null || k == 0) {
			return new int[0];
		}
		return quickSearch(arr, 0, arr.length - 1, k - 1);
	}

	private int[] quickSearch(int[] arr, int l, int h, int k) {
		int partition = partition(arr, l, h);
		if (partition == k) {
			return Arrays.copyOf(arr, k + 1);
		}
		return partition > k ? quickSearch(arr, l, partition - 1, k) : quickSearch(arr, partition + 1, h, k);
	}

	private int partition(int[] arr, int l, int h) {
		int i = l;
		for (int j = l + 1; j <= h; j++) {
			if (arr[j] <= arr[l]) {
				swap(arr, ++i, j);
			}
		}
		swap(arr, l, i);
		return i;
	}

	private void swap(int[] is, int i, int j) {
		if (i != j) {
			is[i] = is[i] + is[j];
			is[j] = is[i] - is[j];
			is[i] = is[i] - is[j];
		}
	}

	/**
	 * 利用堆
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int[] getLeastNumbers4(int[] arr, int k) {
		if (arr == null || k == 0) {
			return new int[0];
		}
		PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
		for (int i = 0; i < k; i++) {
			pq.offer(arr[i]);
		}
		for (int i = k; i < arr.length; i++) {
			if (arr[i] < pq.peek()) {
				pq.poll();
				pq.offer(arr[i]);
			}
		}
		int[] ans = new int[k];
		for (int i = 0; i < k; i++) {
			ans[i] = pq.poll();
		}
		return ans;
	}

	/**
	 * 二叉搜索树
	 * 
	 * @param arr
	 * @param k
	 * @return
	 */
	public int[] getLeastNumbers5(int[] arr, int k) {
		if (arr == null || k == 0) {
			return new int[0];
		}
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		for (int i = 0; i < k; i++) {
			treeMap.put(arr[i], treeMap.getOrDefault(arr[i], 0) + 1);
		}
		for (int i = k; i < arr.length; i++) {
			Map.Entry<Integer, Integer> lastEntry = treeMap.lastEntry();
			if (arr[i] < lastEntry.getKey()) {
				treeMap.put(arr[i], treeMap.getOrDefault(arr[i], 0) + 1);
				if (lastEntry.getValue() == 1) {
					treeMap.pollLastEntry();
				} else {
					treeMap.put(lastEntry.getKey(), lastEntry.getValue() - 1);
				}
			}
		}
		int[] ans = new int[k];
		int idx = 0;
		for (Map.Entry<Integer, Integer> entry : treeMap.entrySet()) {
			int value = entry.getValue();
			while (value-- > 0) {
				ans[idx++] = entry.getKey();
			}
		}
		return ans;
	}
}
