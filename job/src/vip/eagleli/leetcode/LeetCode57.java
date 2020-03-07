package vip.eagleli.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class LeetCode57 {
	public static void main(String[] args) {
		LeetCode57 leetCode57 = new LeetCode57();
		int[][] ans = leetCode57.findContinuousSequence2(9);
		Arrays.stream(ans).forEach((is) -> {
			System.out.println(Arrays.toString(is));
		});
	}

	public int[][] findContinuousSequence(int target) {
		List<int[]> list = new ArrayList<>();
		for (int i = 1; i <= target / 2; i++) {
			int[] middleAns = findContinuousSequence(i, target);
			if (middleAns != null) {
				list.add(middleAns);
			}
		}
		return list.toArray(new int[list.size()][]);
	}

	public int[] findContinuousSequence(int start, int target) {
		int sum = 0, end = start;
		while (end <= target) {
			sum += end;
			if (sum == target) {
				break;
			}
			if (sum > target) {
				return null;
			}
			end++;
		}
		return getMiddleAns(start, end);
	}

	/**
	 * (x + y) * (y − x + 1) / 2 = target
	 * y^2 + y - x^2 + x - 2 * target = 0
	 * 这是一个关于y的一元二次方程，其中a = 1, b = 1, c = -x^2 + x - 2 * target
	 * 直接套用求根公式
	 * 
	 * 判别式 b^2 - 4ac 开根号需要为整数
	 * 最后的求根公式的分子需要为偶数，因为分母为 2
	 * 
	 * y = ( -b +/- sqrt(b^2 - 4ac) ) / 2a
	 * 
	 * @param target
	 * @return
	 */
	public int[][] findContinuousSequence2(int target) {
		List<int[]> list = new ArrayList<>();
		int limit = (target - 1) / 2;
		for (int x = 1; x <= limit; ++x) {
			long delta = 1 - 4 * (x - 1L * x * x - 2 * target);
			if (delta < 0) {
				continue;
			}
			int deltaSqrt = (int) Math.sqrt(delta);
			if (1L * deltaSqrt * deltaSqrt == delta && (deltaSqrt - 1) % 2 == 0) {
				int y = (-1 + deltaSqrt) / 2;
				list.add(getMiddleAns(x, y));
			}
		}
		return list.toArray(new int[list.size()][]);
	}

	public int[][] findContinuousSequence3(int target) {
		List<int[]> list = new ArrayList<>();
		for (int l = 1, r = 2; l < r;) {
			int sum = (l + r) * (r - l + 1) / 2;
			if (sum == target) {
				list.add(getMiddleAns(l, r));
				l++;
			} else if (sum < target) {
				r++;
			} else {
				l++;
			}
		}
		return list.toArray(new int[list.size()][]);
	}

	private int[] getMiddleAns(int start, int end) {
		int[] res = new int[end - start + 1];
		for (int i = start; i <= end; i++) {
			res[i - start] = i;
		}
		return res;
	}

	public int[][] findContinuousSequence4(int target) {
		List<int[]> result = new ArrayList<>();
		int i = 1;
		while (target > 0) {
			target -= i++;
			if (target > 0 && target % i == 0) {
				int[] array = new int[i];
				for (int k = target / i, j = 0; k < target / i + i; k++, j++) {
					array[j] = k;
				}
				result.add(array);
			}
		}
		Collections.reverse(result);
		return result.toArray(new int[result.size()][]);
	}
}
