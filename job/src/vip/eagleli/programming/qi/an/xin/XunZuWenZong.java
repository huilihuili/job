package vip.eagleli.programming.qi.an.xin;

import java.util.Scanner;

public class XunZuWenZong {
	static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			super();
			this.value = value;
		}

		@Override
		public String toString() {
			return this.value + "";
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int[] arr = new int[(int) Math.pow(2, n) - 1];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = scanner.nextInt();
		}
		int p = scanner.nextInt(), q = scanner.nextInt();

		TreeNode[] treeNodes = new TreeNode[arr.length];
		for (int i = arr.length - 1; i > -1; i--) {
			int l = i * 2 + 1;
			int r = i * 2 + 2;
			treeNodes[i] = new TreeNode(arr[i]);
			if (l < arr.length && arr[l] != -1) {
				treeNodes[i].left = treeNodes[l];
			}
			if (r < arr.length && arr[r] != -1) {
				treeNodes[i].right = treeNodes[r];
			}
		}
		TreeNode treeNode = treeNodes[0];
		TreeNode pNode = find(treeNode, p);
		TreeNode qNode = find(treeNode, q);
		if (pNode == null || qNode == null) {
			System.out.println("-1");
		} else {
			System.out.println(solve(treeNode, pNode, qNode).value);
		}
	}

	public static TreeNode find(TreeNode treeNode, int value) {
		if (treeNode == null) {
			return null;
		}

		if (treeNode.value == value) {
			return treeNode;
		}

		if (treeNode.value < value) {
			return find(treeNode.right, value);
		}
		return find(treeNode.left, value);
	}

	// recursively
	public static TreeNode solve(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null || root == p || root == q) {
			return root;
		}
		TreeNode l = solve(root.left, p, q);
		TreeNode r = solve(root.right, p, q);
		return l != null && r != null ? root : l == null ? r : l;
	}
}
