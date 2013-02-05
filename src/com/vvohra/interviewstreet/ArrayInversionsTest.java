package com.vvohra.interviewstreet;
import org.junit.Test;


public class ArrayInversionsTest {
	/*
	 * @Test public void test() { // [1 3 5 8] and [2 4 6 7] int[] arr = new
	 * int[] { 1, 3, 5, 8, 2, 4, 6, 7 }; int[] aux = new int[arr.length]; long
	 * res = ArrayInversions.merge(arr, 0, arr.length, (arr.length) / 2);
	 * 
	 * System.out.println(res); }
	 */

	@Test
	public void testNumInversions() {
		int[] unsorted = new int[] { 2, 1, 3, 1, 2 };
		long res = ArrayInversions.numInversions(unsorted);
		System.out.println(res);

		unsorted = new int[] { 1, 1, 1, 2, 2 };
		res = ArrayInversions.numInversions(unsorted);
		System.out.println(res);

	}
}
