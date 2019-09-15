package vip.eagleli.programming.five.eight;

import java.util.HashSet;
import java.util.Scanner;

public class YouXuShuZuQuChong {
	public static void main(String[] args) {
		String string = new Scanner(System.in).nextLine();
		String[] strings = string.split(",");
		int[] is = new int[strings.length];
		for (int i = 0; i < is.length; i++) {
			is[i] = Integer.valueOf(strings[i]);
		}
		System.out.println(solve(is));
	}

	private static int solve(int[] arr) {
		HashSet<Integer> hashSet = new HashSet<>();
		for (int i : arr) {
			hashSet.add(i);
		}
		return hashSet.size();
	}
}
