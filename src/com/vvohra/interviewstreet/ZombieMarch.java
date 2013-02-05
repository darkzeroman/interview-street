package com.vvohra.interviewstreet;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class ZombieMarch {
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
			K = 50;
			int[][] edges = new int[M][2];
			for (int i = 0; i < M; i++) {
				String[] edge = br.readLine().split(" ");
				edges[i][0] = Integer.parseInt(edge[0]);
				edges[i][1] = Integer.parseInt(edge[1]);
			}
			int[] startPositions = new int[N];
			for (int i = 0; i < N; i++)
				startPositions[i] = Integer.parseInt(br.readLine());

			int sum = 0;
			for (int i = 0; i < startPositions.length; i++)
				sum += startPositions[i];

			prepare(N, M, K, sum, edges, startPositions);

		}
	}

	public static void prepare(int N, int M, int K, int Z, int[][] edges,
			int[] startingPoints) {

		initialize(edges, startingPoints, N, Z);
		findExpected(N, K, Z);
	}

	public static void findExpected(int N, int K, int Z) {

		while (K > 0) {
			for (int i = 0; i < Z; i++) {
				prob[i] = nextStep(prob[i]);
			}
			K--;
		}
		sumProbs();
	}

	public static void sumProbs() {
		double[] sumProbs = new double[prob[0].length];
		for (int i = 0; i < sumProbs.length; i++)
			sumProbs[i] = 0;

		for (int i = 0; i < prob.length; i++) {
			for (int index = 0; index < prob[i].length; index++)
				sumProbs[index] = sumProbs[index] + prob[i][index];
		}

		long[] printProbs = new long[sumProbs.length];
		for (int i = 0; i < sumProbs.length; i++) {
			printProbs[i] = Math.round(sumProbs[i]);
		}
		Arrays.sort(printProbs);
		StringBuilder sb = new StringBuilder();
		for (int i = printProbs.length - 1; i >= printProbs.length - 5; i--) {
			sb.append(printProbs[i]).append(" ");
		}
		System.out.println(sb.toString());

	}

	public static double[] nextStep(double[] currStep) {
		double[] nextStep = new double[currStep.length];
		for (int i = 0; i < nextStep.length; i++)
			nextStep[i] = 0;

		for (int i = 0; i < currStep.length; i++) {
			Integer[] currNeighbors = neighbors_fast[i];
			int numNeighbors = currNeighbors.length;
			double probNeighbor;
			if (numNeighbors != 0)
				probNeighbor = 1 / (double) numNeighbors;
			else
				probNeighbor = 0;
			for (int nIndex = 0; nIndex < currNeighbors.length; nIndex++) {
				int nextNeighbor = currNeighbors[nIndex];
				double toAdd = currStep[i] * probNeighbor;
				nextStep[nextNeighbor] = nextStep[nextNeighbor] + toAdd;
			}

		}
		return nextStep;
	}

	public static void initialize(int[][] edges, int[] startPositions, int N,
			int Z) {
		prob = new double[Z][N];

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

		int index = 0;
		for (int i = 0; i < startPositions.length; i++)
			for (int j = 0; j < startPositions[i]; j++) {
				prob[index][i] = 1d;
				index++;
			}

	}

	static double[][] ijkAlgorithm(double[][] A, double[][] B) {
		int n = A.length;

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

}
