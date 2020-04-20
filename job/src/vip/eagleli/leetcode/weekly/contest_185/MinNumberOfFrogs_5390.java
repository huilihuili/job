package vip.eagleli.leetcode.weekly.contest_185;

import java.util.Iterator;
import java.util.LinkedList;

public class MinNumberOfFrogs_5390 {

	public static void main(String[] args) {
		MinNumberOfFrogs_5390 reformat5388 = new MinNumberOfFrogs_5390();
		System.out.println(reformat5388.reformat("a0b1c2"));
	}

	public String reformat(String s) {
		if (s == null || s.length() < 2) {
			return s;
		}
		LinkedList<Character> charList = new LinkedList<>();
		LinkedList<Character> intList = new LinkedList<>();
		for (char c : s.toCharArray()) {
			if ('0' <= c && c <= '9') {
				intList.add(c);
			} else {
				charList.add(c);
			}
		}
		if (Math.abs(charList.size() - intList.size()) > 1) {
			return "";
		}
		return build(charList, intList);
	}

	private String build(LinkedList<Character> linkedList1, LinkedList<Character> linkedList2) {
		if (linkedList1.size() < linkedList2.size()) {
			return build(linkedList2, linkedList1);
		}
		Iterator<Character> iterator1 = linkedList1.iterator();
		Iterator<Character> iterator2 = linkedList2.iterator();
		StringBuilder stringBuilder = new StringBuilder();
		if (linkedList1.size() > linkedList2.size()) {
			stringBuilder.append(iterator1.next());
		}
		while (iterator2.hasNext()) {
			stringBuilder.append(iterator2.next());
			stringBuilder.append(iterator1.next());
		}
		return stringBuilder.toString();
	}
}
