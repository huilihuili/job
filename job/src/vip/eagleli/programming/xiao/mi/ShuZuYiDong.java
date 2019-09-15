package vip.eagleli.programming.xiao.mi;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class ShuZuYiDong {

	/*
	 * 请完成下面这个函数，实现题目要求的功能 当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^ 开始写代码
	 ******************************/

	static int solution(int[] arr) {
		Integer[] integers = new Integer[arr.length];
		for (int i = 0; i < arr.length; i++) {
			integers[i] = arr[i];
		}
		Arrays.sort(integers);
		int res1 = solution(arr, integers);
		Arrays.sort(integers, (i1, i2) -> {
			return i2 - i1;
		});
		int res2 = solution(arr, integers);
		return Math.min(res1, res2);
	}

	static int solution(int[] arr, Integer[] integers) {
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			hashMap.put(arr[i], i);
		}
		int res = 0;
		for (int i = 0; i < arr.length; i++) {
			if (integers[i] == arr[i]) {
				continue;
			}
			res++;
			swap(arr, i, hashMap.get(integers[i]));
		}
		return res;
	}

	static int solution1(int[] arr) {
		int res = 0;
		int[] arr1 = Arrays.copyOf(arr, arr.length);
		HashMap<Integer, Integer> hashMap = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			hashMap.put(arr[i], i);
		}
		Arrays.sort(arr1);
		for (int i = 0; i < arr.length; i++) {
			if (arr1[i] == arr[i]) {
				continue;
			}
			res++;
			swap(arr, i, hashMap.get(arr1[i]));
		}
		return res;
	}

	static void swap(int[] arr, int i, int j) {
		int t = arr[i];
		arr[i] = arr[j];
		arr[j] = t;
	}

	/****************************** 结束写代码 ******************************/

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int res;

		int _arr_size = 0;
		_arr_size = Integer.parseInt(in.nextLine().trim());
		int[] _arr = new int[_arr_size];
		int _arr_item;
		for (int _arr_i = 0; _arr_i < _arr_size; _arr_i++) {
			_arr_item = Integer.parseInt(in.nextLine().trim());
			_arr[_arr_i] = _arr_item;
		}

		res = solution(_arr);
		System.out.println(String.valueOf(res));

	}
}
