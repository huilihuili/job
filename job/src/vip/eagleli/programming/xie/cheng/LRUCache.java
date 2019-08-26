package vip.eagleli.programming.xie.cheng;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class LRUCache {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		LRU<String, String> lru = new LRU<>(n);
		while (scanner.hasNext()) {
			String string = scanner.nextLine();
			String[] strings = string.split(" ");
			if (strings[0].equals("p")) {
				lru.put(strings[1], strings[2]);
			} else if (strings[0].equals("g")) {
				String value = lru.get(strings[1]);
				System.out.println(value == null ? -1 : value);
			}
		}
	}

	static class LRU<K, V> {
		int captity;
		HashMap<K, V> hashMap;
		LinkedList<K> linkedList;

		public LRU(int captity) {
			this.captity = captity;
			hashMap = new HashMap<>();
			linkedList = new LinkedList<>();
		}

		public void put(K key, V value) {
			if (!hashMap.containsKey(key)) {
				linkedList.addLast(key);
			}
			hashMap.put(key, value);
			if (hashMap.size() > captity) {
				hashMap.remove(linkedList.removeFirst());
			}
		}

		public V get(K key) {
			if (hashMap.containsKey(key)) {
				lru(key);
			}
			return hashMap.get(key);
		}

		public void lru(K key) {
			linkedList.remove(key);
			linkedList.addLast(key);
		}
	}
}
