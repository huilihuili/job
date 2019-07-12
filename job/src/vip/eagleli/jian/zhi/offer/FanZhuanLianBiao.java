package vip.eagleli.jian.zhi.offer;

public class FanZhuanLianBiao {

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
		ListNode listNode2 = new ListNode(2);
		ListNode listNode3 = new ListNode(3);
		listNode1.next = listNode2;
		listNode2.next = listNode3;
		System.out.println(reverseList(listNode1));
	}

	public static ListNode reverseList(ListNode head) {
		if (head == null || head.next == null) {
			return head;
		}

		ListNode pre = head, post = head.next;
		while (post != null) {
			ListNode nextTmp = post.next;
			post.next = pre;
			if (pre == head) {
				pre.next = null;
			}
			pre = post;
			post = nextTmp;
		}
		return pre;
	}
}
