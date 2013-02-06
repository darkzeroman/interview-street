package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;

/*
 * https://www.hackerrank.com/challenges/unfriendly-numbers
 */
public class UnfriendlyNumbers {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputs_str = br.readLine().split(" ");
		int N = Integer.parseInt(inputs_str[0]);
		long K = Long.parseLong(inputs_str[1]);

		String[] unfriendlies_str = br.readLine().split(" ");
		HashSet<Long> friendly_factors = new HashSet<>();
		GetFactors(K, friendly_factors);
		HashSet<Long> unfriendly_factors = new HashSet<>();
		long[] unfriendlies = new long[unfriendlies_str.length];
		for (int i = 0; i < unfriendlies.length; i++) {
			unfriendlies[i] = GCD(Long.parseLong(unfriendlies_str[i]), K);
			unfriendly_factors.add(unfriendlies[i]);
			GetFactors(unfriendlies[i], unfriendly_factors);
		}
		Iterator<Long> iter = friendly_factors.iterator();
		long numCount = 0;
		while (iter.hasNext()) {
			if (!unfriendly_factors.contains(iter.next()))
				numCount++;
		}
		System.out.println(numCount);
	}

	public static long GCD(long a, long b) {
		return b == 0 ? a : GCD(b, a % b);
	}

	public static void GetFactors(long nInput, HashSet<Long> hs) {
		hs.add(1L);
		hs.add(nInput);
		long upperLimit = nInput;
		long currentUpperLimit = nInput;
		long i;

		for (i = 2; i < currentUpperLimit; i++) {
			if ((upperLimit % i) == 0) {
				currentUpperLimit = upperLimit / i;
				hs.add(i);

				if (currentUpperLimit != i) // avoid "double counting" the
											// square
					// root
					hs.add(currentUpperLimit);
			}
		}
	}
}
