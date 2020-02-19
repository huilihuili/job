package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class LeetCode40 {

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> ans = new ArrayList<List<Integer>>();
		Arrays.sort(candidates);
		combinationSum(candidates, target, 0, ans, new LinkedList<Integer>(), 0);
		return ans;
	}

	public void combinationSum(int[] candidates, int target, int index, List<List<Integer>> ans,
			LinkedList<Integer> cur, int sum) {
		if (target == sum) {
			ans.add(new ArrayList<>(cur));
			return;
		}

		for (int i = index; i < candidates.length; i++) {
			if (i > index && candidates[i] == candidates[i - 1]) {
				continue;
			}
			if (sum + candidates[i] > target) {
				break;
			}
			cur.add(candidates[i]);
			combinationSum(candidates, target, i + 1, ans, cur, sum + candidates[i]);
			cur.removeLast();
		}
	}
}
