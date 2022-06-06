package com.hackerrank.counting_valleys;

import java.io.IOException;

public class Solution_2 {

	private static class Result {

		public int prevLevel = 0;
		public int currLevel = 0;

		public int mountains = 0;
		public int valleys = 0;

		@Override
		public String toString() {
			return String.format("Mountains: %d Valleys: %d", mountains, valleys);
		}
	}

	public static Result findResult(int steps, String path) {
		return path.chars()
		.collect(Result::new, 
			(Result r, int e) -> {
				char c = (char) e;
				r.prevLevel = r.currLevel;

				if ('U' == c) {
					r.currLevel += 1;
				}
				else if ('D' == c) {
					r.currLevel -= 1;
				}

				// Count regions on entry, 
				// upon passing threshold (1, -1) 
				// and coming from certain position (0).

				if (r.prevLevel == 0) {
					if (r.currLevel == 1) {
						r.mountains += 1;
					}
					else if (r.currLevel == -1) {
						r.valleys += 1;
					}
				}

				// System.out.println(String.format("%c mountains: %d valleys: %d currLevel: %d", c, r.mountains, r.valleys, r.currLevel));
			},
			(Result r1, Result r2) -> {
				r1.mountains += r2.mountains;
				r1.valleys += r2.valleys;
			}
		);
	}

	public static int countingValleys(int steps, String path) {
		System.out.println(path);
		Result result = findResult(steps, path);
		System.out.println(result);
		return result.valleys;
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
