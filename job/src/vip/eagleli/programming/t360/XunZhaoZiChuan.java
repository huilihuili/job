package vip.eagleli.programming.t360;

import java.util.HashMap;
import java.util.Scanner;

public class XunZhaoZiChuan {
	public static void main(String[] args) {
		String string = new Scanner(System.in).nextLine();
		System.out.println(combine(string.toCharArray()));
	}

	public static int combine(char[] c) {
		int res = 1;
		HashMap<String, Integer> hashMap = new HashMap<>();
		if (c == null) {
			return 0;
		}
		int len = c.length;
		boolean[] used = new boolean[len];
		char cache[] = new char[len];
		int result = len;
		while (true) {
			int index = 0;
			while (used[index]) {
				used[index] = false;
				++result;
				if (++index == len) {
					return res;
				}
			}
			used[index] = true;
			cache[--result] = c[index];
			String substring = String.valueOf(cache).substring(result);
			if (!hashMap.containsKey(substring)) {
				hashMap.put(substring, 1);
			} else {
				hashMap.put(substring, hashMap.get(substring) + 1);
			}
			res = Math.max(res, hashMap.get(substring));
		}
	}
}
