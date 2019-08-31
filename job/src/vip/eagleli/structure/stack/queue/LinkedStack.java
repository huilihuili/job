package vip.eagleli.structure.stack.queue;

import java.util.EmptyStackException;

public class LinkedStack {
	private static class Node {
		int v;
		Node next;

		Node(int v) {
			this.v = v;
		}
	}

	Node root;

	public void push(int i) {
		Node node = new Node(i);
		node.next = root;
		root = node;
	}

	public int pop() {
		if (root == null) {
			throw new EmptyStackException();
		}
		Node node = root;
		root = root.next;
		return node.v;
	}

	public int peek() {
		if (root == null) {
			throw new EmptyStackException();
		}
		return root.v;
	}
}
