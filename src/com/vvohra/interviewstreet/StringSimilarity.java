package com.vvohra.interviewstreet;

import java.io.IOException;
import java.util.Scanner;

/*
 * https://www.hackerrank.com/challenges/string-similarity
 */
public class StringSimilarity {

	public static void main(String[] args) throws IOException {
		Scanner scanner = new Scanner(System.in);

		int N = scanner.nextInt();
		String[] inputs = new String[N];
		for (int i = 0; i < N; i++)
			inputs[i] = scanner.next();

		for (int i = 0; i < N; i++) {
			int res = countSim(inputs[i]);
			System.out.println(res);
		}
		scanner.close();

	}

	public static int countSim(String str) {
		int sim = 0;
		char[] charArray = str.toCharArray();
		int n = charArray.length;
		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (charArray[j - i] == charArray[j])
					sim++;
				else
					break;
			}
		}
		return sim;
	}

}
