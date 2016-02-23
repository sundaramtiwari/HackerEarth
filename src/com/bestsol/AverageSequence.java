package com.bestsol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AverageSequence {

	public static void main(String[] args) throws IOException {
		BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		String line = buffer.readLine();

		line = buffer.readLine();
		line = line.trim();
		String[] values = line.split(" ");
		long sum = 0;
		
		for (int i = 0; i < values.length; i++) {
			long val = Long.valueOf(values[i]);
			
			if (i > 0) {
				long num = ((i+1) * val) - sum;
				sum += num;
				System.out.print(" " + num);
			} else {
				sum += val;
				System.out.print(val);
			}
		}
	}

}
