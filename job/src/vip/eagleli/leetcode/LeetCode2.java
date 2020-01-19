package vip.eagleli.leetcode;

import java.util.LinkedList;

public class LeetCode2 {

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	/**
	 * 鄙人写的
	 * 
	 * 我好像理解题意了
	 * 
	 * 我先用的栈，后面发现不对，改成了队列
	 * 
	 * 看了官方答案后，用队列和不用队列不是一样的效果嘛
	 * 
	 * 哈哈哈
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
		LinkedList<Integer> queue1 = new LinkedList<>();
		LinkedList<Integer> queue2 = new LinkedList<>();
		while (l1 != null) {
			queue1.offer(l1.val);
			l1 = l1.next;
		}
		while (l2 != null) {
			queue2.offer(l2.val);
			l2 = l2.next;
		}

		ListNode res = null, head = null;
		int j = 0;
		while (!queue1.isEmpty() && !queue2.isEmpty()) {
			int sum = queue1.poll() + queue2.poll() + j;
			ListNode listNode = new ListNode(sum % 10);
			j = sum / 10;

			if (res == null) {
				res = listNode;
				head = listNode;
			} else {
				res.next = listNode;
				res = listNode;
			}
		}

		while (!queue1.isEmpty()) {
			int sum = queue1.poll() + j;
			ListNode listNode = new ListNode(sum % 10);
			j = sum / 10;

			if (res == null) {
				res = listNode;
				head = listNode;
			} else {
				res.next = listNode;
				res = listNode;
			}
		}

		while (!queue2.isEmpty()) {
			int sum = queue2.poll() + j;
			ListNode listNode = new ListNode(sum % 10);
			j = sum / 10;

			if (res == null) {
				res = listNode;
				head = listNode;
			} else {
				res.next = listNode;
				res = listNode;
			}
		}

		if (j > 0) {
			ListNode listNode = new ListNode(j);
			res.next = listNode;
		}
		return head;
	}

	/**
	 * 鄙人写的
	 * 
	 * 不用队列写的
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */

	public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
		ListNode cur = null, res = null;
		int j = 0;
		while (l1 != null && l2 != null) {
			int sum = l1.val + l2.val + j;
			ListNode listNode = new ListNode(sum % 10);
			j = sum / 10;
			if (res == null) {
				res = listNode;
				cur = listNode;
			} else {
				cur.next = listNode;
				cur = listNode;
			}
			l1 = l1.next;
			l2 = l2.next;
		}

		while (l1 != null) {
			int sum = l1.val + j;
			ListNode listNode = new ListNode(sum % 10);
			j = sum / 10;
			cur.next = listNode;
			cur = listNode;
			l1 = l1.next;
		}
		while (l2 != null) {
			int sum = l2.val + j;
			ListNode listNode = new ListNode(sum % 10);
			j = sum / 10;
			cur.next = listNode;
			cur = listNode;
			l2 = l2.next;
		}
		if (j > 0) {
			ListNode listNode = new ListNode(j);
			cur.next = listNode;
		}
		return res;
	}

	/**
	 * 官方的答案
	 * 
	 * 比我写的代码少多了
	 * 
	 * 第一个点：
	 * 我定义了两个ListNode，一个用于返回，另一个用于指向当前 
	 * 官方多加了一个头指针 头指针的next就是返回结果
	 * 
	 * 第二个点：
	 * 将我的三个遍历浓缩为一个遍历
	 * 
	 * @param l1
	 * @param l2
	 * @return
	 */
	public ListNode addTwoNumbers3(ListNode l1, ListNode l2) {
		ListNode dummyHead = new ListNode(0);
		ListNode p = l1, q = l2, curr = dummyHead;
		int carry = 0;
		while (p != null || q != null) {
			int x = (p != null) ? p.val : 0;
			int y = (q != null) ? q.val : 0;
			int sum = x + y + carry;

			carry = sum / 10;
			curr.next = new ListNode(sum % 10);
			curr = curr.next;

			if (p != null) {
				p = p.next;
			}
			if (q != null) {
				q = q.next;
			}
		}

		if (carry > 0) {
			curr.next = new ListNode(carry);
		}
		return dummyHead.next;
	}

}
