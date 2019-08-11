package vip.eagleli.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class MapTest {
	public static void main(String[] args) {

	}

	@Test
	public void insertKeyValueTest() {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		ConcurrentHashMap<Integer, Integer> concurrentHashMap = new ConcurrentHashMap<>();
		TreeMap<Integer, Integer> treeMap = new TreeMap<>();
		insertNullKey(hashMap);
		insertNullKey(treeMap);
		insertNullKey(concurrentHashMap);
		System.out.println();
		insertNullValue(hashMap);
		insertNullValue(treeMap);
		insertNullValue(concurrentHashMap);
	}

	private void insertNullKey(Map<Integer, Integer> map) {
		try {
			map.put(null, 1);
		} catch (Exception e) {
			System.out.println(map.getClass().getSimpleName() + " key 不能插入null值");
		}
	}

	private void insertNullValue(Map<Integer, Integer> map) {
		try {
			map.put(1, null);
		} catch (Exception e) {
			System.out.println(map.getClass().getSimpleName() + " value 不能插入null值");
		}
	}

}
