package vip.eagleli.leetcode.weekly.contest_185;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class DisplayTable_5389 {
	public static void main(String[] args) {
		DisplayTable_5389 displayTable_5389 = new DisplayTable_5389();
		List<List<String>> orders = new ArrayList<>();
		displayTable_5389.displayTable(orders);
	}

	public List<List<String>> displayTable(List<List<String>> orders) {
		TreeSet<Integer> tableSet = new TreeSet<>();
		TreeSet<String> menuSet = new TreeSet<>();
		Map<Integer, Map<String, Integer>> map = new HashMap<>();
		for (List<String> list : orders) {
			// String name = list.get(0);
			int table = Integer.valueOf(list.get(1));
			String menu = list.get(2);
			tableSet.add(table);
			menuSet.add(menu);
			Map<String, Integer> tableMap = map.getOrDefault(table, new HashMap<>());
			tableMap.put(menu, tableMap.getOrDefault(menu, 0) + 1);
			map.put(table, tableMap);
		}
		List<List<String>> ans = new ArrayList<>(tableSet.size() + 1);

		List<String> firstRow = new ArrayList<>(menuSet.size() + 1);
		firstRow.add("Table");
		firstRow.addAll(menuSet);
		ans.add(firstRow);

		for (Integer integer : tableSet) {
			List<String> otherRow = new ArrayList<>(menuSet.size() + 1);
			otherRow.add(String.valueOf(integer));
			for (String string : menuSet) {
				otherRow.add(String.valueOf(map.getOrDefault(integer, new HashMap<>()).getOrDefault(string, 0)));
			}
			ans.add(otherRow);
		}
		return ans;
	}
}
