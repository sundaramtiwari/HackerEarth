package com.hackerearth.util;

public class NumUtil {

	public static long getFactorial(int no) {
		long fact = 1;
		if (no > 0) {
			for (int i = 2; i <= no; i++) {
				fact *= i;
			}
		}
		return fact;
	}
}
