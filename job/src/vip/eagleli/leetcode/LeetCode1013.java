package vip.eagleli.leetcode;

public class LeetCode1013 {
	public static void main(String[] args) {
		int[] is = { 10, -10, 10, -10, 10, -10, 10, -10 };
		LeetCode1013 leetCode1013 = new LeetCode1013();
		System.out.println(leetCode1013.canThreePartsEqualSum(is));
	}

	public boolean canThreePartsEqualSum(int[] is) {
		int sum = 0;
		for (int i = 0; i < is.length; i++) {
			sum += is[i];
		}
		if (sum % 3 != 0) {
			return false;
		}
		int target = sum / 3, time = 0;
		sum = 0;
		for (int i = 0; i < is.length; i++) {
			sum += is[i];
			if (sum == target) {
				sum = 0;
				time++;
			}
		}
		return time >= 3;
	}

	public boolean canThreePartsEqualSum2(int[] is) {
		int sum = 0;
		for (int i = 0; i < is.length; i++) {
			sum += is[i];
		}
		if (sum % 3 != 0) {
			return false;
		}
		int target = sum / 3;
		int left = 0, leftSum = is[left], right = is.length - 1, rightSum = is[right];
		while (left + 1 < right) {
			if (leftSum == target && rightSum == target) {
				return true;
			}
			if (leftSum != target) {
				leftSum += is[++left];
			}
			if (rightSum != target) {
				rightSum += is[--right];
			}
		}
		return false;
	}

	public boolean canThreePartsEqualSum_(int[] is) {
		int sum = 0;
		for (int i = 0; i < is.length; i++) {
			sum += is[i];
		}
		if (sum % 3 != 0) {
			return false;
		}
		int target = sum / 3;
		int left = 0, leftSum = 0;
		while ((leftSum += is[left]) != target && left + 1 < is.length) {
			left++;
		}
		int right = is.length - 1, rightSum = 0;
		while ((rightSum += is[right]) != target && right - 1 >= 0) {
			right--;
		}
		return left + 1 < right;
	}

	public boolean canThreePartsEqualSum3(int[] is) {
		int sum = 0;
		for (int i = 0; i < is.length; i++) {
			sum += is[i];
		}
		if (sum % 3 != 0) {
			return false;
		}
		int target = sum / 3;
		int i = 0, n = is.length, cur = 0;
		while (i < n) {
			cur += is[i];
			if (cur == target) {
				break;
			}
			i++;
		}
		if (i == n) {
			return false;
		}
		i++;
		cur = 0;
		while (i < n - 1) {
			cur += is[i];
			if (cur == target) {
				return true;
			}
			i++;
		}
		return false;
	}
}
