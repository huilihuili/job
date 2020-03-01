package vip.eagleli.leetcode;

public class LeetCode50 {

	public static void main(String[] args) {
		LeetCode50 leetCode50 = new LeetCode50();
		System.out.println(leetCode50.myPow(2, 10));
		System.out.println(leetCode50.myPow(2.1, 3));
		System.out.println(leetCode50.myPow(2, -2));

		System.out.println();

		System.out.println(leetCode50.myPow2(2, 10));
		System.out.println(leetCode50.myPow2(2.1, 3));
		System.out.println(leetCode50.myPow2(2, -2));
	}

	public double myPow(double x, int n) {
		long ncopy = n;
		return n > 0 ? fastPow(x, Math.abs(ncopy)) : 1 / fastPow(x, Math.abs(ncopy));
	}

	private double fastPow(double x, long n) {
		if (n == 0) {
			return 1;
		}

		if (n == 1) {
			return x;
		}

		double half = fastPow(x, n / 2);
		double rest = fastPow(x, n % 2);
		return half * half * rest;
	}

	public double myPow2(double x, int n) {
		long ncopy = n;
		return n > 0 ? fastPow2(x, Math.abs(ncopy)) : 1 / fastPow2(x, Math.abs(ncopy));
	}

	/**
	 * 2^10
	 * 10的二进制为1010
	 * 2^(1010)
	 * 
	 * 2=2^1
	 * 2*2=2^2
	 * 2*2*2*2=2^4		
	 * 2*2*2*2*2*2*2*2=2^8
	 * 
	 * 2^(1010)=(2^8*1)(2^4*0)(2^2*1)(2^1*0)=2^8*2^2=2^10
	 * 
	 * @param x
	 * @param n
	 * @return
	 */
	private double fastPow2(double x, long n) {
		if (n == 0) {
			return 1;
		}

		if (n == 1) {
			return x;
		}

		double ans = 1, cur = x;
		while (n > 0) {
			if ((n & 1) == 1) {
				ans *= cur;
			}
			cur = cur * cur;
			n >>= 1;
		}
		return ans;
	}
}
