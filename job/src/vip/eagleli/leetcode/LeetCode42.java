package vip.eagleli.leetcode;

import java.util.LinkedList;

public class LeetCode42 {
	public int trap(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int ans = 0;

		for (int i = 1, len = height.length; i < len - 1; i++) {
			int maxLeft = 0, maxRight = 0;

			for (int j = i; j < len; j++) {
				maxRight = Math.max(maxRight, height[j]);
			}

			for (int j = i; j >= 0; j--) {
				maxLeft = Math.max(maxLeft, height[j]);
			}

			ans += Math.min(maxLeft, maxRight) - height[i];
		}

		return ans;
	}

	public int trap2(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int ans = 0, n = height.length;

		int[] leftMax = new int[n], rightMax = new int[n];

		leftMax[0] = height[0];
		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(height[i], leftMax[i - 1]);
		}

		rightMax[n - 1] = height[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rightMax[i] = Math.max(height[i], rightMax[i + 1]);
		}

		for (int i = 1; i < n - 1; i++) {
			ans += Math.min(leftMax[i], rightMax[i]) - height[i];
		}

		return ans;
	}

	public int trap3(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int ans = 0, maxLeft = height[0], n = height.length;
		int[] rightMax = new int[n];
		rightMax[n - 1] = height[n - 1];
		for (int i = n - 2; i >= 0; i--) {
			rightMax[i] = Math.max(height[i], rightMax[i + 1]);
		}
		for (int i = 1; i < n - 1; i++) {
			maxLeft = Math.max(maxLeft, height[i]);
			int min = Math.min(maxLeft, rightMax[i]);
			ans += min - height[i];
		}
		return ans;
	}

	public int trap4(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int ans = 0, n = height.length, maxRight = height[n - 1];
		int[] leftMax = new int[n];
		leftMax[0] = height[0];
		for (int i = 1; i < n; i++) {
			leftMax[i] = Math.max(height[i], leftMax[i - 1]);
		}
		for (int i = height.length - 2; i > 0; i--) {
			maxRight = Math.max(maxRight, height[i]);
			int min = Math.min(maxRight, leftMax[i]);
			ans += min - height[i];
		}
		return ans;
	}

	public int trap5(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}

		int maxLeft = 0, maxRigh = 0;
		int l = 0, r = height.length - 1;
		int ans = 0;

		while (l < r) {
			if (height[l] < height[r]) {
				if (height[l] >= maxLeft) {
					maxLeft = height[l];
				} else {
					ans += maxLeft - height[l];
				}
				l++;
			} else {
				if (height[r] >= maxRigh) {
					maxRigh = height[r];
				} else {
					ans += maxRigh - height[r];
				}
				r--;
			}
		}

		return ans;
	}

	public int trap6(int[] height) {
		if (height == null || height.length == 0) {
			return 0;
		}
		int ans = 0, idx = 0;
		LinkedList<Integer> stack = new LinkedList<>();
		while (idx < height.length) {
			while (!stack.isEmpty() && height[idx] > height[stack.peek()]) {
				int top = stack.pop();
				if (stack.isEmpty()) {
					break;
				}
				ans += (idx - stack.peek() - 1) * (Math.min(height[stack.peek()], height[idx]) - height[top]);
			}
			stack.push(idx++);
		}
		return ans;
	}

}
