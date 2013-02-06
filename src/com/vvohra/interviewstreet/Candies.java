package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * https://www.hackerrank.com/challenges/candies
 */
public class Candies {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());
		int[] inputs = new int[N];
		long[] candies = new long[N];
		for (int i = 0; i < N; i++)
			inputs[i] = Integer.parseInt(br.readLine().trim());

		for (int i = 0; i < N; i++) {
			candies[i] = 1;
			if (i == 0)
				continue;

			if (inputs[i - 1] < inputs[i]) {
				candies[i] = candies[i - 1] + 1;
			} else {
				int j = i;
				while (j > 0 && inputs[j - 1] > inputs[j]) {
					candies[j - 1] = Math.max(candies[j - 1], candies[j] + 1);
					j = j - 1;
				}
			}
		}

		long numTotal = 0;
		for (int i = 0; i < N; i++)
			numTotal += candies[i];

		System.out.println(numTotal);
	}

}
