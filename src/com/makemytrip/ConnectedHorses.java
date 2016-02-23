package com.makemytrip;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ConnectedHorses {

	private static long count = 0;
	private static long mod = 1000000007;

	public static void main(String[] args) throws IOException {
		File file = new File("C:/Users/Sundaram/Documents/My Docs/CodeEval/connectedHorses.txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
		// BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
        int noOfTestCases = 0, noOfRows, noOfColumns, noOfHorses;
        int[][] horseMatrix = null;
        String line;

        line = buffer.readLine();
        line = line.trim();
    	noOfTestCases = Integer.parseInt(line);

    	while (noOfTestCases > 0) {
        	line = buffer.readLine();
        	line = line.trim();

        	String[] inpArr = line.split(" ");
        	noOfRows = Integer.parseInt(inpArr[0]);
        	noOfColumns = Integer.parseInt(inpArr[1]);
        	noOfHorses = Integer.parseInt(inpArr[2]);

        	horseMatrix = new int[noOfRows][noOfColumns];
        	populateHorseMatrix(buffer, horseMatrix, noOfHorses);

        	for (int i = 0; i < horseMatrix.length; i++) {
				for (int j = 0; j < horseMatrix[0].length; j++) {
					if (horseMatrix[i][j] == 1) {
						testForMove(horseMatrix, i, j, noOfRows, noOfColumns, -1, -1);
					}
				}
			}

        	System.out.println(count%mod);
        	count = 0;
        	horseMatrix = null;
        	noOfTestCases--;
        }
	}

	private static void testForMove(int[][] horseMatrix, int i, int j, int noOfRows, int noOfColumns, int prohibitedRow,
			int prohibitedColumn) {
		if (noOfColumns > 1) {
			checkTop(horseMatrix, i, j, noOfColumns, noOfColumns, prohibitedRow, prohibitedColumn);
			checkBottom(horseMatrix, i, j, noOfRows, noOfColumns, prohibitedRow, prohibitedColumn);
		}

		if (noOfRows > 1) {
			checkLeft(horseMatrix, i, j, noOfRows, noOfColumns, prohibitedRow, prohibitedColumn);
			checkRight(horseMatrix, i, j, noOfRows, noOfColumns, prohibitedRow, prohibitedColumn);
		}
	}

	private static void checkRight(int[][] horseMatrix, int i, int j, int noOfRows, int noOfColumns, int prohibitedRow, int prohibitedColumn) {
		if (j < noOfColumns-2 && j+2 != prohibitedColumn) {
			if (i == 0) {
				if (horseMatrix[i+1][j+2] == 1 && i+1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i+1, j+2, noOfRows, noOfColumns, i, j);
				}

			} else if (i == horseMatrix.length - 1){
				if (horseMatrix[i-1][j+2] == 1 && i-1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i-1, j+2, noOfRows, noOfColumns, i, j);
				}
				
			} else if (i > 0 && j < horseMatrix.length - 1){
				if (horseMatrix[i+1][j+2] == 1 && i+1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i+1, j+2, noOfRows, noOfColumns, i, j);
				}
				if (horseMatrix[i-1][j+2] == 1 && i-1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i-1, j+2, noOfRows, noOfColumns, i, j);
				}
			}
		}
		
	}

	private static void checkLeft(int[][] horseMatrix, int i, int j,
			int noOfRows, int noOfColumns, int prohibitedRow, int prohibitedColumn) {
		if (j >= 2 && j-2 != prohibitedColumn) {
			if (i == 0) {
				if (horseMatrix[i+1][j-2] == 1 && i+1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i+1, j-2, noOfRows, noOfColumns, i, j);
				}

			} else if (i == horseMatrix.length - 1){
				if (horseMatrix[i-1][j-2] == 1 && i-1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i-1, j-2, noOfRows, noOfColumns, i, j);
				}
				
			} else if (i > 0 && j < horseMatrix.length - 1){
				if (horseMatrix[i+1][j-2] == 1 && i+1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i+1, j-2, noOfRows, noOfColumns, i, j);
				}
				if (horseMatrix[i-1][j-2] == 1 && i-1 != prohibitedRow) {
					count++;count%=mod;
					testForMove(horseMatrix, i-1, j-2, noOfRows, noOfColumns, i, j);
				}
			}
		}
		
	}

	private static void checkBottom(int[][] horseMatrix, int i, int j, int noOfRows, int noOfColumns, int prohibitedRow, int prohibitedColumn) {
		if (i < noOfRows-2 && i+2 != prohibitedRow) {

			if (j == 0) {
				if (horseMatrix[i+2][j+1] == 1 && j+1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i+2, j+1, noOfRows, noOfColumns, i, j);
				}

			} else if (j == horseMatrix[i].length - 1){
				if (horseMatrix[i+2][j-1] == 1 && j-1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i+2, j-1, noOfRows, noOfColumns, i, j);
				}
				
			} else {
				if (horseMatrix[i+2][j+1] == 1 && j+1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i+2, j+1, noOfRows, noOfColumns, i, j);
				}
				if (horseMatrix[i+2][j-1] == 1 && j-1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i+2, j-1, noOfRows, noOfColumns, i, j);
				}
			}
		}
	}

	private static void checkTop(int[][] horseMatrix, int i, int j, int noOfRows, int noOfColumns, int prohibitedRow
			, int prohibitedColumn) {
		if (i >= 2 && i-2 != prohibitedRow) {

			if (j == 0) {
				if (horseMatrix[i-2][j+1] == 1 && j+1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i-2, j+1, noOfRows, noOfColumns, i, j);
				}

			} else if (j == horseMatrix[i].length - 1){
				if (horseMatrix[i-2][j-1] == 1 && j-1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i-2, j-1, noOfRows, noOfColumns, i, j);
				}
				
			} else if (j > 0 && j < horseMatrix[i].length - 1){
				if (horseMatrix[i-2][j+1] == 1 && j+1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i-2, j+1, noOfRows, noOfColumns, i, j);
				}
				if (horseMatrix[i-2][j-1] == 1 && j-1 != prohibitedColumn) {
					count++;count%=mod;
					testForMove(horseMatrix, i-2, j-1, noOfRows, noOfColumns, i, j);
				}
			}
		}
	}

	private static void populateHorseMatrix(BufferedReader buffer,
			int[][] horseMatrix, int noOfHorses) throws IOException {
		String line;
		int row, column;
		for (int i = 0; i < noOfHorses; i++) {
			line = buffer.readLine();
			line = line.trim();

			String[] horseArr = line.split(" ");
			row = Integer.parseInt(horseArr[0]);
			column = Integer.parseInt(horseArr[1]);

			horseMatrix[--row][--column] = 1;
		}
		
	}

}
