package com.vvohra.interviewstreet;

import java.util.Arrays;
import java.util.Scanner;

/**
 * https://www.hackerrank.com/challenges/flowers
 */
public class Flowers {

	public static long minCost(long[] costs, int numFlowers, int numFriends) {
		long finalCost = 0;
		int numFriendsBought = 0;

		int[] arrPersonFlowers = new int[numFriends];

		for (int i = costs.length - 1; i >= 0; i--) {
			int x = ++arrPersonFlowers[numFriendsBought % numFriends];
			finalCost += x * (costs[i]);
			++numFriendsBought;
		}

		return finalCost;
	}

	public static void main(String[] args) {
		int N;
		int K;

		Scanner in = new Scanner(System.in);

		N = in.nextInt();
		K = in.nextInt();

		long[] costs = new long[N];
		for (int i = 0; i < N; i++) {
			costs[i] = in.nextLong();
		}
		in.close();
		Arrays.sort(costs);
		System.out.println(minCost(costs, N, K));

	}

}