package vip.eagleli.leetcode;

public class LeetCode33 {
	/**
	 * 
	 * @param nums 按照升序排序的数组在预先未知的某个点上进行了旋转   数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2]
	 * @param target
	 * @return
	 */
	public int search(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;

			if (nums[mid] == target) {
				return mid;
			}

			if (nums[l] <= nums[mid]) {
				if (nums[l] <= target && target < nums[mid]) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			} else {
				if (target > nums[mid] && target <= nums[r]) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
		}
		return -1;
	}
}