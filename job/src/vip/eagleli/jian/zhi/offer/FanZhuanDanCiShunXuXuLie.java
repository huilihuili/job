package vip.eagleli.jian.zhi.offer;

import java.util.Arrays;

public class FanZhuanDanCiShunXuXuLie {
	public static void main(String[] args) {
		System.out.println(reverseSentence("I am a student."));
	}

	public static String reverseSentence(String str) {
		if (" ".equals(str)) {
			return str;
		}
		String[] strs = str.split(" ");
		StringBuilder sb = new StringBuilder();
		sb.append(strs[strs.length - 1]);
		for (int i = strs.length - 2; i >= 0; i--) {
			sb.append(" ").append(strs[i]);
		}
		return sb.toString();
	}
}
