package vip.eagleli.leetcode;

public class LeetCode45 {
	public static void main(String[] args) {
		LeetCode45 leetCode45 = new LeetCode45();
		int[] nums = { 2, 3, 1, 1, 4, 2, 1 };
		System.out.println(leetCode45.jump2(nums));
	}

	static class Result {
		boolean arrive;
		int step;

		public Result(boolean arrive, int step) {
			super();
			this.arrive = arrive;
			this.step = step;
		}
	}

	static Result notArriveResult = new Result(false, -1);

	int ans = Integer.MAX_VALUE;

	/**
	 * 超时
	 * 
	 * @param nums
	 * @return
	 */
	public int jump(int[] nums) {
		if (nums == null || nums.length < 2) {
			return 0;
		}
		jump(nums, 0, 0);
		return ans;
	}

	public Result jump(int[] nums, int index, int step) {
		if (index == nums.length - 1) {
			return new Result(true, step);
		}

		if (index >= nums.length) {
			return notArriveResult;
		}

		for (int i = nums[index]; i >= 1; i--) {
			Result result = jump(nums, index + i, step + 1);
			if (result.arrive) {
				ans = Integer.min(ans, result.step);
			}
		}
		return notArriveResult;
	}

	public int jump2(int[] nums) {
		int position = nums.length - 1, step = 0;
		while (position != 0) {
			for (int i = 0; i < position; i++) {
				if (nums[i] >= position - i) {
					position = i;
					step++;
					break;
				}
			}
		}
		return step;
	}

	public int jump3(int[] nums) {
		int begin = 0, end = 1, ans = 0;
		while (end < nums.length) {
			int maxPosition = 0;
			for (int i = begin; i < end; i++) {
				maxPosition = Math.max(maxPosition, i + nums[i]);
			}
			begin = end;
			end = maxPosition + 1;
			ans++;
		}
		return ans;
	}

	public int jump4(int[] nums) {
		int end = 0, step = 0, maxPosition = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			maxPosition = Math.max(maxPosition, nums[i] + i);
			if (i == end) {
				end = maxPosition;
				step++;
			}
		}
		return step;
	}

}
