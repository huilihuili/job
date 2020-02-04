package vip.eagleli.leetcode;

public class LeetCode25 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			sb.append(val);
			ListNode next = this.next;
			while (next != null) {
				sb.append("->" + next.val);
				next = next.next;
			}
			return sb.toString();
		}
	}

	public static void main(String[] args) {
		ListNode listNode1 = new ListNode(1);
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3 = new ListNode(3);
		ListNode listNode4 = new ListNode(4);
		ListNode listNode5 = new ListNode(5);
		ListNode listNode6 = new ListNode(6);
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode4;
		listNode4.next = listNode5;
		listNode5.next = listNode6;

		System.out.println(listNode1);
		System.out.println(new LeetCode25().reverseKGroup3(listNode1, 2));
	}

	public ListNode reverseKGroup(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}

		int i = 0;
		ListNode preNode = head, middleNode = head.next, nextNode = null;
		while (i++ < k - 1) {
			if (middleNode == null) { // 如果不够k个节点，将之前反转过的节点再重新反转过来
				while (preNode != head) {
					nextNode = preNode.next;
					preNode.next = middleNode;

					middleNode = preNode;
					preNode = nextNode;
				}
				break;
			} else {
				nextNode = middleNode.next;
				middleNode.next = preNode;

				preNode = middleNode;
				middleNode = nextNode;
			}
		}
		if (head.next != null && head.next.next == head) { // 如果反转成功
			head.next = reverseKGroup(middleNode, k);
			return preNode;
		} else {
			return head;
		}
	}

	public ListNode reverseKGroup2(ListNode head, int k) {
		if (head == null || k == 1) {
			return head;
		}

		ListNode listNode = head;
		for (int i = 0; i < k - 1; i++) {
			if (listNode.next == null) {
				return head;
			}
			listNode = listNode.next;
		}

		ListNode nextHead = listNode.next;
		listNode.next = null;
		ListNode ans = reverse(head);
		head.next = reverseKGroup(nextHead, k);
		return ans;
	}

	public ListNode reverseKGroup3(ListNode head, int k) {
		if (head == null || head.next == null || k == 1) {
			return head;
		}

		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode preHead = dummy;

		while (head != null) {
			ListNode cur = head;
			for (int i = 0; i < k - 1; i++) {
				if (cur.next == null) {
					return dummy.next;
				}
				cur = cur.next;
			}

			ListNode nextHead = cur.next;
			cur.next = null;
			preHead.next = reverse(head);
			preHead = head;

			head.next = nextHead;
			head = nextHead;
		}
		return dummy.next;
	}

	private ListNode reverse(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}
		ListNode pre = null, cur = head;
		while (cur != null) {
			ListNode nxt = cur.next;

			cur.next = pre;

			pre = cur;
			cur = nxt;
		}
		return pre;
	}

}
