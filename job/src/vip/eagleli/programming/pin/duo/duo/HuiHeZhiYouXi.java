package vip.eagleli.programming.pin.duo.duo;

import java.util.Scanner;

public class HuiHeZhiYouXi {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int normalAttack = sc.nextInt();
		int buffedAttack = sc.nextInt();
		System.out.println(solve(n, normalAttack, buffedAttack));
	}

	// private static int solve(int n, int normalAttack, int buffedAttack) {
	// if (n <= 0) {
	// return 0;
	// }
	// return Math.min(1 + solve(n - normalAttack, normalAttack, buffedAttack),
	// 2 + solve(n - buffedAttack, normalAttack, buffedAttack));
	// }

	private static int solve(int n, int normalAttack, int buffedAttack) {
		int[] arrs = new int[n + 1];
		for (int i = 1; i <= n ; i++) {
			if (i >= buffedAttack) {
				arrs[i] = Math.min(1 + arrs[i - normalAttack], 2 + arrs[i - buffedAttack]);
			} else if (i >= normalAttack){
				arrs[i] = Math.min(1 + arrs[i - normalAttack], 2);
			} else {
				arrs[i] = Math.min(1, 2);
			}
		}
		return arrs[n];
	}
}
