package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

public class OnlineMedian {

	static PriorityQueue<Long> minElems;
	static PriorityQueue<Long> maxElems;
	static Hashtable<Long, Long> set;
	static int balance;
	static BigDecimal current_median;

	public static void main(String[] args) throws Exception {
		current_median = new BigDecimal(0);
		minElems = new PriorityQueue<Long>(11, new MinComparator());
		maxElems = new PriorityQueue<Long>();
		set = new Hashtable<Long, Long>();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int numOps = Integer.parseInt(br.readLine());
		for (int i = 0; i < numOps; i++) {
			String[] str = br.readLine().split(" ");
			if (str[0].equals("r"))
				remove(new Long(str[1]));
			else
				add(new Long(str[1]));
		}
	}

	public static void add(Long num) {
		if (set.get(num) == null)
			set.put(num, new Long(0));
		Long tempLong = set.get(num);
		set.put(num, new Long(tempLong.longValue() + 1));

		if (num <= current_median.longValue())
			minElems.add(num);
		else
			maxElems.add(num);

		checkBalance();
		updateMedian();

	}

	public static void remove(Long num) {

		if (set.get(num) == null || set.get(num).intValue() == 0) {
			System.out.println("Wrong!");
			return;
		}

		set.put(num, set.get(num).longValue() - 1);

		balance = maxElems.size() - minElems.size();
		if (balance == 0) {
			if (num.longValue() < current_median.longValue())
				minElems.remove(num);
			else
				maxElems.remove(num);
		} else if (balance > 0) {
			if (num.longValue() >= current_median.longValue())
				maxElems.remove(num);
			else
				minElems.remove(num);
		} else {
			if (num.longValue() <= current_median.longValue())
				minElems.remove(num);
			else
				maxElems.remove(num);
		}

		checkBalance();
		updateMedian();

	}

	public static void checkBalance() {
		balance = maxElems.size() - minElems.size();
		switch (balance) {
		case 0:
			break;
		case 1:
			Balance(maxElems, minElems);
			break;
		case -1:
			Balance(minElems, maxElems);
			break;
		case 2:
			Balance(maxElems, minElems);
			break;
		case -2:
			Balance(minElems, maxElems);
			break;
		}
	}

	public static void Balance(PriorityQueue<Long> from, PriorityQueue<Long> to) {
		Long temp = from.poll();
		if (temp == null)
			return;
		to.add(temp);
	}

	public static void updateMedian() {
		if (minElems.size() == 0 && maxElems.size() == 0) {
			System.out.println("Wrong!");
			return;
		}
		balance = maxElems.size() - minElems.size();

		switch (balance) {
		case 0:
			long temp = minElems.peek().longValue() + maxElems.peek().longValue();

			current_median = new BigDecimal(temp / 2.0);
			break;
		case 1:
			current_median = new BigDecimal(maxElems.peek());
			break;
		case -1:
			current_median = new BigDecimal(minElems.peek());
			break;
		}
		System.out.println(current_median.toString());
	}

}

class MinComparator implements Comparator<Long> {

	@Override
	public int compare(Long o1, Long o2) {
		return o2.compareTo(o1);
	}

}
