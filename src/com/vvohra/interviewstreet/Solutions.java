package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solutions {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringBuilder str = new StringBuilder();

		String[] input = br.readLine().split(" ");
		int N = new Integer(input[0]);
		int K = new Integer(input[1]);
		str.append(N + " " + K).append("\n");

		N--;
		while (N >= 0) {
			str.append(br.readLine()).append("\n");
			N--;
		}
		System.out.println(str.toString());
		br.close();
		throw new Exception(str.toString());
	}
}