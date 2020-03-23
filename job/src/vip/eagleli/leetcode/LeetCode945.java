package vip.eagleli.leetcode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LeetCode945 {

	/**
	 * 超时
	 * 
	 * @param A
	 * @return
	 */
	public int minIncrementForUnique(int[] A) {
		int ans = 0;
		Set<Integer> set = new HashSet<>();
		for (int i = 0; i < A.length; i++) {
			while (set.contains(A[i])) {
				A[i]++;
				ans++;
			}
			set.add(A[i]);
		}
		return ans;
	}

	public int minIncrementForUnique2(int[] A) {
		int ans = 0, taken = 0;
		int[] count = new int[80000];
		for (int x : A) {
			count[x]++;
		}
		for (int i = 0; i < count.length; i++) {
			if (count[i] > 1) {
				taken += count[i] - 1;
				ans -= (count[i] - 1) * i;
			} else if (taken > 0 && count[i] == 0) {
				taken -= 1;
				ans += i;
			}
		}
		return ans;
	}

	public int minIncrementForUnique2_(int[] A) {
		// counter数组统计每个数字的个数。
		// （这里为了防止下面遍历counter的时候每次都走到40000，所以设置了一个max，这个数据量不设也行，再额外设置min也行）
		int[] counter = new int[40000];
		int max = -1;
		for (int num : A) {
			counter[num]++;
			max = Math.max(max, num);
		}

		// 遍历counter数组，若当前数字的个数cnt大于1个，则只留下1个，其他的cnt-1个后移
		int move = 0;
		for (int num = 0; num <= max; num++) {
			if (counter[num] > 1) {
				int d = counter[num] - 1;
				move += d;
				counter[num + 1] += d;
			}
		}
		// 最后, counter[max+1]里可能会有从counter[max]后移过来的，counter[max+1]里只留下1个，其它的d个后移。
		// 设 max+1 = x，那么后面的d个数就是[x+1,x+2,x+3,...,x+d],
		// 因此操作次数是[1,2,3,...,d],用求和公式求和。
		int d = counter[max + 1] - 1;
		move += (1 + d) * d / 2;
		return move;
	}

	public int minIncrementForUnique3(int[] A) {
		int ans = 0;
		Arrays.sort(A);
		for (int i = 1; i < A.length; i++) {
			if (A[i - 1] >= A[i]) {
				int pre = A[i];
				A[i] = A[i - 1] + 1;
				ans += A[i] - pre;
			}
		}
		return ans;
	}

	int[] pos = new int[80000];

	public int minIncrementForUnique4(int[] A) {
		Arrays.fill(pos, -1); // -1表示空位
		int move = 0;
		// 遍历每个数字a对其寻地址得到位置b, b比a的增量就是操作数。
		for (int a : A) {
			int b = findPos(a);
			move += b - a;
		}
		return move;
	}

	// 线性探测寻址（含路径压缩）
	private int findPos(int a) {
		int b = pos[a];
		// 如果a对应的位置pos[a]是空位，直接放入即可。
		if (b == -1) {
			pos[a] = a;
			return a;
		}
		// 否则向后寻址
		// 因为pos[a]中标记了上次寻址得到的空位，因此从pos[a]+1开始寻址就行了（不需要从a+1开始）。
		b = findPos(b + 1);
		pos[a] = b; // 寻址后的新空位要重新赋值给pos[a]哦，路径压缩就是体现在这里。
		return b;
	}

}
