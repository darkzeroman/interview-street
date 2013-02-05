package com.vvohra.interviewstreet;

import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/string-similarity
 */

public class Strsim {

	public static void main(String[] args) {

		Scanner user_input = new Scanner(System.in);

		String v1 = user_input.next();
		int number_cases = Integer.parseInt(v1);

		String[] cases = new String[number_cases];
		for (int i = 0; i < number_cases; i++)
			cases[i] = user_input.next();

		for (int k = 0; k < number_cases; k++) {
			int similarity = solve(cases[k]);
			System.out.println(similarity);
		}

		user_input.close();
	}

	static int solve(String sample) {
		int len = sample.length();
		char[] letters = sample.toCharArray();
		int sim = 0;
		for (int i = 0; i < len; i++) {
			for (int j = i; j < len; j++) {
				if (letters[j - i] == letters[j])
					sim++;
				else
					break;
			}
		}
		return sim;
	}
}