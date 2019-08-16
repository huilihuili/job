package vip.eagleli.leetcode;

import java.util.LinkedList;

public class LeetCode42 {
	public static void main(String[] args) {
		int[] height = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
		System.out.println(trap(height));
	}

	private static int trap(int[] height) {
		int res = 0, current = 0;
		LinkedList<Integer> stack = new LinkedList<>();
		while (current < height.length) {
			while (!stack.isEmpty() && height[current] > height[stack.peek()]) {
				int top = stack.pop();
				if (stack.isEmpty()) {
					break;
				}
				System.out.println("current : " + current + " top " + top + " peek " + stack.peek());
				int distance = current - stack.peek() - 1;
				int bounded_height = Math.min(height[current], height[stack.peek()]) - height[top];
				res += distance * bounded_height;
			}
			stack.push(current++);
			System.out.println(stack);
		}
		return res;
	}
}
