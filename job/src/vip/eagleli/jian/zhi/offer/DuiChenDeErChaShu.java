package vip.eagleli.jian.zhi.offer;

public class DuiChenDeErChaShu {

	private static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		TreeNode treeNode1 = new TreeNode(8);
		TreeNode treeNode2 = new TreeNode(6);
		TreeNode treeNode3 = new TreeNode(6);
		TreeNode treeNode4 = new TreeNode(5);
		TreeNode treeNode5 = new TreeNode(7);
		TreeNode treeNode6 = new TreeNode(7);
		TreeNode treeNode7 = new TreeNode(5);
		treeNode1.left = treeNode2;
		treeNode1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode2.right = treeNode5;
		treeNode3.left = treeNode6;
		treeNode3.left = treeNode7;
		System.out.println(isSymmetrical(treeNode1));
	}

	private static boolean isSymmetrical(TreeNode pRoot) {
		return isSymmetrical(pRoot.left, pRoot.right);
	}

	private static boolean isSymmetrical(TreeNode root1, TreeNode root2) {
		if (root1 == null && root2 == null) {
			return true;
		}

		if (root1 == null || root2 == null) {
			return false;
		}

		if (root1.val != root2.val) {
			return false;
		}

		return isSymmetrical(root1.left, root2.right) && isSymmetrical(root1.right, root2.left);
	}
}
