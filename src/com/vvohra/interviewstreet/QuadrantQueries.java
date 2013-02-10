package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * NOT CORRECT
 * https://www.hackerrank.com/challenges/quadrant-queries
 */
public class QuadrantQueries {
	static boolean[] numChangesX;
	static boolean[] numChangesY;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine().trim());

		numChangesX = new boolean[N];
		numChangesY = new boolean[N];
		int[][] points = new int[N][2];
		for (int i = 0; i < N; i++) {
			String[] inputs_str = br.readLine().split(" ");
			points[i][0] = Integer.parseInt(inputs_str[0]);
			points[i][1] = Integer.parseInt(inputs_str[1]);
		}

		setup(points);

		int Q = Integer.parseInt(br.readLine().trim());
		for (int i = 0; i < Q; i++) {
			char[] buff = br.readLine().toCharArray();

			int start = Character.getNumericValue(buff[2]);
			int end = Character.getNumericValue(buff[4]);

			start--;
			end--;

			if (buff[0] == 'X')
				flipX(points, start, end);
			else if (buff[0] == 'Y')
				flipY(points, start, end);
			else {
				findC(points, start, end);
			}
		}

	}

	private static void setup(int[][] points) {
		for (int i = 0; i < points.length; i++) {
			if (points[i][0] > 0)
				numChangesX[i] = true;
			if (points[i][1] > 0)
				numChangesY[i] = true;
		}

	}

	private static void flipX(int[][] points, int start, int end) {
		for (int i = start; i <= end; i++) {
			numChangesY[i] = !numChangesY[i];

		}

	}

	private static void flipY(int[][] points, int start, int end) {
		for (int i = start; i <= end; i++) {
			numChangesX[i] = !numChangesX[i];

		}
	}

	private static void findC(int[][] points, int start, int end) {
		int firstQ = 0, secondQ = 0, thirdQ = 0, fourthQ = 0;
		for (int i = start; i <= end; i++) {
			boolean posX, posY;
			posX = numChangesX[i];
			posY = numChangesY[i];

			if (posX && posY)
				firstQ++;
			else if (!posX && posY)
				secondQ++;
			else if (!posX && !posY)
				thirdQ++;
			else
				fourthQ++;

		}
		System.out.println(String.format("%d %d %d %d", firstQ, secondQ, thirdQ, fourthQ));
	}
}