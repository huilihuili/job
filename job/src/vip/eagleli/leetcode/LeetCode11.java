package vip.eagleli.leetcode;

import java.util.Arrays;

/**
 * 
 * @author Administrator
 * 
 * @see LeetCode42
 */
public class LeetCode11 {
	public static void main(String[] args) {
		LeetCode11 leetCode11 = new LeetCode11();
		int[] height = { 1, 8, 6, 10, 5, 4, 9, 3, 7 };
		leetCode11.maxArea(height);
		System.out.println("-------");
		leetCode11.maxArea2(height);
	}

	/**
	 * ������
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int ans = 0;
		for (int i = 0; i < height.length; i++) {
			for (int j = 0; j < height.length; j++) {
				ans = Math.max(ans, Math.min(height[i], height[j]) * (j - i));
				System.out.print(Math.min(height[i], height[j]) * Math.abs(j - i) + "\t");
			}
			System.out.println();
		}
		return ans;
	}

	public int maxArea2(int[] height) {
		int ans = 0, i = 0, j = height.length - 1;
		char[][] cs = new char[height.length][height.length];
		char step = '0';
		while (i < j) {
			ans = Math.max(ans, (j - i) * Math.min(height[i], height[j]));
			cs[i][j] = step;
			if (height[i] < height[j]) {
				for (int k = i + 1; k < j; k++) {
					cs[i][k] = step;
				}

				i++;
			} else {
				for (int k = i + 1; k < j; k++) {
					cs[k][j] = step;
				}
				j--;
			}
			step++;
		}
		for (int k = 0; k < height.length; k++) {
			System.out.println(Arrays.toString(cs[k]));
		}
		return ans;
	}

	public int maxArea2_(int[] height) {
		int l = 0, r = height.length - 1;
		int ans = 0;
		while (l < r) {
			int area = Math.min(height[l], height[r]) * (r - l);
			ans = Math.max(ans, area);
			if (height[l] <= height[r]) {
				++l;
			} else {
				--r;
			}
		}
		return ans;
	}

}
