package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode820 {

	public int minimumLengthEncoding(String[] words) {
		Set<String> good = new HashSet<>(Arrays.asList(words));
		for (String word : words) {
			for (int k = 1; k < word.length(); k++) {
				good.remove(word.substring(k));
			}
		}

		int ans = 0;
		for (String word : good) {
			ans += word.length() + 1;
		}
		return ans;
	}

	class TrieNode {
		TrieNode[] children;
		int count;

		TrieNode() {
			children = new TrieNode[26];
			count = 0;
		}

		public TrieNode get(char c) {
			if (children[c - 'a'] == null) {
				children[c - 'a'] = new TrieNode();
				count++;
			}
			return children[c - 'a'];
		}
	}

	public int minimumLengthEncoding2(String[] words) {
		TrieNode trie = new TrieNode();
		Map<TrieNode, Integer> nodes = new HashMap<>();

		for (int i = 0; i < words.length; ++i) {
			String word = words[i];
			TrieNode cur = trie;
			for (int j = word.length() - 1; j >= 0; --j) {
				cur = cur.get(word.charAt(j));
			}
			nodes.put(cur, i);
		}

		int ans = 0;
		for (TrieNode node : nodes.keySet()) {
			if (node.count == 0) {
				ans += words[nodes.get(node)].length() + 1;
			}
		}
		return ans;
	}

	public int minimumLengthEncoding3(String[] words) {
		int len = 0;
		Trie trie = new Trie();
		// 先对单词列表根据单词长度由长到短排序
		Arrays.sort(words, (s1, s2) -> s2.length() - s1.length());
		// 单词插入trie，返回该单词增加的编码长度
		for (String word : words) {
			len += trie.insert(word);
		}
		return len;
	}

	// 定义tire
	class Trie {
		TrieNode2 root;

		public Trie() {
			root = new TrieNode2();
		}

		public int insert(String word) {
			TrieNode2 cur = root;
			boolean isNew = false;
			// 倒着插入单词
			for (int i = word.length() - 1; i >= 0; i--) {
				int c = word.charAt(i) - 'a';
				if (cur.children[c] == null) {
					isNew = true; // 是新单词
					cur.children[c] = new TrieNode2();
				}
				cur = cur.children[c];
			}
			// 如果是新单词的话编码长度增加新单词的长度+1，否则不变。
			return isNew ? word.length() + 1 : 0;
		}
	}

	class TrieNode2 {
		char val;
		TrieNode2[] children = new TrieNode2[26];

		public TrieNode2() {
		}
	}

	public int minimumLengthEncoding4(String[] words) {
		int N = words.length;
		// 反转每个单词
		String[] reversed_words = new String[N];
		for (int i = 0; i < N; i++) {
			String word = words[i];
			String rword = new StringBuilder(word).reverse().toString();
			reversed_words[i] = rword;
		}
		// 字典序排序
		Arrays.sort(reversed_words);

		int res = 0;
		for (int i = 0; i < N; i++) {
			if (i + 1 < N && reversed_words[i + 1].startsWith(reversed_words[i])) {
				// 当前单词是下一个单词的前缀，丢弃
			} else {
				res += reversed_words[i].length() + 1; // 单词加上一个 '#' 的长度
			}
		}
		return res;
	}

	public int minimumLengthEncoding4_(String[] words) {
		int N = words.length;

		Comparator<String> cmp = (s1, s2) -> {
			int N1 = s1.length();
			int N2 = s2.length();
			for (int i = 0; i < Math.min(N1, N2); i++) {
				char c1 = s1.charAt(N1 - 1 - i);
				char c2 = s2.charAt(N2 - 1 - i);
				int c = Character.compare(c1, c2);
				if (c != 0) {
					return c;
				}
			}
			return Integer.compare(N1, N2);
		};
		// 逆序字典序排序
		Arrays.sort(words, cmp);

		int res = 0;
		for (int i = 0; i < N; i++) {
			if (i + 1 < N && words[i + 1].endsWith(words[i])) {
				// 当前单词是下一个单词的后缀，丢弃
			} else {
				res += words[i].length() + 1; // 单词加上一个 '#' 的长度
			}
		}
		return res;
	}

}
