package model;

import java.util.ArrayList;

public class SmallTests {
	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<>();

		for (int i = 0; i < 25; i++) {
			a.add(i);
		}

		System.out.println(a.get(20));

		ArrayList<ArrayList<Integer>> a2d = new ArrayList<ArrayList<Integer>>();

		int s = 0;
		for (int i = 0; i < 10; i++) { // filling in the 2d arraylist
			a2d.add(new ArrayList<Integer>());
			for (int j = 0; j < 10; j++) {
				a2d.get(i).add(s);
				s++;
			}
		}

		for (ArrayList<Integer> array : a2d) { // printing out the 2d arraylist

			System.out.println(array);
		}
		// accessing a specific point of a 2d arraylist
		System.out.println(a2d.get(2).get(9));

		ArrayList<ArrayList<Integer>> a2dx = new ArrayList<ArrayList<Integer>>(10);

		LifeSquare lsq1 = new LifeSquare(8, 12, 10, 10, true);
		LifeSquare lsq2 = new LifeSquare(2, 50, 10, 10, false);

		System.out.println(lsq1);
		System.out.println(lsq2);
	}
}
