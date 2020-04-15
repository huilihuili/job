package vip.eagleli.leetcode.mian.shi.ti;

public class P24 {
	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = null;
		while (head != null) {
			ListNode next = head.next;
			head.next = pre;

			pre = head;
			head = next;
		}
		return pre;
	}
}
