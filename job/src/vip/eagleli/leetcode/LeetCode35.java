package vip.eagleli.leetcode;

public class LeetCode35 {

	public static void main(String[] args) {
		int[] nums1 = { 1, 3, 5, 6 }, nums2 = { 1, 3, 5, 6 }, nums3 = { 1, 3, 5, 6 };
		int target1 = 5, target2 = 2, target3 = 7;

		new LeetCode35().searchInsert3(nums1, target1);
		System.out.println();
		new LeetCode35().searchInsert3(nums2, target2);
		System.out.println();
		new LeetCode35().searchInsert3(nums3, target3);
		System.out.println();
		new LeetCode35().searchInsert3(nums3, 0);
	}

	public int searchInsert(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (target <= nums[i]) {
				return i;
			}
		}
		return nums.length;
	}

	public int searchInsert2(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			// System.out.println("l:" + l + " r:" + r + " mid:" + mid);
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		// System.out.println("l:" + l + " r:" + r);
		return l;
	}

	public int searchInsert3(int[] nums, int target) {
		int l = 0, r = nums.length;
		while (l < r) {
			int mid = l + (r - l) / 2;
			// System.out.println("l:" + l + " r:" + r + " mid:" + mid);
			if (nums[mid] == target) {
				return mid;
			} else if (nums[mid] < target) {
				l = mid + 1;
			} else {
				r = mid;
			}
		}
		// System.out.println("l:" + l + " r:" + r);
		return l;
	}

}
