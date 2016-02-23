package com.craftsvilla;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.TreeMap;

public class LuckyNumbers {

	private static long mod = 1000000007;
	private static TreeMap<Long, Long> luckyCache = new TreeMap<Long, Long>(
			Collections.reverseOrder());

	public static void main(String[] args) throws IOException {
		
		/*File file = new File("C:/Users/Sundaram/Documents/My Docs/CodeEval/luckyNumbers.txt");
		BufferedReader buffer = new BufferedReader(new FileReader(file));*/
		 
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String line = buffer.readLine();
		line = line.trim();
		int noOfTestCases = Integer.parseInt(line);

		while (noOfTestCases > 0) {
			line = buffer.readLine();
			line = line.trim();
			long number = Long.parseLong(line);

			printLuckNumerSum(number);
			noOfTestCases--;
		}
	}

	private static void printLuckNumerSum(long number) {
		long bitCount, sum = 0;
		int index = 1;

		boolean exists = checkInCache(number, index, sum);

		if (!exists) {
			for (; index <= number; index++) {
				bitCount = Long.bitCount(index);
				if (bitCount == 2) {
					sum += index;
					sum = sum % mod;
				}
			}
			luckyCache.put(number, sum);
			System.out.println(sum);
			System.out.println(luckyCache);
		}
	}

	private static boolean checkInCache(long number, long index, long sum) {
		boolean exists = false;
		if (luckyCache.containsKey(number)) {
			System.out.println(luckyCache.get(number));
			exists = true;
		} else if (!luckyCache.isEmpty() && number > luckyCache.get(luckyCache.firstKey())) {
			index = luckyCache.firstKey();
			sum = luckyCache.get(index);
		}

		return exists;
	}

}
