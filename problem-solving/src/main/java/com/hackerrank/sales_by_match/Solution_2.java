package com.hackerrank.sales_by_match;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution_2 {

	private static class Result {

		public Map<Integer, Integer> groups = new HashMap<>();
		public int pairs = 0;
		public int total = 0;

		@Override
		public String toString() {
			return "Result { "
				+ " pairs: " + pairs + ", " 
				+ " total: " + total + ", "
				+ " leftovers: " + (total - 2 * pairs) + " }";
		}
	}
	
	private static Result findResult(int n, int[] ar) {
		return Arrays.stream(ar)
		.collect(Result::new, 
			(Result r, int e) -> {
				// System.out.println("accumulator: ");
				Integer count = r.groups.get(e);

				if (count == null) {
					count = 1;
				} else {
					count += 1;
				}

				// System.out.println("e: " + e + " count: " + count);
				r.groups.put(e, count);
				r.total += 1;

				if (count > 0 && count % 2 == 0) {
					// System.out.println("pair for e: " + e);
					r.pairs += 1;
				}
			}, 
			(Result r1, Result r2) -> {
				// System.out.println("combiner: ");
				r1.groups.putAll(r2.groups);
				r1.pairs += r2.pairs;
			}
		);
	}

	private static int sockMerchant(int n, int[] ar) {
		Result result = findResult(n, ar);
		System.out.println(result);
		return result.pairs;
	}

	private static void test(int n, int[] ar, int expected) {
		int result = sockMerchant(n, ar);

		System.out.println("Got: " + result + " Expected: " + expected);
		System.out.println(result == expected ? "OK" : "KO");
		System.out.println();
	}

	public static void main(String[] args) throws IOException {

		test(7, new int[] { 1, 2, 1, 2, 1, 3, 2 }, 2);

		test(9, new int[] { 10, 20, 20, 10, 10, 30, 50, 10, 20 }, 3);
	}
}
