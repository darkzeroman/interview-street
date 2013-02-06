package com.vvohra.interviewstreet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.LinkedList;

public class FacebookChallenge {

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);
		for (int i = 0; i < N; i++) {
			recCall(Integer.parseInt(br.readLine()));
		}

	}

	public static void recCall(int num) {
		LinkedList<Integer> queue = new LinkedList<Integer>();

		queue.add(num);
		queue.add(-1);
		Hashtable<Integer, ArrayList<Integer>> memo = new Hashtable<Integer, ArrayList<Integer>>();
		boolean firstPlayerTurn = true;

		while (!queue.isEmpty()) {
			Integer currNum = queue.poll();
			if (currNum == -1) {
				firstPlayerTurn = !firstPlayerTurn;
				continue;
			}
			ArrayList<Integer> successors = memo.get(currNum);
			if (successors == null) {
				successors = generateSuccessors(currNum);
				memo.put(currNum, successors);
			}

			if (successors.size() == 0) {
				whoWon(firstPlayerTurn);
				 return;
			} else {
				for (Integer successor : successors) {
					if (isPowerOfTwo(successor + 1)) {
						whoWon(!firstPlayerTurn);
						return;
					}
					if (!queue.contains(successor))
						queue.add(successor);

				}
				queue.add(-1);
			}

		}

	}

	static void whoWon(boolean firstPlayerTurn) {
		if (firstPlayerTurn)
			System.out.println("Second Player");
		else
			System.out.println("First Player");

	}

	static boolean isPowerOfTwo(int x) {
		return (x & (x - 1)) == 0;
	}

	static ArrayList<Integer> generateSuccessors(int num) {
		ArrayList<Integer> al = new ArrayList<Integer>();

		int numBits = weight(num);
		int upperK = Math.round(Math.round(Math.log(num) / Math.log(2))) - 1;
		for (int i = 0; i <= upperK; i++) {
			int tempNum = num - (int) Math.pow(2, i);

			if (weight(tempNum) == numBits)
				al.add(tempNum);
		}
		return al;
	}

	static long numBits(int a) {
		if (a == 0)
			return 0;
		if (a % 2 == 0)
			return numBits(a - 1) + weight(a);
		return ((long) a + 1) / 2 + 2 * numBits(a / 2);
	}

	static int weight(int i) {
		i = i - ((i >> 1) & 0x55555555);
		i = (i & 0x33333333) + ((i >> 2) & 0x33333333);
		return (((i + (i >> 4)) & 0x0F0F0F0F) * 0x01010101) >> 24;
	}
}
