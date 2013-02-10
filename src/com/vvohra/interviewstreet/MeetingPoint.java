package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

/*
 * NOT CORRECT
 * https://www.hackerrank.com/challenges/meeting-point
 */
public class MeetingPoint {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		long[][] points = new long[N][2];
		for (int i = 0; i < N; i++) {
			String[] inputs_str = br.readLine().split(" ");
			points[i][0] = Long.parseLong(inputs_str[0]);
			points[i][1] = Long.parseLong(inputs_str[1]);
		}
		sumDistances(points);
	}

	public static void sumDistances(long[][] points) {

		TreeSet<LongPoint> x_sorted = new TreeSet<>();
		TreeSet<LongPoint> y_sorted = new TreeSet<>(new LongPointComparator());
		for (int i = 0; i < points.length; i++) {
			long x = points[i][1] - points[i][0];
			long y = points[i][1] + points[i][0];
			LongPoint lp = new LongPoint(x, y);
			x_sorted.add(lp);
			y_sorted.add(lp);

		}

		long[] sumXs = new long[points.length];
		long[] sumYs = new long[points.length];

		Iterator<LongPoint> XIter = x_sorted.iterator();
		Iterator<LongPoint> YIter = y_sorted.iterator();

		int index = 0;
		while (XIter.hasNext()) {
			if (index == 0) {
				sumXs[index] = XIter.next().x;
				sumYs[index] = YIter.next().y;
			} else {
				sumXs[index] = sumXs[index - 1] + XIter.next().x;
				sumYs[index] = sumYs[index - 1] + YIter.next().y;
			}
			index++;
		}

		XIter = x_sorted.iterator();
		YIter = y_sorted.iterator();

		Hashtable<LongPoint, long[]> ht = new Hashtable<>();

		index = 0;
		while (XIter.hasNext()) {
			LongPoint lp = XIter.next();
			long xDistance = 0;
			if (index == 0)
				xDistance = sumXs[sumXs.length - 1] - sumXs[index] - (sumXs.length - 2 * index + 1) * lp.x;
			else
				xDistance = sumXs[sumXs.length - 1] - sumXs[index] - sumXs[index - 1] - (sumXs.length - 2 * index + 1)
						* lp.x;
			ht.put(lp, new long[] { xDistance, 0 });
			index++;
		}

		index = 0;
		while (YIter.hasNext()) {
			LongPoint lp = YIter.next();
			long yDistance = 0;
			if (index == 0)
				yDistance = sumYs[sumYs.length - 1] - sumYs[index] - (sumYs.length - 2 * index + 1) * lp.y;
			else
				yDistance = sumYs[sumYs.length - 1] - sumYs[index] - sumYs[index - 1] - (sumYs.length - 2 * index + 1)
						* lp.y;
			ht.get(lp)[1] = yDistance;
			index++;
		}

		Iterator<Entry<LongPoint, long[]>> all_iter = ht.entrySet().iterator();
		while (all_iter.hasNext()) {
			Entry<LongPoint, long[]> entry = all_iter.next();
			System.out.println(entry.getKey() + " " + Arrays.toString(entry.getValue()));
		}

		all_iter = ht.entrySet().iterator();
		long minDistance = Long.MAX_VALUE;
		LongPoint minLongPoint = null;

		while (all_iter.hasNext()) {
			Entry<LongPoint, long[]> entry = all_iter.next();
			long tempDistance = entry.getValue()[0] + entry.getValue()[1];
			if (tempDistance < minDistance) {
				minLongPoint = entry.getKey();
				minDistance = tempDistance;
			}
		}

		System.out.println(minDistance);
		System.out.println((minLongPoint.y - minLongPoint.x) / 2 + "," + (minLongPoint.y + minLongPoint.x) / 2);

	}
}

class LongPoint implements Comparable<LongPoint> {

	long x, y;

	public LongPoint(long x, long y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public int compareTo(LongPoint o) {
		return (int) (this.x - o.x);
	}

	@Override
	public String toString() {
		return String.format("%d %d", x, y);
	}
}

class LongPointComparator implements Comparator<LongPoint> {

	@Override
	public int compare(LongPoint o1, LongPoint o2) {
		if (o1.y != o2.y)
			return (int) (o1.y - o2.y);
		else
			return (int) (o2.x - o1.x);

	}

}