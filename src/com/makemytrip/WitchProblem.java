package com.makemytrip;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WitchProblem {

	public static void main(String args[] ) throws Exception {

        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
		/*File file = new File("C:/Users/Sundaram/Documents/My Docs/CodeEval/witchProblem.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));*/
        String line = buffer.readLine();
        long low, mid, high;
        int noOfTestCases = Integer.parseInt(line);
        for (int i = 0; i < noOfTestCases; i++) {
            line = buffer.readLine();
            line = line.trim();
        	String[] inputArray = line.split(" ");
        	low = Long.parseLong(inputArray[0]);
        	mid = Long.parseLong(inputArray[1]);
        	high = Long.parseLong(inputArray[2]);

			if (mid-low >= high-mid) {
				System.out.println(mid - low - 1);
			} else {
				System.out.println(high - mid - 1);
			} 
        }

    }
}
