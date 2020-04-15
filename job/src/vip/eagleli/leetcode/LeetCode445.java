package vip.eagleli.leetcode;

import java.util.LinkedList;

public class LeetCode445 {

	public class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode ans = null;
		LinkedList<Integer> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
		while (l1 != null) {
			stack1.push(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {
			stack2.push(l2.val);
			l2 = l2.next;
		}
		int carry = 0;
		while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
			int val1 = stack1.isEmpty() ? 0 : stack1.pop();
			int val2 = stack2.isEmpty() ? 0 : stack2.pop();
			int sum = val1 + val2 + carry;
			ListNode listNode = new ListNode(sum % 10);
			if (ans == null) {
				ans = listNode;
			} else {
				listNode.next = ans;
				ans = listNode;
			}
			carry = sum / 10;
		}
		return ans;
	}

	public ListNode addTwoNumbers_(ListNode l1, ListNode l2) {
		ListNode ans = null;
		LinkedList<Integer> stack1 = new LinkedList<>(), stack2 = new LinkedList<>();
		while (l1 != null) {
			stack1.push(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {
			stack2.push(l2.val);
			l2 = l2.next;
		}
		int carry = 0;
		while (!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
			int sum = carry;
			sum += stack1.isEmpty() ? 0 : stack1.pop();
			sum += stack2.isEmpty() ? 0 : stack2.pop();
			ListNode node = new ListNode(sum % 10);
			node.next = ans;
			ans = node;
			carry = sum / 10;
		}
		return ans;
	}

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode ans = null;
		l1 = reverseList(l1);
		l2 = reverseList(l2);
		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int val1 = l1 == null ? 0 : l1.val;
			int val2 = l2 == null ? 0 : l2.val;
			int sum = val1 + val2 + carry;
			ListNode node = new ListNode(sum % 10);
			node.next = ans;
			ans = node;
			carry = sum / 10;

			l1 = l1 == null ? null : l1.next;
			l2 = l2 == null ? null : l2.next;
		}
		return ans;
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
