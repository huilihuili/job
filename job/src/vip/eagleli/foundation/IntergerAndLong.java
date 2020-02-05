package vip.eagleli.foundation;

import java.util.HashMap;
import java.util.Map;

public class IntergerAndLong {
	public static void main(String[] args) {
		Map<Long, String> map = new HashMap<>();
		map.put(1L, "123");

		System.out.println(map.get(1));
		System.out.println(map.get(1L));
	}
}
