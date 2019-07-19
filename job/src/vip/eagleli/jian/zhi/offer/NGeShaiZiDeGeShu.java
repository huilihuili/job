package vip.eagleli.jian.zhi.offer;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NGeShaiZiDeGeShu {
	public static void main(String[] args) {
		System.out.println(solve(2));
	}

	private static List<Map.Entry<Integer, Double>> solve(int number) {
		int maxSum = 6 * number;
		double total = Math.pow(6, number);
		long[][] probabilities = new long[number + 1][maxSum + 1];
		for (int i = 1; i <= 6; i++) {
			probabilities[1][i] = 1;
		}
		for (int i = 2; i <= number; i++) {
			for (int j = i; j <= i * 6; j++) {
				for (int k = 1; k <= j && k <= 6; k++) {
					probabilities[i][j] += probabilities[i - 1][j - k];
				}
			}
		}
		List<Map.Entry<Integer, Double>> res = new ArrayList<>();
		for (int i = number; i <= maxSum; i++) {
			res.add(new AbstractMap.SimpleEntry(i, probabilities[number][i] / total));
		}
		return res;
	}
}
