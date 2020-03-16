package vip.eagleli.leetcode.weekly.contest_180;

import java.util.ArrayList;
import java.util.List;

public class BalanceBST_5179 {

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public TreeNode balanceBST(TreeNode root) {
		if (root == null) {
			return root;
		}
		ArrayList<TreeNode> list = new ArrayList<>();
		inOrder(root, list);
		return balanceBST(list, 0, list.size() - 1);
	}

	public TreeNode balanceBST(ArrayList<TreeNode> list, int start, int end) {
		if (start > end) {
			return null;
		}
		int mid = start + (end - start) / 2;
		TreeNode res = list.get(mid);
		res.left = balanceBST(list, start, mid - 1);
		res.right = balanceBST(list, mid + 1, end);
		return res;
	}

	public void inOrder(TreeNode root, List<TreeNode> list) {
		if (root != null) {
			inOrder(root.left, list);
			list.add(root);
			inOrder(root.right, list);
		}
	}
}
