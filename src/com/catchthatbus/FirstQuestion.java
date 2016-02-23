package com.catchthatbus;

import java.io.DataInputStream;

public class FirstQuestion {

	@SuppressWarnings("resource")
	public static void main(String args[]) throws Exception {

		DataInputStream buffer = new DataInputStream(System.in);
		boolean flag = false;
		while (buffer.available() > 0) {
			int noOfTestCases = buffer.readInt();
			for (int i = 0; i < noOfTestCases; i++) {
				if (flag) {
					byte[] b = new byte[1000000];
					buffer.read(b);
					b.toString();
				}
			}
		}
		/*
		 * String line = buffer.readLine(); int noOfTestCases =
		 * Integer.parseInt(line); for (int i = 0; i < noOfTestCases; i++) {
		 * line = buffer.readLine(); line = line.trim(); String f =
		 * buffer.readLine(); String[] fArr = f.split(" "); String s =
		 * buffer.readLine(); String[] sArr = s.split(" ");
		 * 
		 * for (int j = 0; j < fArr.length; j++) { if (!fArr[j].equals(sArr[j]))
		 * System.out.println(fArr[j] + " " + sArr[j]); } }
		 */

	}
}
