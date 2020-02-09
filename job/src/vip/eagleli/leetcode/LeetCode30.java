package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeetCode30 {

	public List<Integer> findSubstring(String s, String[] words) {
		if (s == null || "".equals(s) || words == null || words.length == 0) {
			return new ArrayList<Integer>();
		}

		Map<String, Integer> wordsMap = new HashMap<String, Integer>();
		for (String str : words) {
			wordsMap.put(str, wordsMap.getOrDefault(str, 0) + 1);
		}

		List<Integer> res = new ArrayList<Integer>();

		int oneWordSize = words[0].length();
		int allWordSize = words.length * oneWordSize;

		for (int i = 0; i <= s.length() - allWordSize; i++) {

			String tmp = s.substring(i, i + allWordSize);
			HashMap<String, Integer> d = new HashMap<String, Integer>(wordsMap);

			for (int j = 0; j < tmp.length(); j += oneWordSize) {
				String key = tmp.substring(j, j + oneWordSize);
				if (!d.containsKey(key)) {
					break;
				}
				d.put(key, d.get(key) - 1);
				if (d.get(key) == 0) {
					d.remove(key);
				}
			}

			if (d.size() == 0) {
				res.add(i);
			}
		}
		return res;
	}

	public List<Integer> findSubstring_(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return res;
		}
		HashMap<String, Integer> map = new HashMap<>();
		int oneWord = words[0].length();
		int allLen = oneWord * words.length;
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		for (int i = 0; i <= s.length() - allLen; i++) {
			String tmp = s.substring(i, i + allLen);
			HashMap<String, Integer> tmpMap = new HashMap<>();
			for (int j = 0; j < allLen; j += oneWord) {
				String w = tmp.substring(j, j + oneWord);
				tmpMap.put(w, tmpMap.getOrDefault(w, 0) + 1);
			}
			if (map.equals(tmpMap)) {
				res.add(i);
			}
		}
		return res;
	}

	public List<Integer> findSubstring2(String s, String[] words) {
		if (s == null || "".equals(s) || words == null || words.length == 0) {
			return new ArrayList<Integer>();
		}

		List<Integer> rs = new ArrayList<>();
		int wLen = words[0].length();
		int wTotalLen = words[0].length() * words.length;
		int sLen = s.length();

		int[] flagArray = new int[wLen];
		for (int i = 0; i < wLen; i++) {
			int flag = 0;
			for (String word : words) {
				flag += word.charAt(i);
			}
			flagArray[i] = flag;
		}

		BitSet bs = new BitSet(words.length);
		for (int i = 0; i <= sLen - wTotalLen; i++) {
			int j = 0;
			for (; j < wLen; j++) {
				int sumFlag = 0;
				for (int k = 0; k < words.length; k++) {
					sumFlag += s.charAt(i + k * wLen + j);
				}
				if (sumFlag != flagArray[j]) {
					break;
				}
			}
			if (j == wLen) {
				// 可能匹配
				bs.clear();
				for (int k = 0; k < words.length; k++) {
					String w = s.substring(i + k * wLen, i + (k + 1) * wLen);
					int n = 0;
					for (; n < words.length; n++) {
						if (!bs.get(n) && words[n].equals(w)) {
							bs.set(n);
							break;
						}
					}
					if (n == words.length) {
						break;
					}
				}
				if (bs.cardinality() == words.length) {
					rs.add(i);
				}
			}
		}
		return rs;
	}

	public List<Integer> findSubstring3(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return res;
		}
		HashMap<String, Integer> map = new HashMap<>();
		int oneWord = words[0].length();
		int wordNum = words.length;
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		for (int i = 0; i < oneWord; i++) {
			int left = i, right = i, count = 0;
			HashMap<String, Integer> tmpMap = new HashMap<>();
			while (right + oneWord <= s.length()) {
				String w = s.substring(right, right + oneWord);
				tmpMap.put(w, tmpMap.getOrDefault(w, 0) + 1);
				right += oneWord;
				count++;

				while (tmpMap.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
					String tw = s.substring(left, left + oneWord);
					count--;
					tmpMap.put(tw, tmpMap.getOrDefault(tw, 0) - 1);
					left += oneWord;
				}
				if (count == wordNum) {
					res.add(left);
				}
			}
		}
		return res;
	}

	public List<Integer> findSubstring4(String s, String[] words) {
		List<Integer> res = new ArrayList<>();
		if (s == null || s.length() == 0 || words == null || words.length == 0) {
			return res;
		}
		HashMap<String, Integer> map = new HashMap<>();
		int oneWord = words[0].length();
		int wordNum = words.length;
		for (String word : words) {
			map.put(word, map.getOrDefault(word, 0) + 1);
		}
		for (int i = 0; i < oneWord; i++) {
			int left = i, right = i, count = 0;
			HashMap<String, Integer> tmpMap = new HashMap<>();
			while (right + oneWord <= s.length()) {
				String w = s.substring(right, right + oneWord);
				tmpMap.put(w, tmpMap.getOrDefault(w, 0) + 1);
				right += oneWord;
				count++;

				if (!map.containsKey(w)) {
					count = 0;
					left = right;
					tmpMap.clear();
					continue;
				}

				while (tmpMap.getOrDefault(w, 0) > map.getOrDefault(w, 0)) {
					String tw = s.substring(left, left + oneWord);
					count--;
					tmpMap.put(tw, tmpMap.getOrDefault(tw, 0) - 1);
					left += oneWord;
				}
				
				if (count == wordNum) {
					res.add(left);
				}
			}
		}
		return res;
	}
}
