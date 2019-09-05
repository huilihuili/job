package vip.eagleli.programming.xie.cheng;

import java.util.LinkedList;
import java.util.Scanner;

public class BiaoDaShiJieXi {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String res;

		String _expr;
		try {
			_expr = in.nextLine();
		} catch (Exception e) {
			_expr = null;
		}

		res = resolve(_expr);
		System.out.println(res);
	}

	/*
	 * ������������������ʵ����ĿҪ��Ĺ��� ��Ȼ����Ҳ���Բ������������ģ����������ȫ�����Լ����뷨�� ^-^ ��ʼд����
	 ******************************/
	static String resolve(String expr) {
		StringBuilder stringBuilder = reverse(expr, -1, expr.length());
		if (stringBuilder == null) {
			return "";
		}
		return stringBuilder.toString();
	}

	static StringBuilder reverse(String string, int start, int end) {
		int index = start + 1;
		StringBuilder stringBuilder = new StringBuilder();
		while (index < end) {
			if (string.charAt(index) == '(') {
				int e = end - 1;
				while (e > index && string.charAt(e) != ')') {
					e--;
				}
				if (e == index) {
					return null;
				}
				StringBuilder stringBuildert = reverse(string, index, e);
				if (stringBuilder == null) {
					return null;
				}
				stringBuilder.append(stringBuildert.reverse().toString());
				index = e;
			} else if (string.charAt(index) == ')') {
				return null;
			} else {
				stringBuilder.append(string.charAt(index));
			}
			index++;
		}
		return stringBuilder;
	}
	/****************************** ����д���� ******************************/
}
