package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TwosComplement {
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
			long res = countOnesRange(a, b);
			System.out.println(res);
		}

	}

	public static long countOnesRange(int a, int b) {
		long num = 0;
		for (int i = a; i <= b; i++) {
			num += countOnes(Integer.toBinaryString(i));
		}
		return num;
	}

	public static int countOnes(String str) {
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '1')
				count++;
		}
		return count;
	}

}
