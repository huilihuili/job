package vip.eagleli.leetcode;

public class LeetCode34 {
	public int[] searchRange(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (nums[mid] == target) {
				int[] ans = { mid, mid };
				while (ans[0] > 0 && nums[ans[0]] == nums[ans[0] - 1]) {
					ans[0]--;
				}
				while (ans[1] < nums.length - 1 && nums[ans[1]] == nums[ans[1] + 1]) {
					ans[1]++;
				}
				return ans;
			} else if (nums[mid] < target) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}
		}
		return new int[] { -1, -1 };
	}

	public int[] searchRange2(int[] nums, int target) {
		int[] ans = { -1, -1 };
		ans[0] = findFirst(nums, target);
		ans[1] = ans[0] == -1 ? ans[0] : findLast(nums, target);
		return ans;
	}

	public int findFirst(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (nums[mid] < target) {
				l = mid + 1;
			} else if (nums[mid] > target) {
				r = mid - 1;
			} else {
				if (mid <= 0 || nums[mid] != nums[mid - 1]) {
					return mid;
				}
				r = mid - 1;
			}
		}
		return -1;
	}

	public int findLast(int[] nums, int target) {
		int l = 0, r = nums.length - 1;
		while (l <= r) {
			int mid = l + (r - l) / 2;
			if (nums[mid] < target) {
				l = mid + 1;
			} else if (nums[mid] > target) {
				r = mid - 1;
			} else {
				if (mid >= nums.length - 1 || nums[mid] != nums[mid + 1]) {
					return mid;
				}
				l = mid + 1;
			}
		}
		return -1;
	}

	public int[] searchRange3(int[] nums, int target) {
		int[] targetRange = { -1, -1 };

		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				targetRange[0] = i;
				break;
			}
		}

		if (targetRange[0] == -1) {
			return targetRange;
		}

		for (int j = nums.length - 1; j >= 0; j--) {
			if (nums[j] == target) {
				targetRange[1] = j;
				break;
			}
		}

		return targetRange;
	}
}
