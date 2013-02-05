package com.vvohra.interviewstreet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Billboard {

	static ArrayDeque<Integer> Q = new ArrayDeque<>();

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] lines = br.readLine().split(" ");
		int N = Integer.parseInt(lines[0]);
		int K = Integer.parseInt(lines[1]);
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		maxRevenue(arr, K);

	}

	public static long maxRevenue(int[] r, int K) {
		Q.clear();

		long[] dp = new long[r.length];
		long sum = 0;
		long endMin = Long.MAX_VALUE;

		for (int i = 0; i < dp.length; i++) {
			sum += r[i];
			long min = updateSlidingWindow(dp, i, K);
			dp[i] = min + r[i];

			if (i > dp.length - (K + 2)) {
				endMin = Math.min(endMin, dp[i]);
			}
		}

		long maxProfit = sum - endMin;
		System.out.println(maxProfit);

		return maxProfit;
	}

	public static long updateSlidingWindow(long[] arr, int index, int K) {
		index--;
		if (index == -1)
			return 0;

		if (index < K) {
			while (!Q.isEmpty() && arr[index] <= arr[Q.peekLast()])
				Q.pollLast();
			Q.addLast(index);
			return 0;
		} else if (index == K) {
			while (!Q.isEmpty() && arr[index] <= arr[Q.peekLast()])
				Q.pollLast();
			while (!Q.isEmpty() && Q.peekFirst() <= index - K - 1)
				Q.pollFirst();
			Q.addLast(index);
			long result = arr[Q.peekFirst()];
			return result;
		} else if (index < arr.length) {
			while (!Q.isEmpty() && arr[index] <= arr[Q.peekLast()])
				Q.pollLast();
			while (!Q.isEmpty() && Q.peekFirst() <= index - K - 1)
				Q.pollFirst();
			Q.addLast(index);
			long result = arr[Q.peekFirst()];
			return result;
		} else {
			return arr[Q.peekFirst()];
		}

	}

}