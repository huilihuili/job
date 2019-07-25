package vip.eagleli.leetcode;

public class LeetCode12 {
	public static void main(String[] args) {
		System.out.println(intToRoman(58));
	}
    public static String intToRoman(int num) {
        String[] cs = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] is = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        int index = 0;
        for (int i = 0; i < is.length; i++) {
            if (num >= is[i]) {
                index = i;
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            if (num < is[index]) {
                index++;
                continue;
            }
            int n = num / is[index];
            for (int i = 0; i < n; i++) {
                sb.append(cs[index]);
            }
            num = num % is[index];
            index++;
        }
        return sb.toString();
    }
}
