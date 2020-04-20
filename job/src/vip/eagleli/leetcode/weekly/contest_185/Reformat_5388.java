package vip.eagleli.leetcode.weekly.contest_185;

public class Reformat_5388 {
	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
		char[] c1 = s1.toCharArray();
		char[] c2 = s2.toCharArray();
		int k = 0, num = 0;
		for (int i = 1; i <= n1; i++) {
			for (int j = 0; j < c1.length; j++) {
				if (c1[j] == c2[k]) {
					k++;
					if (k == c2.length) {
						k = 0;
						num++;
					}
				}
			}
		}
		return num / n2;
	}

}
