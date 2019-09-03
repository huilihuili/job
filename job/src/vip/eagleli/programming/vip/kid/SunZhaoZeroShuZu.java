package vip.eagleli.programming.vip.kid;

import java.util.HashMap;
import java.util.Scanner;

public class SunZhaoZeroShuZu {
	public static void main(String[] args) {
		String string = new Scanner(System.in).nextLine();
		String[] strings = string.split(",");
		int[] arr = new int[strings.length];
		for (int i = 0; i < strings.length; i++) {
			arr[i] = Integer.valueOf(strings[i].trim());
		}
		System.out.println(sovle(arr));
	}

	private static int sovle(int[] arr) {
		int res = 0;
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i : arr) {
			if (!hashMap.containsKey(i * -1)) {
				if (!hashMap.containsKey(i)) {
					hashMap.put(i, 1);
				} else {
					hashMap.put(i, hashMap.get(i) + 1);
				}
			} else {
				res++;
				if (hashMap.get(i * -1) == 1) {
					hashMap.remove(i * -1);
				} else {
					hashMap.put(i * -1, hashMap.get(i * -1) - 1);
				}
			}
		}
		return res;
	}
}
