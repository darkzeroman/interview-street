package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwosCompletionBetter {
	/*
	 * https://www.hackerrank.com/challenges/2s-complement
	 */
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			String[] inputStr = br.readLine().split(" ");
			int a = Integer.parseInt(inputStr[0]);
			int b = Integer.parseInt(inputStr[1]);
			long res = numBitsRange(a, b);
			System.out.println(res);
		}

	}

	static long numBitsRange(int a, int b) {
		if (a >= 0) {
			long res = numBits(b);
			if (a > 0)
				res -= numBits(a - 1);
			return res;
		}
		long res = (32L * -(long) a) - numBits(~a);
		if (b > 0)
			res += numBits(b);
		else if (b < -1) {
			b++;
			res -= (32L * -(long) b) - numBits(~b);

		}
		return res;

	}

	static long numBits(int a) {
		if (a == 0)
			return 0;
		if (a % 2 == 0)
			return numBits(a - 1) + weight(a);
		return ((long) a + 1) / 2 + 2 * numBits(a / 2);
	}

	static int weight(int i) {
		i = i - ((i >> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
		return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	}

}
