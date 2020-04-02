package vip.eagleli.leetcode;

public class LeetCode1111 {
	public int[] maxDepthAfterSplit(String seq) {
		int[] ans = new int[seq.length()];
		int idx = 0, depth = 0;
		for (char c : seq.toCharArray()) {
			ans[idx++] = c == '(' ? ++depth % 2 : depth-- % 2;
		}
		return ans;
	}
}
