package com.vvohra.interviewstreet.unknown;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] inputStr = br.readLine().split(" ");
		int N = Integer.parseInt(inputStr[0]);
		int K = Integer.parseInt(inputStr[1]);

		State start = new State(N, K);
		start.input(br.readLine());
		State goal = new State(N, K);
		goal.input(br.readLine());

		State res = run(start, goal);
		System.out.println(res.path.size());
		for (String str : res.path)
			System.out.println(str);

	}

	public static State run(State startState, State goalState) {
		LinkedList<State> queue = new LinkedList<State>();
		queue.add(startState);
		HashSet<State> visited = new HashSet<State>();
		while (queue.size() > 0) {
			State currState = queue.removeFirst();
			if (currState.isEqual(goalState))
				return currState;
			ArrayList<State> successors = generateSuccessors(currState);
			for (State successor : successors) {
				if (!visited.contains(successor)) {
					visited.add(successor);
					queue.add(successor);
				}
			}

		}
		return null;
	}

	public static ArrayList<State> generateSuccessors(State state) {
		ArrayList<State> successors = new ArrayList<State>();

		for (int i = 0; i < state.towers.size(); i++) {
			Stack<Integer> currStack = state.towers.get(i);
			if (currStack.size() == 0)
				continue;
			Integer num = currStack.pop();
			for (int j = 0; j < state.towers.size(); j++) {
				if (i == j || state.towers.get(j).size() > 0
						&& state.towers.get(j).peek() < num)
					continue;
				State newState = (State) state.clone();
				newState.towers.get(j).push(num);
				newState.path.add((i + 1) + " " + (j + 1));
				successors.add(newState);
			}
			currStack.push(num);
		}
		return successors;
	}
}

class State {
	ArrayList<Stack<Integer>> towers;
	ArrayList<String> path = new ArrayList<String>();
	int n, k;

	public State(int n, int k) {
		this.n = n;
		this.k = k;
		towers = new ArrayList<Stack<Integer>>();
		for (int i = 0; i < k; i++)
			towers.add(new Stack<Integer>());
	}

	public State() {

	}

	public void input(String str) {
		int count = 1;
		StringTokenizer strtok = new StringTokenizer(str);
		Hashtable<Integer, ArrayList<Integer>> unsorted = new Hashtable<Integer, ArrayList<Integer>>();
		while (strtok.hasMoreTokens()) {
			String tempString = strtok.nextToken();
			int num = new Integer(tempString) - 1;
			if (unsorted.get(num) == null)
				unsorted.put(num, new ArrayList<Integer>());
			unsorted.get(num).add(count);
			count++;
		}
		Iterator<Integer> it = (Iterator<Integer>) unsorted.keys();
		while (it.hasNext()) {
			Integer curr = it.next();
			Collections.sort(unsorted.get(curr));
			ArrayList<Integer> sortedList = unsorted.get(curr);
			for (int i = sortedList.size() - 1; i >= 0; i--) {
				towers.get(curr).push(sortedList.get(i));
			}
		}
	}

	protected Object clone() {
		// TODO Auto-generated method stub
		State state = new State(this.n, this.k);
		for (int i = 0; i < towers.size(); i++) {
			Iterator<Integer> it = towers.get(i).iterator();
			while (it.hasNext())
				state.towers.get(i).push(it.next());
		}

		Iterator<String> it = this.path.iterator();
		while (it.hasNext())
			state.path.add(it.next());

		return state;
	}

	public boolean isEqual(State goalState) {
		for (int i = 0; i < this.towers.size(); i++) {
			if (this.towers.get(i).size() != goalState.towers.get(i).size())
				return false;
			for (int j = 0; j < this.towers.get(i).size(); j++) {

				int thisNum = this.towers.get(i).get(j);
				int otherNum = goalState.towers.get(i).get(j);
				if (thisNum != otherNum)
					return false;
			}
		}
		return true;

	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < this.towers.size(); i++)
			sb.append(this.towers.get(i).toString());
		return sb.toString();
	}
}
