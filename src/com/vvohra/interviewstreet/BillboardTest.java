package com.vvohra.interviewstreet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class BillboardTest {

	@Test
	public void Test1() {
		int K = 3;
		int[] r = new int[] { 16, 28, 37, 8, 47, 30, 36, 6, 0, 37 };

		assertEquals(Billboard.maxRevenue(r, K), Billboard.maxRevenue(r, K));

	}

	@Test
	public void Test2() {
		int K = 2;
		int[] r = new int[] { 1, 2, 3, 1, 6, 10 };
		assertEquals(Billboard.maxRevenue(r, K), Billboard.maxRevenue(r, K));

	}

	@Test
	public void TestSlidingWindow() {
		// 1 3 2 2 3 3 6
		int[] r = new int[] { 1, 2, 3, 1, 6, 10 };
		// System.out.println(Arrays.toString(r));

		int w = 2;
		int[] B = new int[r.length];
		int n = r.length;
		// Billboard.maxSlidingWindow(r, n, w, B);
		// System.out.println(Arrays.toString(B));

	}

	@Test
	public void TestLiveSlidingWindow() {
		long[] r = new long[] { 1, 2, 3, 1, 6, 10 };
		int K = 2;
		long[] out = new long[r.length];
		for (int i = 0; i < r.length; i++) {
			out[i] = Billboard.updateSlidingWindow(r, i, K);
		}
		// System.out.println("Live:" + Arrays.toString(out));
	}

	@Test
	public void TestPQMinWindow() {
		long[] r = new long[] { 1, 2, 3, 1, 6, 10 };
		int K = 2;
		long[] out = new long[r.length];
		for (int i = 0; i < r.length; i++) {
			// out[i] = Billboard.updatePQAndGetMin(r, i, K);
		}
		// System.out.println("PQ  :" + Arrays.toString(out));
	}
}
