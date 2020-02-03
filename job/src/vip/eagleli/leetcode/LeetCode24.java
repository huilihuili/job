package vip.eagleli.leetcode;

public class LeetCode24 {
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
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		listNode3.next = listNode4;
		listNode4.next = listNode5;

		System.out.println(listNode1);
		new LeetCode24().swapPairs2(listNode1);
	}

	public ListNode swapPairs(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode ans = head.next;
		head.next = swapPairs(head.next.next);
		ans.next = head;
		return ans;
	}

	public ListNode swapPairs2(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode headCurr1 = head, head1 = head, headCurr2 = head.next, head2 = head.next;
		while (headCurr1 != null && headCurr2 != null) {
			headCurr1.next = headCurr1.next.next;
			headCurr1 = headCurr1.next;

			if (headCurr2.next != null) {
				headCurr2.next = headCurr2.next.next;
				headCurr2 = headCurr2.next;
			}
		}
		ListNode ans = new ListNode(-1);
		ListNode curr = ans;
		while (head1 != null && head2 != null) {
			curr.next = head2;
			curr = curr.next;
			head2 = head2.next;

			curr.next = head1;
			curr = curr.next;
			head1 = head1.next;
		}
		curr.next = head1;
		return ans.next;
	}

	/**
	 * 官方答案
	 * 
	 * @param head
	 * @return
	 */
	public ListNode swapPairs3(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode prevNode = dummy;

		while ((head != null) && (head.next != null)) {

			// Nodes to be swapped
			ListNode firstNode = head;
			ListNode secondNode = head.next;

			// Swapping
			prevNode.next = secondNode;
			firstNode.next = secondNode.next;
			secondNode.next = firstNode;

			// Reinitializing the head and prevNode for next swap
			prevNode = firstNode;
			head = firstNode.next; // jump
		}

		// Return the new head node.
		return dummy.next;
	}

}
