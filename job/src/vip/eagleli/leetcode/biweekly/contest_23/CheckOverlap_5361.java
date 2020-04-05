package vip.eagleli.leetcode.biweekly.contest_23;

public class CheckOverlap_5361 {
	public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
		if (x_center + radius < x1) {
			return false;
		}

		if (y_center + radius < y1) {
			return false;
		}

		if (x2 < x_center - radius) {
			return false;
		}

		if (y2 < y_center - radius) {
			return false;
		}

		if (x_center + radius == x1) {
			return true;
		}

		if (y_center + radius == y1) {
			return true;
		}

		if (x2 == x_center - radius) {
			return true;
		}

		if (y2 == y_center - radius) {
			return true;
		}

		if (radius * radius < (x1 - x_center) * (x1 - x_center) + (y1 - y_center) * (y1 - y_center)) {
			return false;
		}
		if (radius * radius < (x2 - x_center) * (x2 - x_center) + (y1 - y_center) * (y1 - y_center)) {
			return false;
		}
		if (radius * radius < (x2 - x_center) * (x2 - x_center) + (y2 - y_center) * (y2 - y_center)) {
			return false;
		}
		if (radius * radius < (x1 - x_center) * (x1 - x_center) + (y2 - y_center) * (y2 - y_center)) {
			return false;
		}
		return true;
	}
}
