package com.hackerrank.counting_valleys;

import java.io.IOException;

public class Solution_1 {

	public static int countingValleys(int steps, String path) {
		System.out.println(path);

		int prevLevel = 0;
		int currLevel = 0;

		int mountains = 0;
		int valleys = 0;

		for (int i = 0; i < path.length(); i++) {
			char c = (char) path.charAt(i);
			prevLevel = currLevel;

			if ('U' == c) {
				currLevel += 1;
			}
			else if ('D' == c) {
				currLevel -= 1;
			}

			// Count regions on entry, 
			// upon passing threshold (1, -1) 
			// and coming from certain position (0).

			if (prevLevel == 0) {
				if (currLevel == 1) {
					mountains += 1;
				}
				else if (currLevel == -1) {
					valleys += 1;
				}
			}

			// System.out.println(String.format("%c mountains: %d valleys: %d currLevel: %d", c, mountains, valleys, currLevel));
		}

		System.out.println(String.format("Mountains: %d Valleys: %d", mountains, valleys));

		return valleys;
	}

	private static void test(int steps, String path, int expected) {
		int result = countingValleys(steps, path);

		System.out.println("Got: " + result + " Expected: " + expected);
		System.out.println(result == expected ? "OK" : "KO");
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		test(8, "DDUUUUDD", 1);

		test(8, "UDDDUDUU", 1);
	}
}
