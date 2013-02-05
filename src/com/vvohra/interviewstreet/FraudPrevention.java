package com.vvohra.interviewstreet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.TreeSet;

public class FraudPrevention {
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String line = br.readLine();
		int N = Integer.parseInt(line);

		Hashtable<String, ArrayList<Record>> firstCase = new Hashtable<>();
		Hashtable<String, ArrayList<Record>> secondCase = new Hashtable<>();

		for (int i = 0; i < N; i++) {
			Record record = new Record(br.readLine());
			addOrInitializeRecord(record, record.emailAndDealID, firstCase);
			addOrInitializeRecord(record, record.addressAndDealID, secondCase);
		}

		TreeSet<Integer> pq = new TreeSet<>();
		checkCases(firstCase, pq);
		checkCases(secondCase, pq);

		printPriorityQueue(pq);

	}

	public static void printPriorityQueue(TreeSet<Integer> pq) {
		StringBuffer sb = new StringBuffer();
		String delim = "";
		for (Integer i : pq) {
			sb.append(delim).append(i);
			delim = ",";
		}
		System.out.println(sb.toString());
	}

	public static void checkCases(Hashtable<String, ArrayList<Record>> hs,
			TreeSet<Integer> pq) {
		Iterator<Entry<String, ArrayList<Record>>> iter = hs.entrySet()
				.iterator();
		while (iter.hasNext()) {
			Entry<String, ArrayList<Record>> entry = iter.next();
			if (entry.getValue().size() == 1)
				continue;
			Hashtable<String, ArrayList<Record>> smaller_hs = new Hashtable<>();
			Iterator<Record> alIterator = entry.getValue().iterator();

			while (alIterator.hasNext()) {
				Record record = alIterator.next();
				addOrInitializeRecord(record, record.credit_card, smaller_hs);
			}

			Iterator<Entry<String, ArrayList<Record>>> smallerHSIterator = smaller_hs
					.entrySet().iterator();

			while (smallerHSIterator.hasNext()) {
				Entry<String, ArrayList<Record>> smaller_hs_entry = smallerHSIterator
						.next();
				if (smaller_hs_entry.getValue().size() > 1)
					continue;
				for (Record record : entry.getValue())
					pq.add(record.order_id);
			}

		}
	}

	public static void addOrInitializeRecord(Record record, String key,
			Hashtable<String, ArrayList<Record>> hs) {
		if (hs.get(key) == null)
			hs.put(key, new ArrayList<Record>());
		hs.get(key).add(record);

	}
}

class Record {
	public void setRecord(int order_id, int deal_id, String email,
			String address, String city, String state, String zip_code,
			String credit_card) {

		this.order_id = order_id;
		this.deal_id = deal_id;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
		this.zip_code = zip_code;
		this.credit_card = credit_card;
		this.emailAndDealID = email + deal_id;
		this.addressAndDealID = address + city + state + zip_code + deal_id;
	}

	public Record(String input) {
		String[] str = input.split(",");
		setRecord(Integer.parseInt(str[0]), Integer.parseInt(str[1]),
				formatEmail(str[2]), expandAddressAbbr(str[3]), str[4]
						.toUpperCase().trim(), expandStateAbbr(str[5]), str[6]
						.toUpperCase().trim(), str[7].toUpperCase().trim());
	}

	int order_id, deal_id;
	String email, address, city, state, credit_card, zip_code;
	String emailAndDealID, addressAndDealID;

	public static String expandAddressAbbr(String str) {
		return str.toUpperCase().trim().replace("ST.", "STREET").replace("RD.", "ROAD");
	}

	public static String expandStateAbbr(String str) {
		return str.toUpperCase().trim().replace("IL", "ILLINOIS").replace("CA", "CALIFORNIA")
				.replace("NY", "NEW YORK").toUpperCase().trim();

	}

	public static String formatEmail(String str) {
		
		str = str.replace(".", "").replaceFirst("\\+.*@", "@").toUpperCase()
				.trim();
		return str;

	}
}