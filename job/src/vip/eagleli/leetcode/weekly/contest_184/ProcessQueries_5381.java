package vip.eagleli.leetcode.weekly.contest_184;

import java.util.LinkedList;

public class ProcessQueries_5381 {
	public int[] processQueries(int[] queries, int m) {
		LinkedList<Integer> linkedList = new LinkedList<Integer>();
		for (int i = 1; i <= m; i++) {
			linkedList.add(i);
		}
		int[] ans = new int[queries.length];
		for (int i = 0; i < queries.length; i++) {
			ans[i] = linkedList.indexOf(Integer.valueOf(queries[i]));
			linkedList.remove(ans[i]);
			linkedList.addFirst(queries[i]);
		}
		return ans;
	}
}
