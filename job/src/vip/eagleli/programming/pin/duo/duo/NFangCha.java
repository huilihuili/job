package vip.eagleli.programming.pin.duo.duo;

import java.util.Scanner;

public class NFangCha {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[n];
		for (int i = 0; i < n; i++) {
			arr[i] = scanner.nextInt();
		}
		System.out.printf("%.2f\n", solve(n, arr));
	}

	private static double solve(int n, int[] arr) {
		double[] ds = new double[3];
		double res = Double.MAX_VALUE;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				for (int k = j + 1; k < n; k++) {
					ds[0] = arr[i];
					ds[1] = arr[j];
					ds[2] = arr[k];
					res = Math.min(res, varianceImperative(ds));
				}
			}
		}
		return res;
	}

	private static double varianceImperative(double[] population) {
		double average = 0.0;
		for (double p : population) {
			average += p;
		}
		average /= population.length;

		double variance = 0.0;
		for (double p : population) {
			variance += (p - average) * (p - average);
		}
		return variance / population.length;
	}
}
