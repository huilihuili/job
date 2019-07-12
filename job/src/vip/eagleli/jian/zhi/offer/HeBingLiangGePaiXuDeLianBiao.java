package vip.eagleli.jian.zhi.offer;

public class HeBingLiangGePaiXuDeLianBiao {
	private static class ListNode {
		int val;
		ListNode next = null;

		ListNode(int val) {
			this.val = val;
		}

		@Override
		public String toString() {
			ListNode head = this;
			StringBuilder stringBuilder = new StringBuilder();
			while (head != null) {
				stringBuilder.append(head.val + "-->");
				head = head.next;
			}
			return stringBuilder.toString();
		}
	}

	public static void main(String[] args) {
		ListNode listNode1 = new ListNode(1);
		ListNode listNode2 = new ListNode(3);
		ListNode listNode3 = new ListNode(5);
		listNode1.next = listNode2;
		listNode2.next = listNode3;

		ListNode listNode4 = new ListNode(2);
		ListNode listNode5 = new ListNode(4);
		ListNode listNode6 = new ListNode(6);
		listNode4.next = listNode5;
		listNode5.next = listNode6;
		System.out.println(merge(listNode1, listNode4));
	}

	public static ListNode merge(ListNode list1, ListNode list2) {
		if (list1 == null) {
			return list2;
		}
		if (list2 == null) {
			return list1;
		}
		ListNode res;
		if (list1.val < list2.val) {
			res = list1;
			list1 = list1.next;
		} else {
			res = list2;
			list2 = list2.next;
		}
		ListNode head = res;
		while (list1 != null && list2 != null) {
			if (list1.val < list2.val) {
				head.next = list1;
				list1 = list1.next;
			} else {
				head.next = list2;
				list2 = list2.next;
			}
			head = head.next;
		}
		if (list1 != null) {
			head.next = list1;
		}
		if (list2 != null) {
			head.next = list2;
		}
		return res;
	}
}
