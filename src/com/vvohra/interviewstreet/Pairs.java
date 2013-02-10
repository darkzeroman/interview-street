package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

/*
 * https://www.hackerrank.com/challenges/pairs
 */
public class Pairs {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] lines = br.readLine().split(" ");
		int N = Integer.parseInt(lines[0]);
		int K = Integer.parseInt(lines[1]);
		String[] inputs_str = br.readLine().split(" ");
		int[] inputs = new int[N];
		for (int i = 0; i < N; i++)
			inputs[i] = Integer.parseInt(inputs_str[i]);
		run(inputs, K);
	}

	public static void run(int[] inputs, int K) {
		long numPairs = 0;
		HashSet<Integer> set = new HashSet<>();
		Arrays.sort(inputs);
		for (int i = 0; i < inputs.length; i++) {
			if (set.contains(inputs[i] + K))
				numPairs++;
			if (set.contains(inputs[i] - K))
				numPairs++;
			set.add(inputs[i]);
		}
		System.out.println(numPairs);
	}
}
