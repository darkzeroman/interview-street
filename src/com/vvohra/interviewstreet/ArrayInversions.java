package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ArrayInversions {
	static int[] aux;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			int length = Integer.parseInt(br.readLine());
			String[] inputStr = br.readLine().split(" ");
			int[] input = new int[length];

			for (int index = 0; index < input.length; index++)
				input[index] = Integer.parseInt(inputStr[index]);
			long res = numInversions(input);
			System.out.println(res);
		}
	}

	public static long numInversions(int[] arr) {
		aux = new int[arr.length];
		return helper(arr, 0, arr.length);
	}

	public static long helper(int[] arr, int low, int high) {
		if (high - low <= 1)
			return 0;

		int mid = low + (high - low) / 2;
		long numInversions = 0;
		numInversions += helper(arr, low, mid);
		numInversions += helper(arr, mid, high);
		numInversions += merge(arr, low, high, mid);

		return numInversions;
	}

	public static long merge(int[] arr, int l, int r, int mid) {
		int a = l, b = mid, arrIndex = l;
		for (int i = l; i < r; i++)
			aux[i] = arr[i];

		long numInversions = 0;
		while (a < mid && b < r) {
			if (aux[a] <= aux[b]) {
				arr[arrIndex++] = aux[a++];
			} else {
				arr[arrIndex++] = aux[b++];
				numInversions += (mid - a);
			}
		}

		while (a < mid)
			arr[arrIndex++] = aux[a++];
		while (b < r)
			arr[arrIndex++] = aux[b++];

		return numInversions;
	}
}
