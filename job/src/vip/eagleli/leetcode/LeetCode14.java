package vip.eagleli.leetcode;

public class LeetCode14 {
	public static void main(String[] args) {
	}

	/**
	 * 
	 * 从第一个字符开始扫描
	 * 
	 * 依次比对每个字符串
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}

		int index = 0;
		String prefix = strs[0];

		while (index < prefix.length()) {
			for (int j = 1; j < strs.length; j++) {
				if (index == strs[j].length()) {
					return strs[j];
				}
				if (strs[j].charAt(index) != prefix.charAt(index)) {
					return prefix.substring(0, index);
				}
			}
			index++;
		}
		return prefix;
	}

	public String longestCommonPrefix2(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}

		String prefix = strs[0];
		for (int i = 1; i < strs.length; i++) {
			while (strs[i].indexOf(prefix) != 0) {
				prefix = prefix.substring(0, prefix.length() - 1);
				if (prefix.isEmpty()) {
					return prefix;
				}
			}
		}
		return prefix;
	}

	/**
	 * 和我一样的思路
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix3(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}

		for (int i = 0; i < strs[0].length(); i++) {
			char c = strs[0].charAt(i);
			for (int j = 1; j < strs.length; j++) {
				if (i == strs[j].length() || strs[j].charAt(i) != c) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}

	/**
	 * 分治
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix4(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		return longestCommonPrefix4(strs, 0, strs.length - 1);
	}

	private String longestCommonPrefix4(String[] strs, int l, int r) {
		if (l == r) {
			return strs[l];
		}

		int mid = l + (r - l) / 2;
		String lcpLeft = longestCommonPrefix4(strs, l, mid);
		String lcpRight = longestCommonPrefix4(strs, mid + 1, r);
		return commonPrefix(lcpLeft, lcpRight);
	}

	String commonPrefix(String left, String right) {
		int min = Math.min(left.length(), right.length());
		for (int i = 0; i < min; i++) {
			if (left.charAt(i) != right.charAt(i)) {
				return left.substring(0, i);
			}
		}
		return left.substring(0, min);
	}

	/**
	 * 二分查找
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefix5(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		int minLen = Integer.MAX_VALUE;
		for (String str : strs) {
			minLen = Math.min(minLen, str.length());
		}
		int low = 1;
		int high = minLen;
		while (low <= high) {
			int middle = (low + high) / 2;
			if (isCommonPrefix(strs, middle)) {
				low = middle + 1;
			} else {
				high = middle - 1;
			}
		}
		return strs[0].substring(0, (low + high) / 2);
	}

	private boolean isCommonPrefix(String[] strs, int len) {
		String str1 = strs[0].substring(0, len);
		for (int i = 1; i < strs.length; i++) {
			if (!strs[i].startsWith(str1)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 前缀树
	 * 
	 * @param strs
	 * @return
	 */

	public String longestCommonPrefix6(String[] strs) {
		if (strs == null || strs.length == 0) {
			return "";
		}
		if (strs.length == 1) {
			return strs[0];
		}
		return longestCommonPrefix6(strs[0], strs);
	}

	public String longestCommonPrefix6(String q, String[] strs) {
		Trie trie = new Trie();
		for (int i = 1; i < strs.length; i++) {
			trie.insert(strs[i]);
		}
		return trie.searchLongestPrefix(q);
	}

	class TrieNode {
		private TrieNode[] links;
		private final int R = 26; // R links to node children
		private boolean isEnd;
		private int size; // 非空子节点的数量

		public TrieNode() {
			links = new TrieNode[R];
		}

		public void put(char ch, TrieNode node) {
			links[ch - 'a'] = node;
			size++;
		}

		public int getLinks() {
			return size;
		}

		public boolean containsKey(char ch) {
			return links[ch - 'a'] != null;
		}

		public TrieNode get(char ch) {
			return links[ch - 'a'];
		}

		public void setEnd() {
			isEnd = true;
		}

		public boolean isEnd() {
			return isEnd;
		}

	}

	public class Trie {

		private TrieNode root;

		public Trie() {
			root = new TrieNode();
		}

		private String searchLongestPrefix(String word) {
			TrieNode node = root;
			StringBuilder prefix = new StringBuilder();
			for (int i = 0; i < word.length(); i++) {
				char curLetter = word.charAt(i);
				if (node.containsKey(curLetter) && (node.getLinks() == 1) && (!node.isEnd())) {
					prefix.append(curLetter);
					node = node.get(curLetter);
				} else {
					return prefix.toString();
				}

			}
			return prefix.toString();
		}

		// Inserts a word into the trie.
		public void insert(String word) {
			TrieNode node = root;
			for (int i = 0; i < word.length(); i++) {
				char currentChar = word.charAt(i);
				if (!node.containsKey(currentChar)) {
					node.put(currentChar, new TrieNode());
				}
				node = node.get(currentChar);
			}
			node.setEnd();
		}
	}

}
