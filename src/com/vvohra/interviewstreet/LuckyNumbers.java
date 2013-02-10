package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;

/*
 * NOT WORKING
 * https://www.hackerrank.com/challenges/lucky-numbers
 */
public class LuckyNumbers {
	Hashtable<Integer, Integer> ht = new Hashtable<>();

	public static void main(String args[]) throws Exception {
		long start = System.currentTimeMillis();
		fillSieve();
		System.out.println(System.currentTimeMillis() - start);
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int T = Integer.parseInt(line);
		for (int i = 0; i < T; i++) {
			String[] inputs_str = br.readLine().split(" ");
			luckyNumbers(Long.parseLong(inputs_str[0]), Long.parseLong(inputs_str[1]));
		}
	}

	public static void luckyNumbers(long start, long end) {
		int count = 0;
		for (long i = start; i < end; i++) {
			int[] array = returnIntArray(i);
			if (primes[sumArray(array)] && primes[sumSquaredArray(array)])
				count++;
		}
		System.out.println(count);
	}

	public static int[] returnIntArray(long num) {

		char[] buff = Long.toString(num).toCharArray();
		int[] result = new int[buff.length];
		for (int i = 0; i < result.length; i++) {
			result[i] = Character.getNumericValue(buff[i]);
		}
		return result;
	}

	public static int sumArray(int[] arr) {
		int sum = 0;
		for (int val : arr)
			sum += val;
		return sum;
	}

	public static int sumSquaredArray(int[] arr) {
		int squaredSum = 0;
		for (int val : arr)
			squaredSum += Math.pow(val, 2);
		return squaredSum;
	}

	// global array just to keep track of it in this example,
	// but you can easily do this within another function.

	// will contain true or false values for the first 10,000 integers
	static boolean[] primes = new boolean[1500];

	// set up the primesieve
	public static void fillSieve() {
		Arrays.fill(primes, true); // assume all integers are prime.
		primes[0] = primes[1] = false; // we know 0 and 1 are not prime.
		for (int i = 2; i < primes.length; i++) {
			// if the number is prime,
			// then go through all its multiples and make their values false.
			if (primes[i]) {
				for (int j = 2; i * j < primes.length; j++) {
					primes[i * j] = false;
				}
			}
		}
	}

	public static HashSet<Integer> GetFactors(int nInput) {
		HashSet<Integer> hs = new HashSet<>();
		int nNumberToFactor = nInput;
		int nCurrentUpper = nInput;
		int i;

		for (i = 2; i < nCurrentUpper; i++) {
			if ((nNumberToFactor % i) == 0) {
				// if we found a factor, the upper number is the new upper limit
				nCurrentUpper = nNumberToFactor / i;
				hs.add(i);

				if (nCurrentUpper != i) // avoid "double counting" the square
										// root
					hs.add(nCurrentUpper);
			}
		}
		return hs;
	}
}