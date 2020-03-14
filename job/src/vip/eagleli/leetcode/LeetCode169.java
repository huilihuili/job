package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

public class LeetCode169 {
	public static void main(String[] args) {
		System.out.println(7 / 2);
		System.out.println(7 / 2);
	}

	public int majorityElement(int[] nums) {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			hashMap.put(nums[i], hashMap.getOrDefault(nums[i], 0) + 1);
		}
		for (Entry<Integer, Integer> entry : hashMap.entrySet()) {
			if (entry.getValue() > nums.length / 2) {
				return entry.getKey();
			}
		}
		return -1;
	}

	public int majorityElement_(int[] nums) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int cnt = map.getOrDefault(nums[i], 0) + 1;
			if (cnt > nums.length / 2) {
				return nums[i];
			}
			map.put(nums[i], cnt);
		}
		return -1;
	}

	public int majorityElement2(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}

	public int majorityElement3(int[] nums) {
		Random rand = new Random();
		int majorityCount = nums.length / 2;

		while (true) {
			int candidate = nums[randRange(rand, 0, nums.length)];
			if (countOccurences(nums, candidate) > majorityCount) {
				return candidate;
			}
		}
	}

	private int randRange(Random rand, int min, int max) {
		return rand.nextInt(max - min) + min;
	}

	private int countOccurences(int[] nums, int num) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == num) {
				count++;
			}
		}
		return count;
	}

	public int majorityElement4(int[] nums) {
		return majorityElementRec(nums, 0, nums.length - 1);
	}

	private int majorityElementRec(int[] nums, int lo, int hi) {
		// base case; the only element in an array of size 1 is the majority
		// element.
		if (lo == hi) {
			return nums[lo];
		}

		// recurse on left and right halves of this slice.
		int mid = (hi - lo) / 2 + lo;
		int left = majorityElementRec(nums, lo, mid);
		int right = majorityElementRec(nums, mid + 1, hi);

		// if the two halves agree on the majority element, return it.
		if (left == right) {
			return left;
		}

		// otherwise, count each element and return the "winner".
		int leftCount = countInRange(nums, left, lo, hi);
		int rightCount = countInRange(nums, right, lo, hi);

		return leftCount > rightCount ? left : right;
	}

	private int countInRange(int[] nums, int num, int lo, int hi) {
		int count = 0;
		for (int i = lo; i <= hi; i++) {
			if (nums[i] == num) {
				count++;
			}
		}
		return count;
	}

	public int majorityElement5(int[] nums) {
		int count = 0;
		Integer candidate = null;
		for (int num : nums) {
			if (count == 0) {
				candidate = num;
			}
			count += (num == candidate) ? 1 : -1;
		}
		return candidate;
	}

	public int majorityElement6(int[] nums) {
		int left = 0;
		int right = nums.length - 1;
		int middle = nums.length >>> 1;
		int index = partation(left, right, nums);
		while (index != middle) {
			if (index > middle)
				right = index - 1;
			else
				left = index + 1;
			index = partation(left, right, nums);
		}
		return nums[index];
	}

	private int partation(int left, int right, int[] array) {
		int temp = array[right];
		while (left < right) {
			while (left < right && array[left] <= temp)
				left++;
			array[right] = array[left];

			while (left < right && array[right] > temp)
				right--;
			array[left] = array[right];
		}
		array[left] = temp;
		return left;
	}

	public int majorityElement7(int[] nums) {
		LinkedList<Integer> stack = new LinkedList<>();
		for (int i : nums) {
			if (stack.isEmpty() || i == stack.peek()) {
				stack.push(i);
			} else {
				stack.pop();
			}
		}
		return stack.peek();
	}

}
