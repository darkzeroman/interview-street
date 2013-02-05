package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.HashSet;

public class CountScorecards {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for (int test_num = 0; test_num < T; test_num++) {
			int N = Integer.parseInt(br.readLine().trim());

			String[] inputs_str = br.readLine().split(" ");
			int[] inputs = new int[inputs_str.length];
			for (int index = 0; index < inputs_str.length; index++)
				inputs[index] = Integer.parseInt(inputs_str[index]);
			// System.out.println(Arrays.toString(inputs));

			// run(inputs);
			online(inputs);
		}

	}

	public static void run(int[] inputs) {
		HashSet<Integer> hs = new HashSet<Integer>();
		int emptyCount = 0;
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i] == -1) {
				emptyCount++;
				continue;
			}
			if (hs.contains(inputs[i])) {
				System.out.println("0");
				return;
			} else {
				hs.add(inputs[i]);
			}
		}

		BigInteger bi = BigInteger.ONE;
		for (int i = 1; i <= emptyCount; i++) {
			bi = bi.multiply(BigInteger.valueOf(i));
		}
		System.out.println(bi);

	}

	public static int numWins(int numPlayers) {
		return (numPlayers - 1) * (numPlayers) / 2;
	}

	public static void online(int[] inputs) {
		int numWins = numWins(inputs.length);
		int numNegs = 0;
		int sum = 0;
		for (int i = 0; i < inputs.length; i++) {
			if (inputs[i] == -1)
				numNegs++;
			else {
				sum += inputs[i];
			}
		}
		int Q = numNegs;
		int S = numWins - sum;

		long[][] DP = new long[Q][S];

		for (int i = 0; i < S; i++)
			DP[1][i] = 1;
		for (int q = 2; q < Q; q++) {
			for (int s = 1; s < S; s++) {
				long tempSum = 0;
				for (int i = 0; i < inputs.length - 1; i++) {
					tempSum += DP[q - 1][s - i];
				}
				DP[q][s] = tempSum;

			}
		}

		System.out.println(DP[Q - 1][S - 1]);

	}
}
