package vip.eagleli.leetcode.spring2020;

import java.util.ArrayList;
import java.util.LinkedList;

public class NumWays_2 {
	public static void main(String[] args) {

	}

	private int count = 0;

	public int numWays(int n, int[][] relation, int k) {
		ArrayList<LinkedList<Integer>> arrayList = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			arrayList.add(new LinkedList<>());
		}
		for (int[] is : relation) {
			arrayList.get(is[0]).add(is[1]);
		}
		dfs(k, 0, n, 0, arrayList);
		return count;
	}

	private void dfs(int k, int index, int n, int num, ArrayList<LinkedList<Integer>> arr) {
		if (index == n - 1 && k == num) {
			count++;
		}
		if (num > k) {
			return;
		}
		for (int i : arr.get(index)) {
			dfs(k, i, n, num + 1, arr);
		}
	}
}
