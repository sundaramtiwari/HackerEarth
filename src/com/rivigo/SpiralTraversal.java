/**
 * 
 */
package com.rivigo;

/**
 * @author Sundaram
 *
 */
public class SpiralTraversal {

	/**
	 *  1    2   3   4
		5    6   7   8
		9   10  11  12
		13  14  15  16
		17  18  19  20
	 */
	public static void main(String[] args) {
		int[][] input = new int[][] {{1,2,3,4,}, {5,6,7,8}, 
				{9, 10, 11, 12}, {13, 14, 15, 16}, {17, 18, 19, 20}};

		spiralTraversal(input);
	}

	public static void spiralTraversal (int[][] input) {
		
		if (input != null) {
			// Temp matrix to hold data and track traversal
			int[][] temp = new int[input.length][input[0].length];
			travelRight(input, temp, 0, 0);
		} else {
			System.out.println("Input matrix is null!");
		}
	}

	public static void travelRight(int[][] input, int[][] temp, int row, int column) {
		while (column < input[row].length && temp[row][column] != 1) {
			System.out.print(input[row][column] + " ");
			temp[row][column] = 1;
			column++;
		}

		if (temp[++row][--column] == 0) {
			travelDown(input, temp, row, column);
		}
	}
	
	public static void travelDown(int[][] input, int[][] temp, int row, int column) {
		while (row < input.length && temp[row][column] != 1) {
			System.out.print(input[row][column] + " ");
			temp[row][column] = 1;
			row++;
		}

		if (temp[--row][--column] == 0) {
			travelLeft(input, temp, row, column);
		}
	}

	public static void travelLeft(int[][] input, int[][] temp, int row, int column) {
		while (column >= 0 && temp[row][column] != 1) {
			System.out.print(input[row][column] + " ");
			temp[row][column] = 1;
			column--;
		}

		if (temp[--row][++column] == 0) {
			travelUp(input, temp, row, column);
		}
	}
	
	public static void travelUp(int[][] input, int[][] temp, int row, int column) {
		while (row >= 0 && temp[row][column] != 1) {
			System.out.print(input[row][column] + " ");
			temp[row][column] = 1;
			row--;
		}

		if (temp[++row][++column] == 0) {
			travelRight(input, temp, row, column);
		}
	}
}
