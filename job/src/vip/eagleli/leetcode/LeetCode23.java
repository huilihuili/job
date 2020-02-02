package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class LeetCode23 {
	public static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}
	}

	public static void main(String[] args) {
		// [[1,4,5],[1,3,4],[2,6]]
		ListNode listNode11 = new ListNode(1);
		ListNode listNode12 = new ListNode(4);
		ListNode listNode13 = new ListNode(5);
		listNode11.next = listNode12;
		listNode12.next = listNode13;

		ListNode listNode21 = new ListNode(1);
		ListNode listNode22 = new ListNode(3);
		ListNode listNode23 = new ListNode(4);
		listNode21.next = listNode22;
		listNode22.next = listNode23;

		ListNode listNode31 = new ListNode(2);
		ListNode listNode32 = new ListNode(6);
		listNode31.next = listNode32;

		new LeetCode23().mergeKLists(new ListNode[] { listNode11, listNode21, listNode31 });
	}

	public ListNode mergeKLists(ListNode[] lists) {
		ListNode prehead = new ListNode(-1);
		ListNode prev = prehead;
		int listSize = 0;
		for (ListNode listNode : lists) {
			if (listNode != null) {
				listSize++;
			}
		}
		if (listSize == 0) {
			return null;
		}
		while (listSize > 0) {
			int index = next(lists);
			prev.next = lists[index];
			if (listSize == 1) {
				break;
			}
			prev = prev.next;
			lists[index] = lists[index].next;
			if (lists[index] == null) {
				listSize--;
			}
		}
		return prehead.next;
	}

	private int next(ListNode[] listNodes) {
		ListNode next = null;
		int index = -1;
		for (int i = 0; i < listNodes.length; i++) {
			if (listNodes[i] != null && (next == null || listNodes[i].val < next.val)) {
				next = listNodes[i];
				index = i;
			}
		}
		return index;
	}

	/**
	 * 暴力
	 * 
	 * 遍历所有链表，将所有节点的值放到一个数组中。 
	 * 将这个数组排序，然后遍历所有元素得到正确顺序的值。 
	 * 用遍历得到的值，创建一个新的有序链表。
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists2(ListNode[] lists) {
		ArrayList<Integer> arrayList = new ArrayList<>();
		for (ListNode listNode : lists) {
			while (listNode != null) {
				arrayList.add(listNode.val);
				listNode = listNode.next;
			}
		}
		Collections.sort(arrayList);
		ListNode head = new ListNode(-1);
		ListNode point = head;
		for (Integer integer : arrayList) {
			point.next = new ListNode(integer);
			point = point.next;
		}
		return head.next;
	}

	/**
	 * 每个节点使用优先队列比较
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists3(ListNode[] lists) {
		ListNode head = new ListNode(-1);
		ListNode point = head;

		PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>((l1, l2) -> {
			return l1.val - l2.val;
		});
		for (ListNode listNode : lists) {
			if (listNode != null) {
				priorityQueue.offer(listNode);
			}
		}

		while (!priorityQueue.isEmpty()) {
			ListNode listNode = priorityQueue.poll();
			point.next = listNode;
			point = point.next;

			if (listNode.next != null) {
				priorityQueue.offer(listNode.next);
			}
		}
		return head.next;
	}

	/**
	 * 基于合并两个链表
	 * 取第一个链表与第二个链表合并
	 * 然后将合并的链表再与第三个链表合并，依次类型。
	
	 * @param lists
	 * @return
	 */

	public ListNode mergeKLists4(ListNode[] lists) {
		if (lists == null || lists.length < 1) {
			return null;
		}
		ListNode listNode = lists[0];
		for (int i = 1; i < lists.length; i++) {
			listNode = mergeTwoLists(listNode, lists[i]);
		}
		return listNode;
	}

	/**
	 * 将k个链表放入队列，取出两个进行合并，合并后在放入队尾
	 * 直到只有队列中只有一个元素。
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists5(ListNode[] lists) {
		if (lists == null || lists.length < 1) {
			return null;
		}
		Queue<ListNode> queue = new LinkedList<>();
		for (ListNode listNode : lists) {
			if (listNode != null) {
				queue.offer(listNode);
			}
		}
		while (queue.size() > 1) {
			queue.offer(mergeTwoLists(queue.poll(), queue.poll()));
		}
		return queue.poll();
	}

	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
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

	/**
	 * 纯递归
	 * 
	 * @param lists
	 * @return
	 */
	public ListNode mergeKLists6(ListNode[] lists) {
		if (lists == null || lists.length < 1) {
			return null;
		}
		return solve(lists, 0, lists.length - 1);
	}

	private ListNode solve(ListNode[] arr, int left, int right) {
		if (left == right) {
			return arr[left];
		}
		int mid = (left + right) >> 1;
		ListNode lNode = solve(arr, left, mid);
		ListNode rNode = solve(arr, mid + 1, right);
		return merge(lNode, rNode);
	}

	private ListNode merge(ListNode node1, ListNode node2) {
		if (node1 == null) {
			return node2;
		}
		if (node2 == null) {
			return node1;
		}
		if (node1.val < node2.val) {
			node1.next = merge(node1.next, node2);
			return node1;
		} else {
			node2.next = merge(node1, node2.next);
			return node2;
		}
	}

}
