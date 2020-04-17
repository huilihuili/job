package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.TreeSet;

public class LeetCode56 {
	public static void main(String[] args) {
		int[][] intervals = { { 2, 3 }, { 5, 5 }, { 2, 2 }, { 3, 4 }, { 3, 4 } };
		LeetCode56 leetCode56 = new LeetCode56();
		leetCode56.merge_(intervals);
	}

	static class Node {
		int left;
		int right;

		public Node(int left, int right) {
			super();
			this.left = left;
			this.right = right;
		}

	}

	/**
	 * 直接使用TreeMap是错误的
	 * 这是因为TreeMap中的key进行比较，如果相等，则进行替换操作
	 * @param intervals
	 * @return
	 */
	public int[][] merge(int[][] intervals) {
		if (intervals == null || intervals.length < 2) {
			return intervals;
		}
		TreeSet<Node> treeSet = new TreeSet<>((n1, n2) -> n1.left == n2.left ? n1.right - n2.right : n1.left - n2.left);
		for (int[] interval : intervals) {
			treeSet.add(new Node(interval[0], interval[1]));
		}
		ArrayList<int[]> arrayList = new ArrayList<>(intervals.length / 2);
		Iterator<Node> iterator = treeSet.iterator();
		Node node = iterator.next();
		arrayList.add(new int[] { node.left, node.right });
		while (iterator.hasNext()) {
			node = iterator.next();
			int[] cur = arrayList.get(arrayList.size() - 1);
			if (node.left <= cur[1]) {
				cur[1] = Math.max(node.right, cur[1]);
			} else {
				arrayList.add(new int[] { node.left, node.right });
			}
		}
		return arrayList.toArray(new int[arrayList.size()][]);
	}

	public int[][] merge_(int[][] intervals) {
		if (intervals == null || intervals.length < 2) {
			return intervals;
		}
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		for (int[] interval : intervals) {
			treeMap.put(interval[0], Math.max(interval[1], treeMap.getOrDefault(interval[0], interval[1])));
		}
		ArrayList<int[]> arrayList = new ArrayList<>(intervals.length / 2);
		Iterator<Entry<Integer, Integer>> iterator = treeMap.entrySet().iterator();
		Entry<Integer, Integer> entry = iterator.next();
		arrayList.add(new int[] { entry.getKey(), entry.getValue() });
		while (iterator.hasNext()) {
			entry = iterator.next();
			int[] cur = arrayList.get(arrayList.size() - 1);
			if (entry.getKey() <= cur[1]) {
				cur[1] = Math.max(entry.getValue(), cur[1]);
			} else {
				arrayList.add(new int[] { entry.getKey(), entry.getValue() });
			}
		}
		return arrayList.toArray(new int[arrayList.size()][]);
	}

	public int[][] merge2(int[][] intervals) {
		if (intervals == null || intervals.length < 2) {
			return intervals;
		}
		Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
		ArrayList<int[]> ans = new ArrayList<>(intervals.length / 2);
		for (int i = 0; i < intervals.length; i++) {
			int[] curInterVal = ans.isEmpty() ? null : ans.get(ans.size() - 1);
			if (curInterVal == null || curInterVal[1] < intervals[i][0]) {
				ans.add(intervals[i]);
			} else {
				curInterVal[1] = Math.max(curInterVal[1], intervals[i][1]);
			}
		}
		return ans.toArray(new int[0][0]);
	}
}
