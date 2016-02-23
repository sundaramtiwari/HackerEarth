package com.bestsol;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ShiftMultiplication {

	public static void main(String[] args) throws IOException {
		File file = new File(
				"C:/Users/Sundaram/Documents/My Docs/CodeEval/shiftMul.txt");
		BufferedReader buffer = new BufferedReader(new FileReader(file));

		// BufferedReader buffer = new BufferedReader(new
		// InputStreamReader(System.in));
		String line = buffer.readLine();
		line = line.trim();
		int numOfInputs = Integer.parseInt(line);

		for (int i = 0; i < numOfInputs; i++) {
			line = buffer.readLine();
			line = line.trim();
			String[] values = line.split(" ");
			long a = Long.valueOf(values[0]);
			long b = Long.valueOf(values[1]);
			
			if (b % 2 == 0)
				multiplyEven(a, b);
			else 
				multiplyOdd(a, b);
		}

	}

	private static void multiplyOdd(long a, long b) {
		if (b > 1) {
			long half = (long) Math.floor(b/2);
			System.out.println("(" + a + "<<" + half + ") " 
			+ "+ (" + a + "<<" + 0 + ")");
		} else if (b==1) {
			System.out.println("(" + a + "<<" + 0 + ")");
		}
	}

	private static void multiplyEven(long a, long b) {
		System.out.println("(" + a + "<<" + b/2 + ")");
	}

}
