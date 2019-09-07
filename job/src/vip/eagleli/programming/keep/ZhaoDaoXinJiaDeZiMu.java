package vip.eagleli.programming.keep;

import java.util.HashMap;
import java.util.Scanner;

public class ZhaoDaoXinJiaDeZiMu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String s1 = scanner.nextLine(), s2 = scanner.nextLine();
		System.out.println(solve(s1, s2));
	}

	private static Character solve(String s1, String s2) {
		HashMap<Character, Integer> hashMap = new HashMap<>();
		for (Character character : s1.toCharArray()) {
			Integer value = hashMap.get(character);
			if (value == null) {
				hashMap.put(character, 1);
				continue;
			}
			hashMap.put(character, value + 1);
		}
		for (Character character : s2.toCharArray()) {
			if (!hashMap.containsKey(character)) {
				return character;
			}
			Integer value = hashMap.get(character);
			if (value == 1) {
				hashMap.remove(character);
				continue;
			} 
			hashMap.put(character, value - 1);
		}
		return null;
	}
}
