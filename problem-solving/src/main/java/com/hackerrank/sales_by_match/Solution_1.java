package com.hackerrank.sales_by_match;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Solution_1 {

	private static int sockMerchant(int n, int[] ar) {
		Map<Integer, Integer> groups = new HashMap<>();
		int pairs = 0;
		int total = 0;

		for (int e : ar) {
			Integer count = groups.get(e);

			if (count == null) {
				count = 1;
			} else {
				count += 1;
			}

			// System.out.println("e: " + e + " count: " + count);
			groups.put(e, count);
			total += 1;

			if (count > 0 && count % 2 == 0) {
				// System.out.println("pair for e: " + e);
				pairs += 1;
			}
		}

		System.out.println("Result { "
			+ " pairs: " + pairs + ", " 
			+ " total: " + total + ", "
			+ " leftovers: " + (total - 2 * pairs) + " }");

		return pairs;
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
