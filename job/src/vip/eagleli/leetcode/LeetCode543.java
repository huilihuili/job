package vip.eagleli.leetcode;

public class LeetCode543 {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	static class Result {
		int height;
		int diameter;

		public Result(int height, int diameter) {
			super();
			this.height = height;
			this.diameter = diameter;
		}
	}

	public int diameterOfBinaryTree(TreeNode root) {
		return dfs(root).diameter;
	}

	public Result dfs(TreeNode root) {
		if (root == null) {
			return new Result(0, 0);
		}
		Result leftResult = dfs(root.left);
		Result rightResult = dfs(root.right);
		int leftHeight = leftResult.height;
		int rightHeight = rightResult.height;
		return new Result(Math.max(leftHeight, rightHeight) + 1,
				Math.max(leftHeight + rightHeight, Math.max(leftResult.diameter, rightResult.diameter)));
	}

	int ans;

	public int diameterOfBinaryTree2(TreeNode root) {
		ans = 1;
		depth(root);
		return ans - 1;
	}

	public int depth(TreeNode node) {
		if (node == null) {
			return 0; // 访问到空节点了，返回0
		}
		int L = depth(node.left); // 左儿子为根的子树的深度
		int R = depth(node.right); // 右儿子为根的子树的深度
		ans = Math.max(ans, L + R + 1); // 计算d_node即L+R+1 并更新ans
		return Math.max(L, R) + 1; // 返回该节点为根的子树的深度
	}

}
