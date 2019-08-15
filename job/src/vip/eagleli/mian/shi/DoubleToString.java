package vip.eagleli.mian.shi;

public class DoubleToString {
	public static void main(String[] args) {
		System.out.println(1.23 % 10);
	}

	private static String solve(double d) {
		StringBuilder stb = new StringBuilder();
		if (d < 0) {
			d = d * -1;
			stb.append("-");
		}

		int leftCount = 0;
		while (d > 1) {
			d /= 10;
			leftCount++;
		}
		while (d > 0) {
			d = d * 10;
			stb.append(d % 10);
		}
		return stb.toString();
	}
}
