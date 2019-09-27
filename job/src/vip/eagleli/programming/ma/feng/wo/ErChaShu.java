package vip.eagleli.programming.ma.feng.wo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ErChaShu {

	static class TreeNode {
		int value;
		TreeNode left;
		TreeNode right;

		public TreeNode(int value) {
			super();
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String string = scanner.nextLine();
		String[] strings = string.split(",");
		if (strings == null || strings.length < 2) {
			System.out.println("-1");
		}
		TreeNode[] treeNodes = new TreeNode[strings.length];
		for (int i = strings.length - 1; i > -1; i--) {
			treeNodes[i] = new TreeNode(Integer.valueOf(strings[i].trim()));
			int left = i * 2 + 1;
			int right = i * 2 + 2;
			if (left < strings.length) {
				treeNodes[i].left = treeNodes[left];
			}
			if (right < strings.length) {
				treeNodes[i].right = treeNodes[right];
			}
		}
		int value = scanner.nextInt();
		TreeNode treeNode = treeNodes[0];
		List<Integer> list = new ArrayList<Integer>(treeNodes.length);
		solve(treeNode, list);
		int res = -1;
		for (int i = 0; i < list.size(); i++) {
			if (value == list.get(i)) {
				if (i + 1 < list.size()) {
					res = list.get(i + 1);
				}
				break;
			}
		}
		System.out.println(res);
	}

	public static void solve(TreeNode treeNode, List<Integer> list) {
		if (treeNode == null) {
			return;
		}
		solve(treeNode.left, list);
		list.add(treeNode.value);
		solve(treeNode.right, list);
	}
}
