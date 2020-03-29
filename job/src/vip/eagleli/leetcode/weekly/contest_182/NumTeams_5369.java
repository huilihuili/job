package vip.eagleli.leetcode.weekly.contest_182;

public class NumTeams_5369 {

	public int numTeams(int[] rating) {
		int ans = 0;
		for (int i = 0; i < rating.length; i++) {
			for (int j = i + 1; j < rating.length; j++) {
				for (int k = j + 1; k < rating.length; k++) {
					if ((rating[i] < rating[j] && rating[j] < rating[k])
							|| (rating[i] > rating[j] && rating[j] > rating[k])) {
						ans++;
					}
				}
			}
		}
		return ans;
	}

}
