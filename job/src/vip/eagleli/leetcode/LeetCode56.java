package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class LeetCode56 {
	public static void main(String[] args) {
		int[][] intervals = { { 1, 3 }, { 2, 6 }, { 8, 10 }, { 15, 18 } };
		System.out.println(Arrays.toString(merge(intervals)));
	}

	public static int[][] merge(int[][] intervals) {
		Arrays.sort(intervals, (arr1, arr2) -> {
			return arr1[0] == arr2[0] ? arr1[1] - arr2[1] : arr1[0] - arr2[0];
		});
		LinkedList<int[]> linkedList = new LinkedList<>();
		for (int[] is : intervals) {
			if (linkedList.isEmpty() || is[0] > linkedList.getLast()[1]) {
				linkedList.add(is);
			} else {
				linkedList.getLast()[1] = Math.max(linkedList.getLast()[1], is[1]);
			}
		}
		int[][] res = new int[linkedList.size()][];
		int i = 0;
		for (int[] is : linkedList) {
			res[i++] = is;
		}
		return res;
	}
}
