package vip.eagleli.programming.yong.you;

import java.util.Scanner;

public class ErChaShuTuiDao {
	private static class TreeNode {
		Character value;
		TreeNode left;
		TreeNode right;
		public TreeNode(Character value) {
			super();
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		String[] strings = string.split(",");
		System.out.println(solve(strings[0], strings[1]));
	}

	private static String solve(String str1, String str2) {
		StringBuilder stringBuilder = new StringBuilder();
		TreeNode treeNode = solve(str1.toCharArray(), str2.toCharArray(), 0, str1.length() - 1, 0, str2.length() - 1);
		postOrder(treeNode, stringBuilder);
		return stringBuilder.toString();
	}

	private static TreeNode solve(char[] pre, char[] in, int prel, int prer, int inl, int inr) {
		if (prel == prer && inl == inr) {
			return new TreeNode(pre[prel]);
		}
		char rootValue = pre[prel];
		TreeNode root = new TreeNode(rootValue);
		int len = 0;
		for (int i = inl; i <= inr; i++) {
			if (in[i] == rootValue) {
				break;
			}
			len++;
		}
		if (len > 0) {
			root.left = solve(pre, in, prel + 1, prel + len, inl, inl + len - 1);
		}

		if (prel + len + 1 <= prer) {
			root.right = solve(pre, in, prel + len + 1, prer, inl + len + 1, inr);
		}
		return root;
	}

	private static void postOrder(TreeNode treeNode, StringBuilder stringBuilder) {
		if (treeNode == null) {
			return;
		}
		postOrder(treeNode.left, stringBuilder);
		postOrder(treeNode.right, stringBuilder);
		stringBuilder.append(treeNode.value);
	}
}
