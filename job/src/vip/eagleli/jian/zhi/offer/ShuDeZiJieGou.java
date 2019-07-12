package vip.eagleli.jian.zhi.offer;

public class ShuDeZiJieGou {

	private static class TreeNode {
		int val = 0;
		TreeNode left = null;
		TreeNode right = null;

		public TreeNode(int val) {
			this.val = val;
		}
	}

	public static void main(String[] args) {
		TreeNode root1 = new TreeNode(8);
		TreeNode treeNode2 = new TreeNode(8);
		TreeNode treeNode3 = new TreeNode(7);
		TreeNode treeNode4 = new TreeNode(9);
		TreeNode treeNode5 = new TreeNode(2);
		TreeNode treeNode6 = new TreeNode(4);
		TreeNode treeNode7 = new TreeNode(7);
		root1.left = treeNode2;
		root1.right = treeNode3;
		treeNode2.left = treeNode4;
		treeNode2.right = treeNode5;
		treeNode5.left = treeNode6;
		treeNode6.left = treeNode7;

		TreeNode root2 = new TreeNode(8);
		TreeNode root2_1 = new TreeNode(9);
		TreeNode root2_2 = new TreeNode(2);
		root2.left = root2_1;
		root2.right = root2_2;
		System.out.println(hasSubtree(root1, root2));
	}

	public static boolean hasSubtree(TreeNode root1, TreeNode root2) {
		StringBuilder sb1 = new StringBuilder();
		StringBuilder sb2 = new StringBuilder();
		serializeTree(root1, sb1);
		serializeTree(root2, sb2);
		System.out.println(sb1);
		System.out.println(sb2);
		return sb1.toString().contains(sb2.toString());
	}

	public static void serializeTree(TreeNode root, StringBuilder sb) {
		if (root != null) {
			sb.append(root.val).append("_");
			serializeTree(root.left, sb);
			serializeTree(root.right, sb);
		}
	}
}
