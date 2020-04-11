package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LeetCode151 {
	public static void main(String[] args) {
		LeetCode151 leetCode151 = new LeetCode151();
		System.out.println(leetCode151.reverseWords2("a good   example"));
	}

	public String reverseWords(String s) {
		s = s.trim();
		StringBuilder stringBuilder = new StringBuilder();
		String[] strs = s.split(" ");
		for (int i = strs.length - 1; i >= 0; i--) {
			stringBuilder.append(strs[i]);
			if (i > 0 && !"".equals(strs[i])) {
				stringBuilder.append(" ");
			}
		}
		return stringBuilder.toString();
	}

	public String reverseWords_(String s) {
		// 除去开头和末尾的空白字符
		s = s.trim();
		// 正则匹配连续的空白字符作为分隔符分割
		List<String> wordList = Arrays.asList(s.split("\\s+"));
		Collections.reverse(wordList);
		return String.join(" ", wordList);
	}

	public String reverseWords2(String s) {
		char[] cs = s.toCharArray();
		StringBuilder stringBuilder = new StringBuilder();
		reverseChars(cs, 0, cs.length - 1);
		int pre = 0, cur = 0;
		while (cur < cs.length) {
			if (cs[cur] == ' ') {
				if (pre != cur) {
					reverseChars(cs, pre, cur - 1);
					stringBuilder.append(String.valueOf(cs, pre, cur - pre)).append(" ");
				}
				pre = cur + 1;
			}
			cur++;
		}
		if (cur > 0 && cs[cur - 1] != ' ') {
			reverseChars(cs, pre, cur - 1);
			stringBuilder.append(String.valueOf(cs, pre, cur - pre)).append(" ");
		}
		return stringBuilder.length() == 0 ? "" : stringBuilder.toString().substring(0, stringBuilder.length() - 1);
	}

	public String reverseWords2_(String s) {
		StringBuilder sb = trimSpaces(s);

		// 翻转字符串
		reverse(sb, 0, sb.length() - 1);

		// 翻转每个单词
		reverseEachWord(sb);

		return sb.toString();
	}

	public String reverseWords3(String s) {
		int left = 0, right = s.length() - 1;
		// 去掉字符串开头的空白字符
		while (left <= right && s.charAt(left) == ' ') {
			++left;
		}

		// 去掉字符串末尾的空白字符
		while (left <= right && s.charAt(right) == ' ') {
			--right;
		}

		LinkedList<String> stack = new LinkedList<>();
		StringBuilder word = new StringBuilder();

		while (left <= right) {
			char c = s.charAt(left);
			if ((word.length() != 0) && (c == ' ')) {
				// 将单词 push 到队列的头部
				stack.push(word.toString());
				word.setLength(0);
			} else if (c != ' ') {
				word.append(c);
			}
			++left;
		}
		stack.push(word.toString());
		return String.join(" ", stack);
	}

	public StringBuilder trimSpaces(String s) {
		int left = 0, right = s.length() - 1;
		// 去掉字符串开头的空白字符
		while (left <= right && s.charAt(left) == ' ') {
			++left;
		}

		// 去掉字符串末尾的空白字符
		while (left <= right && s.charAt(right) == ' ') {
			--right;
		}

		// 将字符串间多余的空白字符去除
		StringBuilder sb = new StringBuilder();
		while (left <= right) {
			char c = s.charAt(left);

			if (c != ' ') {
				sb.append(c);
			} else if (sb.charAt(sb.length() - 1) != ' ') {
				sb.append(c);
			}

			++left;
		}
		return sb;
	}

	public void reverse(StringBuilder sb, int left, int right) {
		while (left < right) {
			char tmp = sb.charAt(left);
			sb.setCharAt(left++, sb.charAt(right));
			sb.setCharAt(right--, tmp);
		}
	}

	public void reverseEachWord(StringBuilder sb) {
		int n = sb.length();
		int start = 0, end = 0;

		while (start < n) {
			// 循环至单词的末尾
			while (end < n && sb.charAt(end) != ' ') {
				++end;
			}
			// 翻转单词
			reverse(sb, start, end - 1);
			// 更新start，去找下一个单词
			start = end + 1;
			++end;
		}
	}

	private void reverseChars(char[] cs, int start, int end) {
		for (int i = start, j = end; i < j; i++, j--) {
			char c = cs[i];
			cs[i] = cs[j];
			cs[j] = c;
		}
	}
}
