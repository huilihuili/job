package vip.eagleli.leetcode;

public class LeetCode21 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		ListNode ans = new ListNode(0);
		ListNode curr = ans;
		while (l1 != null || l2 != null) {
			if (l1 != null && l2 != null) {
				if (l1.val < l2.val) {
					curr.next = l1;
					l1 = l1.next;
				} else {
					curr.next = l2;
					l2 = l2.next;
				}
			} else if (l1 == null) {
				curr.next = l2;
				l2 = l2.next;
			} else {
				curr.next = l1;
				l1 = l1.next;
			}
			curr = curr.next;
		}
		return ans.next;
	}

	/**
	 * 递归
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
		if (l1 == null) {
			return l2;
		}

		if (l2 == null) {
			return l1;
		}

		if (l1.val < l2.val) {
			l1.next = mergeTwoLists2(l1.next, l2);
			return l1;
		}

		l2.next = mergeTwoLists2(l1, l2.next);
		return l2;
	}

	/**
	 * 迭代
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode mergeTwoLists3(ListNode l1, ListNode l2) {
		ListNode prehead = new ListNode(-1);
		ListNode prev = prehead;
		while (l1 != null && l2 != null) {
			if (l1.val <= l2.val) {
				prev.next = l1;
				l1 = l1.next;
			} else {
				prev.next = l2;
				l2 = l2.next;
			}
			prev = prev.next;
		}
		prev.next = l1 == null ? l2 : l1;
		return prehead.next;
	}

}
