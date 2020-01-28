package vip.eagleli.leetcode;

public class LeetCode19 {
	public static void main(String[] args) {
	}

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode removeNthFromEnd(ListNode head, int n) {
		if (head == null || n < 1) {
			return head;
		}

		ListNode fastNode = head, slowNode = head, preNode = head;
		for (int i = 0; i < n; i++) {
			if (fastNode == null) {
				return head;
			}
			fastNode = fastNode.next;
		}

		while (fastNode != null) {
			fastNode = fastNode.next;
			preNode = slowNode;
			slowNode = slowNode.next;
		}
		if (slowNode == head) {
			return head.next;
		}
		preNode.next = slowNode.next;
		return head;
	}

	public ListNode removeNthFromEnd2(ListNode head, int n) {
		if (head == null || n < 1) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		int length = 0;
		ListNode curr = head;
		while (curr != null) {
			length++;
			curr = curr.next;
		}

		length -= n;
		curr = dummy;
		while (length-- > 0) {
			curr = curr.next;
		}
		curr.next = curr.next.next;
		return dummy.next;
	}

	public ListNode removeNthFromEnd3(ListNode head, int n) {
		if (head == null || n < 1) {
			return head;
		}

		ListNode dummy = new ListNode(0);
		dummy.next = head;

		ListNode fast = dummy, slow = dummy;
		for (int i = 1; i <= n + 1; i++) {
			if (fast == null) {
				return head;
			}
			fast = fast.next;
		}

		while (fast != null) {
			fast = fast.next;
			slow = slow.next;
		}
		slow.next = slow.next.next;
		return dummy.next;
	}

}
