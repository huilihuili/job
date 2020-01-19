package vip.eagleli.leetcode;

public class LeetCode7 {
	public static void main(String[] args) {
		System.out.println(-123 % 10);

		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MAX_VALUE / 10 * 10);

		System.out.println(Integer.MAX_VALUE / 10);

		System.out.println((Integer.MAX_VALUE - 9) / 10);
		System.out.println((Integer.MAX_VALUE - 9) / 10 * 10 + 9);

		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MIN_VALUE / 10 * 10);
	}

	/**
	 * 用long来判断是否溢出
	 * 
	 * @param x
	 * @return
	 */
	public int reverse(int x) {
		boolean negtive = false;
		if (x < 0) {
			negtive = true;
			x = -1 * x;
		}

		long ans = 0;
		while (x > 0) {
			ans = ans * 10 + x % 10;
			x = x / 10;
		}
		return ans > Integer.MAX_VALUE ? 0 : (int) (negtive ? ans * -1 : ans);
	}

	/**
	 * 官方答案
	 * 
	 * 提前判断是否越界
	 * 
	 * 不用区分正数和负数
	 * 
	 * @param x
	 * @return
	 */
	public int reverse2(int x) {
		int ans = 0;
		while (x != 0) {
			int pop = x % 10;
			x = x / 10;
			
			if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > Integer.MAX_VALUE % 10)) {
				return 0;
			}

			if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < Integer.MIN_VALUE % 10)) {
				return 0;
			}
			ans = ans * 10 + pop;
		}
		return ans;
	}
}
