package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Stack;

public class LeetCode199 {
	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public List<Integer> rightSideView(TreeNode root) {
		if (root == null) {
			return new ArrayList<>();
		}

		List<Integer> rightView = new ArrayList<Integer>();
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			rightView.add(queue.peek().val);
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				TreeNode treeNode = queue.poll();
				if (treeNode.right != null) {
					queue.offer(treeNode.right);
				}
				if (treeNode.left != null) {
					queue.offer(treeNode.left);
				}
			}
		}
		return rightView;
	}

	public List<Integer> rightSideView_(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		solve(root, 0, res);
		return res;
	}

	public void solve(TreeNode root, int level, List<Integer> res) {
		if (root != null) {
			if (res.size() == level) {
				res.add(root.val);
			}
			solve(root.right, level + 1, res);
			solve(root.left, level + 1, res);
		}
	}

	public List<Integer> rightSideView2(TreeNode root) {
		Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
		int max_depth = -1;

		Stack<TreeNode> nodeStack = new Stack<TreeNode>();
		Stack<Integer> depthStack = new Stack<Integer>();
		nodeStack.push(root);
		depthStack.push(0);

		while (!nodeStack.isEmpty()) {
			TreeNode node = nodeStack.pop();
			int depth = depthStack.pop();

			if (node != null) {
				// 维护二叉树的最大深度
				max_depth = Math.max(max_depth, depth);

				// 如果不存在对应深度的节点我们才插入
				if (!rightmostValueAtDepth.containsKey(depth)) {
					rightmostValueAtDepth.put(depth, node.val);
				}

				nodeStack.push(node.left);
				nodeStack.push(node.right);
				depthStack.push(depth + 1);
				depthStack.push(depth + 1);
			}
		}

		List<Integer> rightView = new ArrayList<Integer>();
		for (int depth = 0; depth <= max_depth; depth++) {
			rightView.add(rightmostValueAtDepth.get(depth));
		}
		return rightView;
	}

	public List<Integer> rightSideView2_(TreeNode root) {
		Map<Integer, Integer> rightmostValueAtDepth = new HashMap<Integer, Integer>();
		int max_depth = -1;

		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		Queue<Integer> depthQueue = new LinkedList<Integer>();
		nodeQueue.add(root);
		depthQueue.add(0);

		while (!nodeQueue.isEmpty()) {
			TreeNode node = nodeQueue.remove();
			int depth = depthQueue.remove();

			if (node != null) {
				// 维护二叉树的最大深度
				max_depth = Math.max(max_depth, depth);

				// 由于每一层最后一个访问到的节点才是我们要的答案，因此不断更新对应深度的信息即可
				rightmostValueAtDepth.put(depth, node.val);

				nodeQueue.add(node.left);
				nodeQueue.add(node.right);
				depthQueue.add(depth + 1);
				depthQueue.add(depth + 1);
			}
		}

		List<Integer> rightView = new ArrayList<Integer>();
		for (int depth = 0; depth <= max_depth; depth++) {
			rightView.add(rightmostValueAtDepth.get(depth));
		}

		return rightView;
	}

	public List<Integer> rightSideView3(TreeNode root) {
		if (root == null) {
			return new ArrayList<Integer>();
		}

		List<Integer> leftList = rightSideView(root.left);// 左子树生成的列表
		List<Integer> rightList = rightSideView(root.right);// 右子树生成的列表

		int max = Math.max(leftList.size(), rightList.size());// 左右子树列表的最大长度
		List<Integer> list = new ArrayList<Integer>(max);// 最终合成的列表
		list.add(root.val);// 先把当前节点加入最终列表
		for (int i = 0; i < max; i++) {// 优先加入右子树的列表元素，若右子树加完了，则开始加入左子树列表
			if (i < rightList.size()) {
				list.add(rightList.get(i));
			} else {
				list.add(leftList.get(i));
			}
		}
		return list;
	}

}
