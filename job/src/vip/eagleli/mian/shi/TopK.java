package vip.eagleli.mian.shi;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class TopK {
	public List<Integer> topK(int[] nums, int k) {
		PriorityQueue<Integer> res = new PriorityQueue<>(k);
		for (int i = 0; i < k; i++) {
			res.offer(nums[i]);
		}
		for (int i = k; i < nums.length; i++) {
			if (res.peek() < nums[k]) {
				res.poll();
				res.offer(nums[i]);
			}
		}
		return new ArrayList<>(res);
	}
}
