package vip.eagleli.programming.dang.dang;

import java.util.HashMap;
import java.util.Scanner;

public class ZiFuTongJi {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(solve(string));
	}

	private static int solve(String string) {
		HashMap<Character, Integer> hashMap = new HashMap<>();
		int res = 1;
		for (char c : string.toCharArray()) {
			if (c == ' ') {
				continue;
			}
			if (!hashMap.containsKey(c)) {
				hashMap.put(c, 1);
			} else {
				hashMap.put(c, hashMap.get(c) + 1);
				res = Math.max(res, hashMap.get(c));
			}
		}
		return res;
	}
}
