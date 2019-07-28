package vip.eagleli.programming.pin.duo.duo;

import java.util.Scanner;

public class ShengXuShuZu {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String str1 = sc.nextLine();
		String str2 = sc.nextLine();
		String[] strs1 = str1.split(" ");
		String[] strs2 = str2.split(" ");
		long[] a = new long[strs1.length];
		long[] b = new long[strs2.length];
		for (int i = 0; i < strs1.length; i++) {
			a[i] = Long.valueOf(strs1[i]);
		}
		for (int i = 0; i < strs2.length; i++) {
			b[i] = Long.valueOf(strs2[i]);
		}
		if (!solve(a, b)) {
			System.out.println("NO");
		} else {
			for (int i = 0; i < a.length; i++) {
				System.out.print(a[i] + " ");
			}
			System.out.println();
		}
	}

	private static boolean solve(long[] a, long[] b) {
		int index = getIndex(a);
		int valueIndex = getValueIndex(a, b, index);
		if (valueIndex == -1) {
			if (index > 0) {
				index--;
				valueIndex = getValueIndex(a, b, index);
			}
			if (valueIndex == -1) {
				return false;
			}
		}
		a[index] = b[valueIndex];
		return true;
	}

	private static int getIndex(long[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] >= a[i + 1]) {
				return i + 1;
			}
		}
		return a.length - 1;
	}

	private static int getValueIndex(long[] a, long[] b, int index) {
		long min = (long) Integer.MIN_VALUE - 1, max = (long) Integer.MAX_VALUE + 1;
		if (index > 0) {
			min = a[index - 1];
		}
		if (index < a.length - 1) {
			max = a[index + 1];
		}
		return getValueIndex(b, min, max);
	}

	private static int getValueIndex(long[] b, long min, long max) {
		int res = -1;
		for (int i = 0; i < b.length; i++) {
			if (b[i] > min && b[i] < max) {
				res = res == -1 ? i : b[i] > b[res] ? i : res;
			}
		}
		return res;
	}
}
