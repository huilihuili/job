package vip.eagleli.programming.sogo;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class LRUCache {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		LRU<String, Long> lru = new LRU<>(n);
		while (scanner.hasNext()) {
			String key = scanner.next();
			Long value = scanner.nextLong();
			lru.put(key, value);
		}
	}

	static class LRU<K, V> extends LinkedHashMap<K, V> {
		private static final int MAX_ENTRIES = 3;

		public LRU(int captity) {
			super(MAX_ENTRIES, 1.0f, true);
		}

		protected boolean removeEldestEntry(Map.Entry eldest) {
			return size() > MAX_ENTRIES;
		}

		@Override
		public V put(K key, V value) {
			if ((Long) value > (Long) get(key)) {
				return super.put(key, value);
			}
			return null;
		}
	}
}
