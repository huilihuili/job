package vip.eagleli.programming.qi.niu.yun;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class KuoHaoZhengQueXing {

	static HashMap<Character, Character> hashMap = new HashMap<>();
	static {
		hashMap.put('(', ')');
		hashMap.put('{', '}');
		hashMap.put('[', ']');
		hashMap.put('<', '>');
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		System.out.println(solve(string));
	}

	private static int solve(String string) {
		if (string == null || string.length() == 0) {
			return 1;
		}
		LinkedList<Character> stack = new LinkedList<>();
		for (Character character : string.toCharArray()) {
			if (hashMap.containsKey(character)) {
				stack.push(character);
				continue;
			}
			if (stack.isEmpty()) {
				return 0;
			}
			Character character2 = stack.pop();
			if (hashMap.get(character2).equals(character)) {
				continue;
			}
			return 0;
		}
		return 1;
	}
}
