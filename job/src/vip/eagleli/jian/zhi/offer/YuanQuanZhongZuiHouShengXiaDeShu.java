package vip.eagleli.jian.zhi.offer;

import java.util.Iterator;
import java.util.LinkedList;

public class YuanQuanZhongZuiHouShengXiaDeShu {
	public static void main(String[] args) {
		System.out.println(lastRemaining(5, 3));
	}

	public static int lastRemaining(int n, int m) {
		LinkedList<Integer> l = new LinkedList<>();
		for (int i = 0; i < n; i++) {
			l.add(i);
		}

		Iterator<Integer> iterator = l.iterator();
		while (l.size() != 1) {
			for (int i = 0; i < m; i++) {
				if (!iterator.hasNext()) {
					iterator = l.iterator();
				}
				iterator.next();
			}
			iterator.remove();
		}
		return l.iterator().next();
	}
}
