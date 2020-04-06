package vip.eagleli.leetcode.weekly.contest_183;

import java.math.BigInteger;

public class NumSteps_5377 {
	public static void main(String[] args) {
		NumSteps_5377 numSteps_5377 = new NumSteps_5377();
		System.out.println(numSteps_5377.numSteps("1101"));
	}

	public int numSteps(String s) {
		BigInteger bigInteger = new BigInteger(s, 2);
		BigInteger one = new BigInteger("1", 10);
		int ans = 0;
		while (bigInteger.compareTo(one) != 0) {
			if (bigInteger.and(one).intValue() != 0) {
				bigInteger = bigInteger.add(one);
			} else {
				bigInteger = bigInteger.shiftRight(1);
			}
			ans++;
		}
		return ans;
	}

}
