package vip.eagleli.programming.guang.lian.da;

public class YuanDeMianJi {
	public static void main(String[] args) {
		int[] a = { 1, 2, 3, 4, 5 };
		for (int i : a) {
			System.out.println(i + " " + solve(i));
		}
	}

	private static int solve(int n) {
		if (n <= 1) {
			return 0;
		}
		int a = (int) ((n * 2) / Math.pow(2, 1.0 / 2));
		return a * a;
	}
}
