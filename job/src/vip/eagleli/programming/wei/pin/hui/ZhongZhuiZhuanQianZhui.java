package vip.eagleli.programming.wei.pin.hui;

import java.util.LinkedList;

public class ZhongZhuiZhuanQianZhui {

	public static void main(String[] args) {
		System.out.println(solve("ab+c+"));
	}

	private static String solve(String str) {
		StringBuilder sb = new StringBuilder();
		LinkedList<Character> st = new LinkedList<>();
		char[] data = str.toCharArray();
		sb.append(data[0]);
		for (int i = 1; i < data.length; i++) {
			switch (data[i]) {
			case '*':
				sb.append(data[i]);
				sb.append(st.pop());
				break;
			case '/':
				sb.append(data[i]);
				sb.append(st.pop());
				break;
			case '+':
				sb.insert(0, "(");
				sb.append(data[i]);
				sb.append(st.pop());
				sb.append(")");
				break;
			case '-':
				sb.insert(0, "(");
				sb.append(data[i]);
				sb.append(st.pop());
				sb.append(")");
				break;
			default:
				st.push(data[i]);
				break;
			}
		}
		return sb.toString();
	}

}
