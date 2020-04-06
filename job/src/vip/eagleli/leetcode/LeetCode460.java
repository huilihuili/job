package vip.eagleli.leetcode;

import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.PriorityQueue;

public class LeetCode460 {
	public static void main(String[] args) {
		long time1 = Calendar.getInstance().getTimeInMillis();
		long time2 = Calendar.getInstance().getTimeInMillis();
		System.out.println(time1 - time2);
	}

	static class LFUCache {
		static class Node {
			static long count = 0;

			int key;
			int value;
			int frenquence;
			long timestamp;

			public Node(int key, int value, int frenquence, long timestamp) {
				super();
				this.key = key;
				this.value = value;
				this.frenquence = frenquence;
				this.timestamp = timestamp;
			}

		}

		Map<Integer, Node> map;
		PriorityQueue<Node> queue;
		int capacity;

		public LFUCache(int capacity) {
			if (capacity == 0) {
				return;
			}
			map = new HashMap<>(capacity, 1.0f);
			queue = new PriorityQueue<>(capacity, (n1, n2) -> {
				return n1.frenquence == n2.frenquence ? Long.compare(n1.timestamp, n2.timestamp)
						: Integer.compare(n1.frenquence, n2.frenquence);
			});
			this.capacity = capacity;
		}

		public int get(int key) {
			if (capacity == 0) {
				return -1;
			}
			Node node = map.get(key);
			if (node == null) {
				return -1;
			}
			queue.remove(node);
			node.frenquence++;
			node.timestamp = Node.count++;
			queue.offer(node);
			return node.value;
		}

		public void put(int key, int value) {
			if (capacity == 0) {
				return;
			}
			Node node = map.get(key);
			if (node != null) {
				queue.remove(node);
				node.frenquence++;
				node.value = value;
				node.timestamp = Node.count++;
			} else {
				if (map.size() == capacity) {
					map.remove(queue.poll().key);
				}
				node = new Node(key, value, 0, Node.count++);
				map.put(key, node);
			}
			queue.offer(node);
		}
	}

	static class LFUCache2 {

		static class Node {

			int key;
			int value;
			int frenquence;

			public Node(int key, int value, int frenquence) {
				super();
				this.key = key;
				this.value = value;
				this.frenquence = frenquence;
			}

		}

		Map<Integer, Node> map;
		Map<Integer, LinkedHashSet<Node>> freqMap;
		int capacity;
		int minFrenq;

		public LFUCache2(int capacity) {
			if (capacity == 0) {
				return;
			}
			map = new HashMap<>(capacity, 1.0f);
			freqMap = new HashMap<>();
			this.capacity = capacity;
		}

		public int get(int key) {
			if (capacity == 0) {
				return -1;
			}
			Node node = map.get(key);
			if (node == null) {
				return -1;
			}
			freqInc(node);
			return node.value;
		}

		public void put(int key, int value) {
			if (capacity == 0) {
				return;
			}
			Node node = map.get(key);
			if (node != null) {
				node.value = value;
				freqInc(node);
			} else {
				if (map.size() == capacity) {
					map.remove(removeNode().key);
				}
				node = new Node(key, value, 0);
				map.put(key, node);
				addNode(node);
			}
		}

		void freqInc(Node node) {
			int freq = node.frenquence;
			LinkedHashSet<Node> set = freqMap.get(freq);
			set.remove(node);
			if (freq == minFrenq && set.size() == 0) {
				minFrenq++;
			}

			// 加入新freq对应的链表
			node.frenquence++;
			LinkedHashSet<Node> newSet = freqMap.get(node.frenquence);
			if (newSet == null) {
				newSet = new LinkedHashSet<>();
				freqMap.put(node.frenquence, newSet);
			}
			newSet.add(node);
		}

		void addNode(Node node) {
			LinkedHashSet<Node> set = freqMap.get(node.frenquence);
			if (set == null) {
				set = new LinkedHashSet<>();
				freqMap.put(node.frenquence, set);
			}
			set.add(node);
			minFrenq = node.frenquence;
		}

		Node removeNode() {
			LinkedHashSet<Node> set = freqMap.get(minFrenq);
			Node deadNode = set.iterator().next();
			set.remove(deadNode);
			return deadNode;
		}

	}
}
