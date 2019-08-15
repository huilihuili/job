package vip.eagleli.leetcode;

public class LeetCode58 {
	public static void main(String[] args) {
		System.out.println(lengthOfLastWord("a"));
	}

	public static int lengthOfLastWord(String s) {
		int res = 0;
		for (int i = s.length() - 1; i > -1 && s.charAt(i) != ' '; i--) {
			res++;
		}
		return res;
		
	}
}
