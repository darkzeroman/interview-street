package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class StringReduction {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int i = 0; i < T; i++) {
			newReduce(br.readLine());
		}

	}

	public static void newReduce(String str) {
		int[] nums = new int[3];
		char[] buff = str.toCharArray();
		for (int i = 0; i < buff.length; i++) {
			if (buff[i] == 'a')
				nums[0]++;
			else if (buff[i] == 'b')
				nums[1]++;
			else
				nums[2]++;
		}

		if (isTwoZero(nums)) {
			System.out.println(buff.length);
		} else if (nums[0] % 2 == 0 && nums[1] % 2 == 0 && nums[2] % 2 == 0) {
			System.out.println("2");
		} else if (nums[0] % 2 == 1 && nums[1] % 2 == 1 && nums[2] % 2 == 1) {
			System.out.println("2");
		} else
			System.out.println("1");
	}

	public static boolean isTwoZero(int[] nums) {
		if (nums[0] + nums[1] == 0)
			return true;
		if (nums[1] + nums[2] == 0)
			return true;
		if (nums[0] + nums[2] == 0)
			return true;
		return false;
	}

	public static void reduce(String str) {
		char[] buff = str.toCharArray();
		boolean keepGoing = true;
		while (keepGoing) {
			keepGoing = false;
			int start = -1;
			int end = -1;
			for (int i = 0; i < buff.length; i++) {
				if (buff[i] == 0)
					continue;
				if (start == -1) {
					start = i;
					continue;
				}
				if (end == -1) {
					end = i;
					if (buff[start] == buff[end]) {
						start = end;
						end = -1;
						continue;
					}
					char temp = changeToMake(buff[start], buff[end]);
					buff[start] = temp;
					buff[end] = 0;

					end = -1;
					keepGoing = true;

				}

			}
		}

		StringBuilder strbuilder = new StringBuilder();

		for (int i = 0; i < buff.length; i++) {
			if (buff[i] != 0)
				strbuilder.append(buff[i]);
		}
		System.out.println(strbuilder.toString().length());

	}

	public static char changeToMake(char one, char two) {
		char tempOne = one;
		char tempTwo = two;
		if (two < one) {
			tempOne = two;
			tempTwo = one;
		}

		if (tempOne == 'a' && tempTwo == 'b')
			return 'c';
		else if (tempOne == 'a' && tempTwo == 'c')
			return 'b';
		else
			// (one == 'b' && two == 'c')
			return 'a';
	}
}
