package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode39 {
	public static void main(String[] args) {
		int[] candidates = { 2, 3, 6, 7 };
		System.out.println(new LeetCode39().combinationSum(candidates, 7));
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		combinationSum(candidates, target, 0, ans, new LinkedList<Integer>(), 0);
		return ans;
	}

	public void combinationSum(int[] candidates, int target, int index, List<List<Integer>> ans,
			LinkedList<Integer> cur, int sum) {
		if (sum > target) {
			return;
		}

		if (target == sum) {
			ans.add(new ArrayList<>(cur));
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			cur.add(candidates[i]);
			combinationSum(candidates, target, i, ans, cur, sum + candidates[i]);
			cur.removeLast();
		}
	}

	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		combinationSum2(candidates, target, 0, ans, new LinkedList<Integer>(), 0);
		return ans;
	}

	public void combinationSum2(int[] candidates, int target, int index, List<List<Integer>> ans,
			LinkedList<Integer> cur, int sum) {
		if (target == sum) {
			ans.add(new ArrayList<>(cur));
			return;
		}

		for (int i = index; i < candidates.length && sum + candidates[i] <= target; i++) {
			cur.add(candidates[i]);
			combinationSum2(candidates, target, i, ans, cur, sum + candidates[i]);
			cur.removeLast();
		}
	}
}
