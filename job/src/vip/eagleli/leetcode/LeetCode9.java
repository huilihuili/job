package vip.eagleli.leetcode;

import java.util.LinkedList;

public class LeetCode9 {
	public static void main(String[] args) {
	}

	/**
	 * 
	 * 首先取出所有的数字，然后依次判断
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		LinkedList<Integer> queue = new LinkedList<>();
		while (x != 0) {
			queue.offer(x % 10);
			x /= 10;
		}

		while (!queue.isEmpty()) {
			if (queue.size() == 1) {
				return true;
			}

			if (queue.removeFirst() != queue.removeLast()) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
 	 * 
	 * 如果反转的过程中，数字溢出，那么这个数一定不是回文数字
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome2(int x) {
		if (x < 0) {
			return false;
		}

		int revertedNumber = 0, y = x;
		while (y != 0) {
			revertedNumber = revertedNumber * 10 + y % 10;
			y /= 10;
		}
		return revertedNumber == x;
	}

	/**
	 * 官方答案
	 * 
	 * @param x
	 * @return
	 */
	public boolean isPalindrome3(int x) {
		if (x < 0 || (x % 10 == 0 && x != 0)) {
			return false;
		}

		int revertedNumber = 0;
		while (x > revertedNumber) {
			revertedNumber = 10 * revertedNumber + x % 10;
			x /= 10;
		}
		return revertedNumber == x || revertedNumber / 10 == x;
	}
}
