package vip.eagleli.jian.zhi.offer;

import java.util.Arrays;

public class ZuiChangBuHanChongFuZiFuDeZiZiFuChuan {
	public static void main(String[] args) {
		System.out.println(longestSubStringWithoutDuplication("arabcacfr"));
	}

	public static int longestSubStringWithoutDuplication(String str) {
		int curLen = 0;
		int maxLen = 0;
		int[] preIndexs = new int[26];
		Arrays.fill(preIndexs, -1);
		for (int curI = 0; curI < str.length(); curI++) {
			int c = str.charAt(curI) - 'a';
			int preI = preIndexs[c];
			if (preI == -1 || curI - preI > curLen) {
				System.out.println(curI + " " + preI + " " + (curI - preI) + " " + curLen);
				curLen++;
			} else {
				maxLen = Math.max(maxLen, curLen);
				curLen = curI - preI;
			}
			preIndexs[c] = curI;
		}
		maxLen = Math.max(maxLen, curLen);
		return maxLen;
	}
}
