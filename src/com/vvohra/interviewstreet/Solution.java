package com.vvohra.interviewstreet;
import java.util.Scanner;

public class Solution {

	public static void main(String args[]) throws Exception {
		Scanner scanner = new Scanner(System.in);
		StringBuilder str = new StringBuilder();

		int N = scanner.nextInt();
		while (N >= 0) {
			str.append(scanner.nextLine()).append("\n");
			N--;
		}
		System.out.println(str.toString());
		scanner.close();
		throw new Exception(str.toString());
	}
}
