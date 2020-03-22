package vip.eagleli.leetcode;

import java.util.AbstractMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class LeetCode365 {
	public boolean canMeasureWater(int x, int y, int z) {
		if (z == 0) {
			return true;
		}
		if (x + y < z) {
			return false;
		}
		Queue<Map.Entry<Integer, Integer>> queue = new LinkedList<>();
		Set<Map.Entry<Integer, Integer>> visited = new HashSet<>();
		Map.Entry<Integer, Integer> start = new AbstractMap.SimpleEntry<>(0, 0);
		visited.add(start);
		queue.add(start);
		while (!queue.isEmpty()) {
			Map.Entry<Integer, Integer> entry = queue.poll();
			int curx = entry.getKey(), cury = entry.getValue();
			if (curx == z || cury == z || curx + cury == z) {
				return true;
			}
			addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(0, cury));
			addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curx, 0));
			addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(x, cury));
			addIntoQueue(queue, visited, new AbstractMap.SimpleEntry<>(curx, y));
			addIntoQueue(queue, visited,
					new AbstractMap.SimpleEntry<>(curx - Math.min(curx, y - cury), cury + Math.min(curx, y - cury)));
			addIntoQueue(queue, visited,
					new AbstractMap.SimpleEntry<>(curx + Math.min(cury, x - curx), cury - Math.min(cury, x - curx)));
		}
		return false;
	}

	private void addIntoQueue(Queue<Map.Entry<Integer, Integer>> queue, Set<Map.Entry<Integer, Integer>> visited,
			Map.Entry<Integer, Integer> entry) {
		if (!visited.contains(entry)) {
			visited.add(entry);
			queue.add(entry);
		}
	}

	public boolean canMeasureWater2(int x, int y, int z) {
		if (z == 0) {
			return true;
		}
		if (x + y < z) {
			return false;
		}
		return z % gcd(x, y) == 0;
	}

	private int gcd(int x, int y) {
		return y == 0 ? x : gcd(y, x % y);
	}
}
