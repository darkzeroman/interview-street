package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/*
 * NOT CORRECT
 * https://www.hackerrank.com/challenges/save-humanity
 * 
 */
public class SavingHumanity {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringBuilder pStr = new StringBuilder(br.readLine());
			StringBuilder vStr = new StringBuilder(br.readLine());
			br.readLine();
			solve(pStr, vStr);
		}

	}

	private static void solve(StringBuilder pStr, StringBuilder vStr) {
		ArrayList<Integer> al = new ArrayList<>();
		for (int i = 0; i < pStr.length(); i++) {
			int numErrors = 0;
			int numCounted = 0;
			int index = i;
			for (int j = 0; j < vStr.length(); j++) {
				if (index >= pStr.length()) {
					numErrors = numErrors + 2;
					continue;
				}
				if (vStr.charAt(j) == pStr.charAt(index))
					numCounted++;
				else if (numErrors > 1) {
					numErrors++;
					break;
				} else
					numErrors++;
				index++;
			}
			if (numErrors <= 1 && numCounted >= vStr.length() - 1)
				al.add(i);
		}
		String delim = "";
		StringBuilder sb = new StringBuilder();

		for (Integer num : al) {
			sb.append(delim).append(num);
			delim = " ";
		}
		System.out.println(sb.toString());
	}
}
