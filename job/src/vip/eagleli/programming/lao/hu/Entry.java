package vip.eagleli.programming.lao.hu;

import java.util.HashMap;
import java.util.Map;

public class Entry {

	public static String CONST_PARAM;

	static {
		System.out.println("static global func");
	}

	public Entry() {
		CONST_PARAM = "AA";
		System.out.println("Entry, construct");
	}

	static class Key {
		Integer id;

		Key(Integer id) {
			this.id = id;
		}

		@Override
		public int hashCode() {
			return id.hashCode();
		}

	}

	public static void main(String[] args) {
		System.out.println(Entry.CONST_PARAM);
		Entry entry;
		System.out.println(Entry.CONST_PARAM);
		Map<Key, String> m = new HashMap<Key, String>();
		while (true) {
			for (int i = 0; i < 10000; i++) {
				if (!m.containsKey(new Key(i))) {
					m.put(new Key(i), "Number:" + i);
				}
			}
		}
	}
}
