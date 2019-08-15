package vip.eagleli.mian.shi;

import java.util.Arrays;

public class ShuffleArray {
	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		shuffleArray(array);
	}

	public static void shuffleArray(int[] array) {
		System.out.println(Arrays.toString(array));
		for (int i = array.length - 1; i > -1; i--) {
			int t = (int) Math.floor(Math.random() * i);
			int temp = array[t];
			array[t] = array[i];
			array[i] = temp;
		}
		System.out.println(Arrays.toString(array));
	}
}
