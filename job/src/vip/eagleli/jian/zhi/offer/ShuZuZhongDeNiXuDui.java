package vip.eagleli.jian.zhi.offer;

public class ShuZuZhongDeNiXuDui {
	public static void main(String[] args) {
		int[] array = { 7, 5, 6, 4 };
		System.out.println(InversePairs(array));
	}

	public static int InversePairs(int[] array) {
		int[] copy = new int[array.length];
		return (int) InversePairs(array, copy, 0, array.length - 1);
	}

	public static long InversePairs(int[] array, int[] copy, int start, int end) {
		if (start == end) {
			return 0;
		}

		int mid = (start + end) / 2;
		long left = InversePairs(array, copy, start, mid);
		long right = InversePairs(array, copy, mid + 1, end);
		left %= 1000000007;
		right %= 1000000007;
		long count = 0;
		int low = start;
		int high = mid + 1;
		int index = start;
		while (low <= mid && high <= end) {
			if (array[low] < array[high]) {
				copy[index++] = array[low++];
			} else {
				copy[index++] = array[high++];
				count += (mid - low + 1);
				count %= 1000000007;
			}
		}
		while (low <= mid) {
			copy[index++] = array[low++];
		}
		while (high <= end) {
			copy[index++] = array[high++];
		}
		for (int i = start; i <= end; i++) {
			array[i] = copy[i];
		}
		return (left + right + count) % 1000000007;
	}
}
