package vip.eagleli.leetcode;

public class LeetCode836 {

	/**
	 * 判断一个一个矩形的四个点是否有一个在另一个矩形中
	 * 
	 * 这种方法是错误的
	 * 
	 * @param rec1
	 * @param rec2
	 * @return
	 */
	public boolean isRectangleOverlap_(int[] rec1, int[] rec2) {
		return isPointInRectangle(new int[] { rec1[0], rec1[1] }, rec2)
				|| isPointInRectangle(new int[] { rec1[2], rec1[1] }, rec2)
				|| isPointInRectangle(new int[] { rec1[2], rec1[3] }, rec2)
				|| isPointInRectangle(new int[] { rec1[0], rec1[3] }, rec2);
	}

	public boolean isPointInRectangle(int[] point, int[] rec) {
		return point[0] > rec[0] && point[1] > rec[1] && point[0] < rec[2] && point[1] < rec[3];
	}

	/**
	 * 分析在什么情况下，矩形 rec1 和 rec2 没有重叠
	 * 
	 * 想象一下，如果我们在平面中放置一个固定的矩形 rec2，那么矩形 rec1 必须要出现在 rec2 的「四周」
	 * 
	 * 也就是说，矩形 rec1 需要满足以下四种情况中的至少一种：
	 * 矩形 rec1 在矩形 rec2 的左侧
	 * 矩形 rec1 在矩形 rec2 的右侧
	 * 矩形 rec1 在矩形 rec2 的上方
	 * 矩形 rec1 在矩形 rec2 的下方
	 * 
	 * @param rec1
	 * @param rec2
	 * @return
	 */
	public boolean isRectangleOverlap(int[] rec1, int[] rec2) {
		return !(rec1[2] <= rec2[0] || rec1[3] <= rec2[1] || rec1[0] >= rec2[2] || rec1[1] >= rec2[3]);
	}

	/**
	 * 如果两个矩形重叠，那么它们重叠的区域一定也是一个矩形
	 * 那么这代表了两个矩形与 x轴平行的边（水平边）投影到 x轴上时会有交集，与 y轴平行的边（竖直边）投影到 y轴上时也会有交集。
	 * 
	 * 矩形 rec1 和 rec2 的水平边投影到 x轴上的线段分别为 (rec1[0], rec1[2])和 (rec2[0], rec2[2])。
	 * 根据数学知识我们可以知道，当 Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])时
	 * 这两条线段有交集。
	 * 对于矩形 rec1 和 rec2 的竖直边投影到 y轴上的线段，同理可以得到
	 * 当Math.min(rec1[3], rec2[3]) > max(rec1[1], rec2[1])时，这两条线段有交集。
	 */
	public boolean isRectangleOverlap2(int[] rec1, int[] rec2) {
		return Math.min(rec1[2], rec2[2]) > Math.max(rec1[0], rec2[0])
				&& Math.min(rec1[3], rec2[3]) > Math.max(rec1[1], rec2[1]);
	}
}
