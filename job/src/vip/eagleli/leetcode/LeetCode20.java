package vip.eagleli.leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LeetCode20 {
	public static void main(String[] args) {
	}

	public boolean isValid(String s) {
		if (s == null || s.length() < 1) {
			return true;
		}

		Map<Character, Character> map = new HashMap<Character, Character>() {
			private static final long serialVersionUID = 1L;
			{
				put('(', ')');
				put('{', '}');
				put('[', ']');
			}
		};

		LinkedList<Character> stack = new LinkedList<>();
		for (int i = 0; i < s.length(); i++) {
			Character character = s.charAt(i);
			if (map.containsKey(character)) {
				stack.push(character);
			} else {
				if (stack.isEmpty() || !map.get(stack.pop()).equals(character)) {
					return false;
				}
			}
		}
		return stack.isEmpty();
	}

}
