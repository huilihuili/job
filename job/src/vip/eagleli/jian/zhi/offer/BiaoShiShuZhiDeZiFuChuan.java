package vip.eagleli.jian.zhi.offer;

public class BiaoShiShuZhiDeZiFuChuan {
	public static void main(String[] args) {
		System.out.println(new BiaoShiShuZhiDeZiFuChuan().isNumeric("-1.45e+6".toCharArray()));
	}

	public boolean isNumeric(char[] str) {
		int index = 0;
		if (str[index] == '-' || str[index] == '+') {
			index++;
		}
		int end = scanInteger(str, index);
		boolean res = judge(index, end);
		index = end;

		if (index < str.length && str[index] == '.') {
			index++;
			end = scanInteger(str, index);
			res = res || judge(index, end);
			index = end;
		}

		if (index < str.length && (str[end] == 'e' || str[end] == 'E')) {
			index++;
			if ((index < str.length) && (str[index] == '+' || str[index] == '-')) {
				index++;
			}
			end = scanInteger(str, index);
			res = res && judge(index, end);
			index = end;
		}
		return res && index == str.length;
	}

	public int scanInteger(char[] str, int start) {
		for (int i = start; i < str.length; i++) {
			if (0 <= str[i] - '0' && str[i] - '0' <= 9) {
				continue;
			}
			return i;
		}
		return str.length;
	}

	public boolean judge(int start, int end) {
		return end > start ? true : false;
	}
}
