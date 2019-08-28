package vip.eagleli.leetcode;

import java.util.LinkedList;
import java.util.List;

public class LeetCode46 {
	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		System.out.println(new LeetCode46().permute(nums));
	}

	public List<List<Integer>> permute(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		permute(nums, 0, res);
		return res;
	}

	public void permute(int[] nums, int index, List<List<Integer>> res) {
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
