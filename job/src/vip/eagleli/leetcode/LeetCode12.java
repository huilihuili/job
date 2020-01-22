package vip.eagleli.leetcode;

public class LeetCode12 {
	public static void main(String[] args) {
	}

	/**
	 * 记得以前看过思路
	 * 
	 * 凭印象写出来的
	 * 
	 * 官方没有给答案
	 * 
	 * @param num
	 * @return
	 */
	public String intToRoman(int num) {
		int[] ints = { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };
		String[] romans = { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M" };

		StringBuilder sb = new StringBuilder();
		int index = ints.length - 1;
		while (num > 0 && index >= 0) {
			if (num >= ints[index]) {
				num -= ints[index];
				sb.append(romans[index]);
			} else {
				index--;
			}
		}
		return sb.toString();
	}
}
