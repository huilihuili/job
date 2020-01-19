package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.List;

public class LeetCode6 {

	/**
	 * 借用了一个二维数组
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert(String s, int numRows) {
		List<char[]> list = new ArrayList<>();
		int index = 0, n = s.length();
		while (index < n) {
			list.add(new char[numRows]);
			for (int i = 0; i < numRows && index < n; i++, index++) {
				list.get(list.size() - 1)[i] = s.charAt(index);
			}

			for (int i = 0; i < numRows - 2 && index < n; i++, index++) {
				list.add(new char[numRows]);
				list.get(list.size() - 1)[numRows - 2 - i] = s.charAt(index);
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j)[i] == 0) {
					continue;
				}
				sb.append(list.get(j)[i]);
			}
		}
		return sb.toString();
	}

	/**
	 * 官方答案
	 * 
	 * 去掉数组
	 * 
	 * 按行排序
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert2(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}

		List<StringBuilder> rows = new ArrayList<>();
		for (int i = 0; i < Math.min(numRows, s.length()); i++) {
			rows.add(new StringBuilder());
		}

		int curRow = 0;
		boolean goingDown = false;

		for (int i = 0; i < s.length(); i++) {
			rows.get(curRow).append(s.charAt(i));
			if (curRow == 0 || curRow == numRows - 1) {
				goingDown = !goingDown;
			}
			curRow += goingDown ? 1 : -1;
		}

		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < rows.size(); i++) {
			ans.append(rows.get(i));
		}
		return ans.toString();
	}

	/**
	 * 官方答案
	 * 
	 * 利用规律
	 * 
	 * 按行访问
	 * 
	 * @param s
	 * @param numRows
	 * @return
	 */
	public String convert3(String s, int numRows) {
		if (numRows == 1) {
			return s;
		}
		int n = s.length(), cycleLen = 2 * numRows - 2;
		StringBuilder ans = new StringBuilder();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j + i < n; j += cycleLen) {
				ans.append(s.charAt(j + i));
				if (i != 0 && i != numRows - 1 && j + cycleLen - i < n) {
					ans.append(s.charAt(j + cycleLen - i));
				}
			}
		}
		return ans.toString();
	}
}
