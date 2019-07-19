package vip.eagleli.structure.tree;

import java.util.Stack;

public class InTraversal {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void inOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		inOrderRecur(head.left);
		System.out.print(head.value + " ");
		inOrderRecur(head.right);
	}

	public static void inOrderUnRecur(Node head) {
		System.out.print("in-order: ");
		if (head != null) {
			Node cur = head;
			Stack<Node> stack = new Stack<>();
			while (!stack.isEmpty() || cur != null) {
				if (cur != null) {
					stack.push(cur);
					cur = cur.left;
				} else {
					cur = stack.pop();
					System.out.print(cur.value + " ");
					cur = cur.right;
				}
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Node head = new Node(5);
		head.left = new Node(3);
		head.right = new Node(8);
		head.left.left = new Node(2);
		head.left.right = new Node(4);
		head.left.left.left = new Node(1);
		head.right.left = new Node(7);
		head.right.left.left = new Node(6);
		head.right.right = new Node(10);
		head.right.right.left = new Node(9);
		head.right.right.right = new Node(11);

		// recursive
		System.out.println("==============recursive==============");
		System.out.print("in-order: ");
		inOrderRecur(head);
		System.out.println("\n");

		// unrecursive
		System.out.println("============unrecursive=============");
		inOrderUnRecur(head);
	}

}
