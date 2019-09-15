package vip.eagleli.programming.wei.pin.hui;

public class ErChaShuDeZhiJing {

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		public TreeNode(int val) {
			super();
			this.val = val;
		}
	}

	static class Res {
		int depth;
		int max;

		public Res(int depth, int max) {
			this.depth = depth;
			this.max = max;
		}
	}

	public static void main(String[] args) {

	}

	public int diameter(TreeNode root) {
		Res res = solve(root);
		return res.max;
	}

	private Res solve(TreeNode root) {
		if (root == null) {
			return new Res(0, 0);
		}
		Res left = solve(root.left);
		Res right = solve(root.right);

		int depth = Math.max(left.depth, right.depth) + 1;
		int max = Math.max(left.depth + right.depth, Math.max(left.max, right.max));
		return new Res(depth, max);
	}
}
