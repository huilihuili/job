package vip.eagleli.programming.pin.duo.duo;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class ZiFuHuan {
	public static void main(String[] args) {
		String string = new Scanner(System.in).nextLine();
		String[] strs = string.split(" ");
		System.out.println(solve(strs));
	}

	private static boolean solve(String[] strs) {
		Set<Character> set = new HashSet<>();
		for (int i = 0; i < strs.length; i++) {
			if (set.contains(strs[i].charAt(0))) {
				set.remove(strs[i].charAt(0));
			} else {
				set.add(strs[i].charAt(0));
			}

			if (set.contains(strs[i].charAt(strs[i].length() - 1))) {
				set.remove(strs[i].charAt(strs[i].length() - 1));
			} else {
				set.add(strs[i].charAt(strs[i].length() - 1));
			}
		}
		return set.isEmpty();
	}
}
