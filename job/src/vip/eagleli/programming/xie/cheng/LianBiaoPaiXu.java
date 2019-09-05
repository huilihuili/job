package vip.eagleli.programming.xie.cheng;

import java.util.LinkedList;
import java.util.Scanner;

public class LianBiaoPaiXu {

	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		ListNode head = null;
		ListNode node = null;
		int m = in.nextInt();
		while (in.hasNextInt()) {
			int v = in.nextInt();
			if (head == null) {
				node = new ListNode(v);
				head = node;
			} else {
				node.next = new ListNode(v);
				node = node.next;
			}
		}
		head = partition(head, m);
		if (head != null) {
			System.out.print(head.val);
			head = head.next;
			while (head != null) {
				System.out.print(",");
				System.out.print(head.val);
				head = head.next;
			}
		}
		System.out.println();
	}

	static ListNode partition(ListNode head, int m) {
		LinkedList<Integer> queue1 = new LinkedList<>();
		LinkedList<Integer> queue2 = new LinkedList<>();
		ListNode node = head;
		while (node != null) {
			if (node.val <= m) {
				queue1.offer(node.val);
			} else {
				queue2.offer(node.val);
			}
			node = node.next;
		}
		node = head;
		while (!queue1.isEmpty()) {
			node.val = queue1.poll();
			node = node.next;
		}
		while (!queue2.isEmpty()) {
			node.val = queue2.poll();
			node = node.next;
		}
		return head;
	}
}
