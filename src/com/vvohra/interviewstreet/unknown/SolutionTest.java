package com.vvohra.interviewstreet.unknown;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Test;

public class SolutionTest {

	@Test
	public void testEqualsTo() {
		State state = new State(2, 3);
		state.input("1 1");

		State other = new State(2, 3);
		other.input("1 2");

		assertFalse(state.isEqual(other));

		other = new State(2, 3);
		other.input("1 1");
		assertTrue(state.isEqual(other));

	}

	@Test
	public void testSuccessors() {
		State state = new State(2, 3);
		state.input("2 1");

		ArrayList<State> successors = Solution.generateSuccessors(state);
		int x = 4;
	}

	@Test
	public void testCaseOne() {

		State state = new State(6, 4);
		state.input("4 2 4 3 1 1");

		State goal = new State(6, 4);
		goal.input("1 1 1 1 1 1");
		State res = Solution.run(state, goal);
		System.out.println(res);
		System.out.println(res.path.size());
		System.out.println(res.path);
	}

	@Test
	public void testCaseTwo() {

		State state = new State(2, 3);
		state.input("1 1");

		State goal = new State(2, 3);
		goal.input("2 2");
		State res = Solution.run(state, goal);
		System.out.println(res);
		System.out.println(res.path.size());
		System.out.println(res.path);
	}

}
