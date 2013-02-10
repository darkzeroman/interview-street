package com.vvohra.interviewstreet;

import java.util.Arrays;

/*
 * https://www.hackerrank.com/challenges/billboards
 */
public class BillBoards {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// maxRevenue(new int[] { 0, 1, 2, 8, 10 },
		// new int[] { 0, 10, 15, 50, 20 });
		maxRevenue(new long[] { 1, 2, 3, 1, 6, 10 }, 2);
		newMaxMax(new long[] { 1, 2, 3, 1, 6, 10 }, 2);
	}

	//

	public static void maxRevenue(long[] r, int K) {
		long[] T = new long[r.length];

		for (int i = 0; i < T.length; i++) {
			long temp = 0;

			temp = Math.max(getPrevious(r, i, K) + get(T, i - (K + 1)), temp);
			temp = Math.max(getPrevious(r, i, K - 1) + get(T, i - K), temp);
			temp = Math.max(get(T, i - 1), temp);
			temp = Math.max(get(r, i), temp);

			T[i] = temp;
		}
		System.out.println(Arrays.toString(T));
	}

	public static long getPrevious(long[] r, int index, int K) {
		long temp = 0;
		for (int i = 0; i < K; i++) {
			temp += get(r, index - i);
		}
		return temp;

	}

	public static long get(long[] T, int index) {
		if (index < 0)
			return 0;
		else
			return T[index];
	}

	public static long newMaxMax(long[] r, int K) {
		long[] dp = new long[r.length];
		long sum = 0;
		for (int i = 0; i < dp.length; i++) {
			sum += r[i];
			long min = Long.MAX_VALUE;

			for (int j = 1; j <= K + 1; j++) {
				min = Math.min(min, get(dp, i - j));
			}
			dp[i] = min + r[i];
		}
		long min = Long.MAX_VALUE;
		for (int i = 1; i <= K + 1; i++) {
			min = dp[dp.length - i];
		}
		long maxProfit = sum - min;
		System.out.println(maxProfit);
		return maxProfit;
	}
}