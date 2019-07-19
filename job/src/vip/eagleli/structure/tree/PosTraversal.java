package vip.eagleli.structure.tree;

import java.util.Deque;
import java.util.LinkedList;

public class PosTraversal {

	public static class Node {
		public int value;
		public Node left;
		public Node right;

		public Node(int data) {
			this.value = data;
		}
	}

	public static void posOrderRecur(Node head) {
		if (head == null) {
			return;
		}
		posOrderRecur(head.left);
		posOrderRecur(head.right);
		System.out.print(head.value + " ");
	}

	public static void posOrderUnRecur(Node head) {
		System.out.print("pos-order: ");
		if (head == null) {
			System.out.println();
			return;
		}
		Deque<Node> stack1 = new LinkedList<>();
		Deque<Node> stack2 = new LinkedList<>();
		stack1.push(head);
		while (!stack1.isEmpty()) {
			Node cur = stack1.pop();
			stack2.push(cur);
			if (cur.left != null) {
				stack1.push(cur.left);
			}
			if (cur.right != null) {
				stack1.push(cur.right);
			}
		}
		while (!stack2.isEmpty()) {
			System.out.print(stack2.pop().value + " ");
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
		System.out.print("pos-order: ");
		posOrderRecur(head);
		System.out.println("\n");

		// unrecursive
		System.out.println("============unrecursive=============");
		posOrderUnRecur(head);

	}

}
