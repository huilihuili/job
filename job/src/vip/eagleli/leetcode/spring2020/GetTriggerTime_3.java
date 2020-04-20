package vip.eagleli.leetcode.spring2020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class GetTriggerTime_3 {
	static class Node {
		int index;
		int value;

		public Node(int index, int value) {
			super();
			this.index = index;
			this.value = value;
		}
	}

	public static void main(String[] args) {
		int[][] increase = { { 0, 4, 5 }, { 4, 8, 8 }, { 8, 6, 1 }, { 10, 10, 0 } };
		int[][] requirements = { { 0, 1, 6 }, { 12, 11, 16 }, { 20, 2, 6 }, { 9, 2, 6 }, { 10, 18, 3 }, { 8, 14, 9 } };
		GetTriggerTime_3 getTriggerTime_3 = new GetTriggerTime_3();
		System.out.println(Arrays.toString(getTriggerTime_3.getTriggerTime(increase, requirements)));
	}

	public int[] getTriggerTime(int[][] increase, int[][] requirements) {
		ArrayList<TreeSet<Node>> arrayList = new ArrayList<>();
		for (int i = 0; i < 3; i++) {
			arrayList.add(new TreeSet<>((n1, n2) -> n1.value != n2.value ? n1.value - n2.value : n1.index - n2.index));
		}
		for (int i = 0; i < increase.length; i++) {
			for (int j = 0; j < 3; j++) {
				if (i != 0) {
					increase[i][j] += increase[i - 1][j];
				}
				arrayList.get(j).add(new Node(i + 1, increase[i][j]));
			}
		}
		int[] ans = new int[requirements.length];
		for (int i = 0; i < requirements.length; i++) {
			ans[i] = find(arrayList, requirements[i]);
		}
		return ans;
	}

	private int find(ArrayList<TreeSet<Node>> arrayList, int[] requirement) {
		if (requirement[0] == 0 && requirement[1] == 0 && requirement[2] == 0) {
			return 0;
		}
		int ans = -1;
		for (int i = 0; i < 3; i++) {
			Node node = new Node(0, requirement[i]);
			Node ceiling = arrayList.get(i).ceiling(node);
			if (ceiling == null) {
				return -1;
			}
			ans = Math.max(ans, ceiling.index);
		}
		return ans;
	}
}
