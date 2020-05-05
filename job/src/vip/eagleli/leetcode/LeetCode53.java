package vip.eagleli.leetcode;

public class LeetCode53 {
	public static void main(String[] args) {
		LeetCode53 leetCode53 = new LeetCode53();
		int[] nums = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		System.out.println(leetCode53.maxSubArray(nums));
	}

	public int maxSubArray(int[] nums) {
		if (nums == null || nums.length < 1) {
			return 0;
		}

		int ans = nums[0], cur = nums[0];
		for (int i = 1; i < nums.length; i++) {
			cur = Math.max(nums[i], nums[i] + cur);
			ans = Math.max(ans, cur);
		}
		return ans;
	}

	public int maxSubArray2(int[] nums) {
		if (nums == null || nums.length < 1) {
			return 0;
		}
		return get(nums, 0, nums.length - 1).mSum;
	}

	Status get(int[] a, int l, int r) {
		if (l == r) {
			return new Status(a[l], a[l], a[l], a[l]);
		}
		int m = (l + r) >> 1;
		Status lSub = get(a, l, m);
		Status rSub = get(a, m + 1, r);
		return pushUp(lSub, rSub);
	}

	Status pushUp(Status l, Status r) {
		int iSum = l.iSum + r.iSum;
		int lSum = Math.max(l.lSum, l.iSum + r.lSum);
		int rSum = Math.max(r.rSum, r.iSum + l.rSum);
		int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
		return new Status(lSum, rSum, mSum, iSum);
	};

	static class Status {
		int lSum, rSum, mSum, iSum;
		public Status(int lSum, int rSum, int mSum, int iSum) {
			super();
			this.lSum = lSum;
			this.rSum = rSum;
			this.mSum = mSum;
			this.iSum = iSum;
		}
	}

}
