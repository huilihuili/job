package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LeetCode914 {

	public boolean hasGroupsSizeX(int[] deck) {
		if (deck == null || deck.length < 2) {
			return false;
		}
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int d : deck) {
			map.put(d, map.getOrDefault(d, 0) + 1);
		}
		int x = map.get(deck[0]);
		for (Integer value : map.values()) {
			if ((x = gcd(x, value)) < 2) {
				return false;
			}
		}
		return true;
	}

	public boolean hasGroupsSizeX2(int[] deck) {
		int[] counts = new int[10000];
		for (int card : deck) {
			counts[card]++;
		}
		int gcd = counts[deck[0]];
		for (int cnt : counts) {
			if ((gcd = gcd(gcd, cnt)) < 2) {
				return false;
			}
		}
		return true;
	}

	public boolean hasGroupsSizeX2_(int[] deck) {
		// 计数
		int[] counter = new int[10000];
		for (int num : deck) {
			counter[num]++;
		}
		// 求最大公约数
		return Arrays.stream(counter).reduce(this::gcd).getAsInt() > 1;
	}

	// 最大公约数
	public int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}

	// 最小公倍数
	public int gcdluc(int a, int b) {
		return a * b / gcd(a, b);
	}

}
