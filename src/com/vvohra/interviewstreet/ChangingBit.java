package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.BitSet;

public class ChangingBit {

	static BitSet a;
	static BitSet b;
	static BitSet c;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] lines = br.readLine().split(" ");

		N = Integer.parseInt(lines[0]);
		int Q = Integer.parseInt(lines[1]);

		a = initBitSet(br);
		b = initBitSet(br);
		c = new BitSet(N + 1);

		for (int i = 0; i < Q; i++) {
			String[] input = br.readLine().split(" ");
			int idx = new Integer(input[1]);

			if (input[0].equals("set_a")) {
				int val = new Integer(input[2]);
				changeBit(a, idx, val);
			} else if (input[0].equals("set_b")) {
				int val = new Integer(input[2]);
				changeBit(b, idx, val);
			} else
				printBitSet(idx);

		}
	}

	public static void changeBit(BitSet bs, int idx, int val) {
		if (val == 0) {
			bs.clear(idx);
		} else {
			bs.set(idx);
		}
	}

	public static void printBitSet(int idx) {

		int range_start = 0;
		for (int i = idx - 1; i >= 0; i--)
			if (!a.get(i) && !b.get(i)) {
				range_start = i;
				break;
			}

		BigInteger tempA;
		BigInteger tempB;
		try {
			tempA = new BigInteger(a.get(range_start, idx).toByteArray());
		} catch (Exception e) {
			tempA = new BigInteger("0");
		}
		try {
			tempB = new BigInteger(b.get(range_start, idx).toByteArray());
		} catch (Exception e) {
			tempB = new BigInteger("0");

		}
		BigInteger tempC = tempA.add(tempB);
		boolean carryOver = tempC.testBit(idx - range_start);

		boolean aBit = a.get(idx);
		boolean bBit = b.get(idx);

		if (carryOver) {
			if (aBit && bBit)
				System.out.print("1");
			else if (aBit ^ bBit)
				System.out.print("0");
			else
				// just carry over
				System.out.print("1");
		} else {
			if (aBit && bBit)
				System.out.print("0");
			else if (aBit ^ bBit)
				System.out.print("1");
			else
				// no carry over
				System.out.print("0");

		}

	}

	public static BitSet initBitSet(BufferedReader br) throws IOException {
		char[] buff = new char[N];

		br.read(buff);
		br.readLine();

		BitSet bs = new BitSet(N);
		int indexStr = buff.length - 1;
		int indexBS = 0;

		while (indexStr >= 0) {
			if (buff[indexStr] == '0')
				bs.clear(indexBS);
			else
				bs.set(indexBS);
			indexStr--;
			indexBS++;

		}

		return bs;
	}
}
