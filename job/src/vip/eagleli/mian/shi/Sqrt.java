package vip.eagleli.mian.shi;

public class Sqrt {
	public static void main(String[] args) {
		System.out.println(Math.sqrt(3));
		System.out.println(sqrt(3));
	}

	static double sqrt(double value) {
		double xn = 1;
		final int N = 100;
		for (int i = 1; i < N; i++) {
			xn = xn / 2 + value / (xn * 2);
		}
		return xn;
	}
}
