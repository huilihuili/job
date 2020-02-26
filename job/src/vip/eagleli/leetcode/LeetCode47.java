package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LeetCode47 {

	public static void main(String[] args) {
		int[] nums = { 0, 1, 0, 0, 9 };
		LeetCode47 leetCode47 = new LeetCode47();
		List<List<Integer>> ans = leetCode47.permuteUnique(nums);
		ans.forEach(System.out::println);
		System.out.println();
		ans = leetCode47.permuteUnique2(nums);
		ans.forEach(System.out::println);
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		List<List<Integer>> ans = new LinkedList<>();
		ArrayList<Integer> numsList = new ArrayList<>(nums.length);
		Arrays.stream(nums).forEach(num -> numsList.add(num));
		backtrack(numsList, 0, ans);
		return ans;
	}

	public void backtrack(ArrayList<Integer> nums, int index, List<List<Integer>> ans) {
		if (index == nums.size() - 1) {
			ans.add(new ArrayList<>(nums));
		} else {
			Set<Integer> set = new HashSet<>();
			for (int i = index; i < nums.size(); i++) {
				if (set.contains(nums.get(i))) {
					continue;
				}
				set.add(nums.get(i));
				Collections.swap(nums, index, i);
				backtrack(nums, index + 1, ans);
				Collections.swap(nums, index, i);
			}
		}
	}

	public List<List<Integer>> permuteUnique2(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		boolean[] visited = new boolean[nums.length];
		Arrays.sort(nums);
		backtrack2(res, nums, new ArrayList<Integer>(nums.length), visited);
		return res;
	}

	private void backtrack2(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, boolean[] visited) {
		if (tmp.size() == nums.length) {
			res.add(new ArrayList<>(tmp));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (visited[i] || (i > 0 && !visited[i - 1]) && nums[i] == nums[i - 1]) {
				continue;
			}
			visited[i] = true;
			tmp.add(nums[i]);
			backtrack2(res, nums, tmp, visited);
			visited[i] = false;
			tmp.remove(tmp.size() - 1);
		}
	}

	public List<List<Integer>> permuteUnique3(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		boolean[] visited = new boolean[nums.length];
		backtrack3(res, nums, new ArrayList<Integer>(nums.length), visited);
		return res;
	}

	private void backtrack3(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, boolean[] visited) {
		if (tmp.size() == nums.length) {
			res.add(new ArrayList<>(tmp));
			return;
		}

		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < nums.length; i++) {
			if (visited[i] || set.contains(nums[i])) {
				continue;
			}
			set.add(nums[i]);
			visited[i] = true;
			tmp.add(nums[i]);
			backtrack3(res, nums, tmp, visited);
			visited[i] = false;
			tmp.remove(tmp.size() - 1);
		}
	}

}
