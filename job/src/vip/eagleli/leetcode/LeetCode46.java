package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LeetCode46 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		LeetCode46 leetCode46 = new LeetCode46();
		List<List<Integer>> ans = leetCode46.permute2(nums);
		ans.forEach(System.out::println);
		System.out.println();
		ans = leetCode46.permute3(nums);
		ans.forEach(System.out::println);
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> ans = new ArrayList<>();
		permute(nums, 0, ans);
		return ans;
	}

	public void permute(int[] nums, int index, List<List<Integer>> ans) {
		if (index == nums.length - 1) {
			List<Integer> arr = new ArrayList<>(nums.length);
			Arrays.stream(nums).forEach(num -> arr.add(num));
			ans.add(arr);
		} else {
			for (int i = index; i < nums.length; i++) {
				swap(nums, index, i);
				permute(nums, index + 1, ans);
				swap(nums, index, i);
			}
		}
	}

	private void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}

	public List<List<Integer>> permute2(int[] nums) {
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
			for (int i = index; i < nums.size(); i++) {
				Collections.swap(nums, index, i);
				backtrack(nums, index + 1, ans);
				Collections.swap(nums, index, i);
			}
		}
	}

	public List<List<Integer>> permute3(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		boolean[] visited = new boolean[nums.length];
		backtrack(res, nums, new ArrayList<Integer>(nums.length), visited);
		return res;
	}

	private void backtrack(List<List<Integer>> res, int[] nums, ArrayList<Integer> tmp, boolean[] visited) {
		if (tmp.size() == nums.length) {
			res.add(new ArrayList<>(tmp));
			return;
		}
		for (int i = 0; i < nums.length; i++) {
			if (visited[i]) {
				continue;
			}
			visited[i] = true;
			tmp.add(nums[i]);
			backtrack(res, nums, tmp, visited);
			visited[i] = false;
			tmp.remove(tmp.size() - 1);
		}
	}

}
