package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class NewZombieMarch {
	public static ArrayList<ArrayList<Integer>> neighbors;
	public static double[][] prob;
	static Integer[][] neighbors_fast;

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String[] NMK = br.readLine().split(" ");
			int N = Integer.parseInt(NMK[0]);
			int M = Integer.parseInt(NMK[1]);
			int K = Integer.parseInt(NMK[2]);
			// K = ;
			int[][] edges = new int[M][2];
			for (int i = 0; i < M; i++) {
				String[] edge = br.readLine().split(" ");
				edges[i][0] = Integer.parseInt(edge[0]);
				edges[i][1] = Integer.parseInt(edge[1]);
			}
			double[][] startPositions = new double[N][1];
			for (int i = 0; i < N; i++)
				startPositions[i][0] = Double.parseDouble(br.readLine());

			prepare(N, M, K, edges, startPositions);

		}
	}

	public static void prepare(int N, int M, int K, int[][] edges, double[][] startingPoints) {

		initialize(edges, N);
		findExpected(N, K, startingPoints);
	}

	public static void findExpected(int N, int K, double[][] startingPoints) {

		while (K > 1) {
			prob = ijkAlgorithm(prob, prob);
			K--;
		}
		double[][] ans = ijkend(prob, startingPoints);
		// for (int i = 0; i < ans.length; i++)
		// System.out.println(Arrays.toString(ans[i]));
		sumProbs(ans);
	}

	private static void sumProbs(double[][] ans) {
		double[] arr = new double[ans.length];
		for (int i = 0; i < ans.length; i++)
			arr[i] = ans[i][0];
		Arrays.sort(arr);

		double[] smaller = Arrays.copyOfRange(arr, arr.length - 5, arr.length);
		StringBuilder sb = new StringBuilder();
		for (int i = smaller.length - 1; i >= smaller.length - 5; i--) {
			sb.append(Math.round(smaller[i])).append(" ");
		}
		System.out.println(sb.toString());
	}

	public static void initialize(int[][] edges, int N) {
		prob = new double[N][N];

		neighbors = new ArrayList<ArrayList<Integer>>();

		for (int i = 0; i < N; i++)
			neighbors.add(new ArrayList<Integer>());

		for (int i = 0; i < edges.length; i++) {
			int u = edges[i][0];
			int v = edges[i][1];

			neighbors.get(u).add(v);
			neighbors.get(v).add(u);
		}

		neighbors_fast = new Integer[neighbors.size()][];

		for (int i = 0; i < neighbors.size(); i++)
			neighbors_fast[i] = neighbors.get(i).toArray(new Integer[0]);

		for (int i = 0; i < neighbors_fast.length; i++) {
			for (int j = 0; j < neighbors_fast[i].length; j++) {
				int index = neighbors_fast[i][j];
				prob[neighbors_fast.length - 1 - index][i] = 1 / (double) neighbors_fast[i].length;
			}
		}

	}

	static double[][] ijkAlgorithm(double[][] A, double[][] B) {
		int n = B.length;

		// initialise C
		double[][] C = new double[n][n];

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				for (int k = 0; k < n; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}

	static double[][] ijkend(double[][] A, double[][] B) {

		int x = A.length;
		int y = 1;
		// initialise C
		double[][] C = new double[A.length][1];

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				for (int k = 0; k < x; k++) {
					C[i][j] += A[i][k] * B[k][j];
				}
			}
		}
		return C;
	}
}
