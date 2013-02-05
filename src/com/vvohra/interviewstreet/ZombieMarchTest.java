package com.vvohra.interviewstreet;
import java.math.BigDecimal;
import java.util.ArrayList;

import org.junit.Test;


public class ZombieMarchTest {

	@Test
	public void test() {
		ArrayList<ArrayList<Integer>> neighbors = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 4; i++)
			neighbors.add(new ArrayList<Integer>());

		int[][] edges = new int[][] { { 0, 1 }, { 0, 2 }, { 2, 3 }, { 1, 3 } };
		int[] startingPoints = new int[] { 0 };
		BigDecimal[][] prob = new BigDecimal[1][4];
		prob[0][0] = new BigDecimal(1);
		int K = 2;
		ZombieMarch.prepare(4, edges.length, K, startingPoints.length, edges,
				startingPoints);

	}

}
