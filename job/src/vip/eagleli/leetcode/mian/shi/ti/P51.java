package vip.eagleli.leetcode.mian.shi.ti;

import java.util.Arrays;

public class P51 {
	public static void main(String[] args) {
		P51 p51 = new P51();
		int[] nums = { 1, 3, 2, 3, 1 };
		System.out.println(p51.reversePairs(nums));
		System.out.println(Arrays.toString(nums));
	}

	public int reversePairs(int[] nums) {
		return mergeSort(nums, 0, nums.length - 1);
	}

	private int mergeSort(int[] nums, int start, int end) {
		if (start >= end) {
			return 0;
		}
		int mid = start + (end - start) / 2;
		int ans = 0;
		ans += mergeSort(nums, start, mid);
		ans += mergeSort(nums, mid + 1, end);
		ans += merge(nums, start, mid, end);
		return ans;
	}

	private int merge(int[] nums, int start, int mid, int end) {
		int[] arr = new int[end - start + 1];
		int index = 0, i = start, j = mid + 1;
		int ans = 0;
		while (i <= mid && j <= end) {
			if (nums[i] <= nums[j]) {
				arr[index++] = nums[i++];
			} else {
				arr[index++] = nums[j++];
				ans += (mid - i + 1);
			}
		}
		while (i <= mid) {
			arr[index++] = nums[i++];
		}
		while (j <= end) {
			arr[index++] = nums[j++];
		}
		for (i = 0; i < arr.length; i++) {
			nums[start + i] = arr[i];
		}
		return ans;
	}

}
