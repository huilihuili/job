package vip.eagleli.leetcode;

import java.util.HashMap;

public class LeetCode13 {
	public static void main(String[] args) {
	}

	/**
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt(String s) {
		int[] ints = { 1, 5, 10, 50, 100, 500, 1000 };
		Character[] romans = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };

		HashMap<Character, Integer> romanToInts = new HashMap<>();
		for (int i = 0; i < ints.length; i++) {
			romanToInts.put(romans[i], ints[i]);
		}

		HashMap<Character, Character> specialRomans = new HashMap<>();
		specialRomans.put('V', 'I');
		specialRomans.put('X', 'I');
		specialRomans.put('L', 'X');
		specialRomans.put('C', 'X');
		specialRomans.put('D', 'C');
		specialRomans.put('M', 'C');

		int ans = 0;
		for (int i = 0; i < s.length(); i++) {
			ans += romanToInts.get(s.charAt(i));
			if (specialRomans.containsKey(s.charAt(i)) && i > 0 && specialRomans.get(s.charAt(i)) == s.charAt(i - 1)) {
				ans -= 2 * romanToInts.get(s.charAt(i - 1));
			}
		}
		return ans;
	}

	/**
	 * 另一种思路
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt2(String s) {
		int[] ints = { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
		String[] romans = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };
		HashMap<String, Integer> romanToInts = new HashMap<>();
		for (int i = 0; i < ints.length; i++) {
			romanToInts.put(romans[i], ints[i]);
		}

		int ans = 0, i = 0;
		while (i < s.length()) {
			if (i + 1 < s.length() && romanToInts.containsKey(s.substring(i, i + 2))) {
				ans += romanToInts.get(s.substring(i, i + 2));
				i += 2;
			} else {
				ans += romanToInts.get(s.substring(i, i + 1));
				i++;
			}
		}
		return ans;
	}

	/**
	 * 另一种思路
	 * 
	 * @param s
	 * @return
	 */
	public int romanToInt3(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}

		int res = 0, last = 0, curr = 0;
		for (int i = s.length() - 1; i >= 0; i--) {
			curr = getValue(s.charAt(i));
			if (curr < last) {
				res -= curr;
			} else {
				res += curr;
			}
			last = curr;
		}
		return res;
	}

	private int getValue(char ch) {
		switch (ch) {
		case 'I':
			return 1;
		case 'V':
			return 5;
		case 'X':
			return 10;
		case 'L':
			return 50;
		case 'C':
			return 100;
		case 'D':
			return 500;
		case 'M':
			return 1000;
		default:
			return 0;
		}
	}
}
