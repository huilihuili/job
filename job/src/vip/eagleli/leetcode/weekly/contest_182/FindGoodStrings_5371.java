package vip.eagleli.leetcode.weekly.contest_182;

public class FindGoodStrings_5371 {
	public static void main(String[] args) {
		FindGoodStrings_5371 findGoodStrings_5371 = new FindGoodStrings_5371();
		System.out.println(findGoodStrings_5371.findGoodStrings(2, "aa", "da", "b"));
		System.out.println(findGoodStrings_5371.add("az"));
	}

	public int findGoodStrings(int n, String s1, String s2, String evil) {
		int ans = 0, mod = 1000000007;
		for (String s = s1; s.length() == n && s.compareTo(s2) <= 0; s = add(s)) {
			if (!s.contains(evil)) {
				ans++;
				ans %= mod;
			}
		}
		return ans;
	}

	private String add(String string) {
		StringBuilder stringBuilder = new StringBuilder();
		int carry = 1;
		for (int i = string.length() - 1; i >= 0; i--) {
			if (carry != 0) {
				int c = string.charAt(i) + carry;
				if (c <= 'z') {
					stringBuilder.append((char) c);
					carry = 0;
				} else {
					stringBuilder.append((char) ('a' + c - 'z' - 1));
					carry = 1;
				}
			} else {
				stringBuilder.append(string.charAt(i));
			}
		}
		if (carry != 0) {
			stringBuilder.append("a");
		}
		return stringBuilder.reverse().toString();
	}
}
