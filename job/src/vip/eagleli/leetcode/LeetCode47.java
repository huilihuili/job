package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class LeetCode47 {
	public static void main(String[] args) {
		int[] nums = { 0, 1, 0, 0, 9 };
		System.out.println(new LeetCode47().permuteUnique(nums));
	}

	public List<List<Integer>> permuteUnique(int[] nums) {
		Set<List<Integer>> res = new HashSet<>();
		permute(nums, 0, res);
		return new ArrayList<List<Integer>>(res);
	}

	public void permute(int[] nums, int index, Set<List<Integer>> res) {
		if (index == nums.length - 1) {
			List<Integer> list = new LinkedList<>();
			for (int i : nums) {
				list.add(i);
			}
			res.add(list);
			return;
		}
		for (int i = index; i < nums.length; i++) {
			swap(nums, index, i);
			permute(nums, index + 1, res);
			swap(nums, index, i);
		}
	}

	public void swap(int[] nums, int i, int j) {
		int t = nums[i];
		nums[i] = nums[j];
		nums[j] = t;
	}
}
