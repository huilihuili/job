package vip.eagleli.leetcode;

public class LeetCode29 {

	public static void main(String[] args) {
		// System.out.println(Integer.MIN_VALUE);
		// System.out.println(-Integer.MIN_VALUE);
		// System.out.println(Integer.MAX_VALUE);
		// System.out.println(-Integer.MAX_VALUE);

		System.out.println(Integer.MIN_VALUE >> 1);

		System.out.println(new LeetCode29().div(-1100540749, -1090366779));
	}

	public int divide(int dividend, int divisor) {
		if (divisor == 0) {
			throw new RuntimeException();
		}

		if (divisor == 1) {
			return dividend;
		}

		if (divisor == -1) {
			if (dividend == Integer.MIN_VALUE) {
				return Integer.MAX_VALUE;
			}
			return -dividend;
		}

		int ans = 0;
		boolean sign = dividend > 0 ^ divisor > 0;
		dividend = dividend < 0 ? dividend : -dividend;
		divisor = divisor < 0 ? divisor : -divisor;

		while (dividend <= divisor) {
			dividend -= divisor;
			ans++;
		}
		return sign ? -ans : ans;
	}

	public int divide2(int dividend, int divisor) {
		if (divisor == 0) {
			throw new RuntimeException();
		}

		if (divisor == 1) {
			return dividend;
		}

		if (divisor == -1) {
			if (dividend == Integer.MIN_VALUE) {
				return Integer.MAX_VALUE;
			}
			return -dividend;
		}

		boolean sign = dividend > 0 ^ divisor > 0;
		dividend = dividend < 0 ? dividend : -dividend;
		divisor = divisor < 0 ? divisor : -divisor;
		return sign ? -div(dividend, divisor) : div(dividend, divisor);
	}

	/**
	 * 
	 * 
	 * @param dividend 是负数
	 * @param divisor 是负数
	 * @return 
	 */
	public int div(int dividend, int divisor) {
		if (dividend > divisor) {
			return 0;
		}
		int ans = 1, originDivisor = divisor;
		while (dividend < (divisor << 1) && (divisor << 1) < 0) {
			ans <<= 1;
			divisor <<= 1;
		}
		return ans + div(dividend - divisor, originDivisor);
	}

	public int divide3(int dividend, int divisor) {
		if (divisor == 0) {
			throw new RuntimeException();
		}

		if (divisor == 1) {
			return dividend;
		}

		if (divisor == -1) {
			if (dividend == Integer.MIN_VALUE) {
				return Integer.MAX_VALUE;
			}
			return -dividend;
		}

		boolean sign = dividend > 0 ^ divisor > 0;
		dividend = dividend < 0 ? dividend : -dividend;
		divisor = divisor < 0 ? divisor : -divisor;

		int ans = 0;
		while (dividend <= divisor) {
			int divisorCopy = divisor;
			int count = 1;
			while (dividend <= (divisorCopy << 1) && (divisorCopy << 1) < 0) {
				divisorCopy <<= 1;
				count <<= 1;
			}

			dividend -= divisorCopy;
			ans += count;
		}

		return sign ? -ans : ans;
	}

}
