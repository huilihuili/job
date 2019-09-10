package vip.eagleli.programming.qi.an.xin;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class JieShuJinChengShu {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string1 = scanner.nextLine(), string2 = scanner.nextLine();
		String[] strings1 = string1.split(" ");
		String[] strings2 = string2.split(" ");
		int[] arr1 = new int[strings1.length];
		int[] arr2 = new int[strings2.length];
		for (int i = 0; i < strings1.length; i++) {
			arr1[i] = Integer.valueOf(strings1[i].trim());
		}
		for (int i = 0; i < strings1.length; i++) {
			arr2[i] = Integer.valueOf(strings2[i].trim());
		}
		int value = scanner.nextInt();
		System.out.println(solve(arr1, arr2, value));
	}

	private static int solve(int[] arr1, int[] arr2, int value) {
		HashMap<Integer, List<Integer>> hashMap = new HashMap<>();
		for (int i = 0; i < arr1.length; i++) {
			hashMap.put(arr1[i], new LinkedList<>());
		}
		for (int i = 0; i < arr1.length; i++) {
			if (arr2[i] == 0) {
				continue;
			}
			hashMap.get(arr2[i]).add(arr1[i]);
		}
		if (!hashMap.containsKey(value)) {
			return 0;
		}
		return solve(hashMap, value);
	}

	private static int solve(HashMap<Integer, List<Integer>> hashMap, int value) {
		if (hashMap.get(value).size() == 0) {
			return 1;
		}
		int res = 1;
		for (Integer integer : hashMap.get(value)) {
			res += solve(hashMap, integer);
		}
		return res;
	}
}
