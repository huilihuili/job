package vip.eagleli.programming.shen.ce.shu.ju;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class SanShuZhiHe {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt(), k = scanner.nextInt();
		int[] nums = new int[n];
		for (int i = 0; i < n; i++) {
			nums[i] = scanner.nextInt();
		}
		List<List<Integer>> res = threeSum(nums, k);
		if (res.size() == 0) {
			System.out.println("-1 -1 -1");
		} else {
			for (List<Integer> list : res) {
				System.out.println(list.get(0) + " " + list.get(1) + " " + list.get(2));
			}
		}
	}

	public static List<List<Integer>> threeSum(int[] nums, int k) {
		List<List<Integer>> res = new LinkedList<>();
        Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] > k) {
				break;
			}
			if (i > 0 && nums[i] == nums[i - 1]) {
				continue;
			}
			int l = i + 1;
			int r = nums.length - 1;
			while (l < r) {
				int sum = nums[i] + nums[l] + nums[r];
				if (sum == k) {
					res.add(new ArrayList(Arrays.asList(nums[i], nums[l], nums[r])));
					judge((LinkedList<List<Integer>>) res);
					while (l < r && nums[r] == nums[r - 1]) {
						r--;
					}
					while (l < r && nums[l] == nums[l + 1]) {
						l++;
					}
					r--;
					l++;
				} else if (sum < k) {
					l++;
				} else {
					r--;
				}
			}
		}
		return res;
	}
	
	public static void judge(LinkedList<List<Integer>> res) {
		List<Integer> tIntegers = res.getLast();
		int a1 = tIntegers.get(0);
		int a2 = tIntegers.get(1);
		int a3 = tIntegers.get(2);
		
		if (a1 == a2 || a1 == a3 || a2 == a3) {
			res.removeLast();
		}
	}
	
}
