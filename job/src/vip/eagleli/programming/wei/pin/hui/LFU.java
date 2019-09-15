package vip.eagleli.programming.wei.pin.hui;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class LFU {
	static class Node {
		Integer key;
		Integer value;
		Long count;
		Long time;

		public Node(Integer key, Integer value, Long count, Long time) {
			super();
			this.key = key;
			this.value = value;
			this.count = count;
			this.time = time;
		}
	}

	static class MainLFU {
		long time;
		int size;
		Map<Integer, Node> map;
		PriorityQueue<Node> pq;

		public MainLFU(int capacity) {
			if (capacity < 0) {
				throw new IllegalArgumentException();
			}

			pq = new PriorityQueue<>(capacity, (n1, n2) -> {
				return n1.count == n2.count ? (int) (n1.time - n2.time) : (int) (n1.count - n2.count);
			});
			map = new HashMap<>(capacity);
			size = capacity;
		}

		public int get(int key) {
			if (size == 0) {
				return -1;
			}

			if (map.containsKey(key)) {
				Node node = map.get(key);
				node.count++;
				node.time = time++;

				pq.remove(node);
				pq.offer(node);
				return node.value;
			}
			return -1;
		}

		public void put(int key, int value) {
			if (size == 0) {
				return;
			}

			if (map.containsKey(key)) {
				Node node = map.get(key);
				node.count++;
				node.time = time++;
				node.value = value;

				pq.remove(node);
				pq.offer(node);
			} else {
				if (map.size() == size) {
					Node temp = pq.poll();
					map.remove(temp.key);
				}
				Node node = new Node(key, value, 1L, time++);
				pq.offer(node);
				map.put(key, node);
			}
		}
	}

	public static void main(String[] args) {

	}
}
