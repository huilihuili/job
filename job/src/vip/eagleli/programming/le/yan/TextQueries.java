package vip.eagleli.programming.le.yan;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class TextQueries {
	public static void textQueries(List<String> sentences, List<String> queries) {
		ArrayList<HashSet<String>> list = new ArrayList<>();
		for (String string : sentences) {
			String[] words = string.split(" ");
			HashSet<String> hashSet = new HashSet<>();
			for (int i = 0; i < words.length; i++) {
				hashSet.add(words[i]);
			}
			list.add(hashSet);
		}
		for (String string : queries) {
			String[] words = string.split(" ");
			System.out.println(solve(list, words));
		}
	}

	private static String solve(ArrayList<HashSet<String>> sentences, String[] words) {
		StringBuilder stringBuilder = null;
		for (int i = 0; i < sentences.size(); i++) {
			if (solve(sentences.get(i), words)) {
				if (stringBuilder == null) {
					stringBuilder = new StringBuilder();
					stringBuilder.append(i);
				} else {
					stringBuilder.append(" ").append(i);
				}
			}
		}
		return stringBuilder == null ? "-1" : stringBuilder.toString();
	}

	private static boolean solve(HashSet<String> sentences, String[] words) {
		for (int i = 0; i < words.length; i++) {
			if (!sentences.contains(words[i])) {
				return false;
			}
		}
		return true;
	}
}
