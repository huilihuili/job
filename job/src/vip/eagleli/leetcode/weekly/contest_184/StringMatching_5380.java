package vip.eagleli.leetcode.weekly.contest_184;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StringMatching_5380 {
	public List<String> stringMatching(String[] words) {
		Set<String> set = new HashSet<>();
		for (int i = 0; i < words.length; i++) {
			for (int j = 0; j < words.length; j++) {
				if (i != j && words[i].contains(words[j])) {
					set.add(words[j]);
				}
			}
		}
		return new ArrayList<>(set);
	}
}
