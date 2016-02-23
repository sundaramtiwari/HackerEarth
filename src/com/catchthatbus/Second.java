package com.catchthatbus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Second {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		int i = 1;
		while (i <= t) {
			String str[] = br.readLine().split(" ");
			long x = Long.parseLong(str[0]);
			long y = Long.parseLong(str[1]);
			long total = x + y;
			BigInteger facx = factorialOfLatgeNo(x);
			BigInteger facy = factorialOfLatgeNo(y);
			BigInteger fact = factorialOfLatgeNo(total);
			BigInteger ans = fact;
			ans = ans.divide(facy);
			ans = ans.divide(facx);
			BigInteger modd = new BigInteger("1000000007");
			System.out.println("Case #" + i + ": " + ans.mod(modd));
			i++;
		}
	}

	public static BigInteger factorialOfLatgeNo(long n) {
		BigInteger fact = new BigInteger("1");
		for (int i = 1; i <= n; i++) {
			fact = fact.multiply(new BigInteger(i + ""));
		}
		return fact;
	}

}
