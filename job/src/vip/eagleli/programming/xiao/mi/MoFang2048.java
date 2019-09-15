package vip.eagleli.programming.xiao.mi;

import java.util.Arrays;
import java.util.Scanner;

public class MoFang2048 {
	/*
	 * 请完成下面这个函数，实现题目要求的功能 当然，你也可以不按照下面这个模板来作答，完全按照自己的想法来 ^-^ 开始写代码
	 ******************************/
	static String solution(String[] input) {
		int[][] arr = new int[input.length][];
		for (int i = 0; i < input.length; i++) {
			String[] strings = input[i].split(" ");
			int[] arrt = new int[strings.length];
			for (int j = 0; j < strings.length; j++) {
				arrt[j] = Integer.valueOf(strings[j].trim());
			}
			arr[i] = arrt;
		}
		solution(arr);
		StringBuilder stringBuilder = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (j > 0) {
					stringBuilder.append(" ");
				}
				stringBuilder.append(arr[i][j]);
			}
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	static void solution(int[][] input) {
		for (int[] is : input) {
			solution(is);
		}
	}

	static void solution(int[] input) {
		int index = 0;
		int start = 0;
		while (start < input.length && input[start] == 0) {
			start++;
		}
		while (start < input.length) {
			if (start + 1 < input.length && input[start] == input[start + 1]) {
				input[index++] = input[start] * 2;
				start += 2;
				continue;
			}

			input[index++] = input[start++];
		}
		while (index < input.length) {
			input[index++] = 0;
		}
	}

	/****************************** 结束写代码 ******************************/

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String res;

		int _input_size = 0;
		_input_size = Integer.parseInt(in.nextLine().trim());
		String[] _input = new String[_input_size];
		String _input_item;
		for (int _input_i = 0; _input_i < _input_size; _input_i++) {
			try {
				_input_item = in.nextLine();
			} catch (Exception e) {
				_input_item = null;
			}
			_input[_input_i] = _input_item;
		}

		res = solution(_input);
		System.out.println(res);
	}
}
