package com.makemytrip;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PagalGhoda {

	private static long count = 0;
	private static long mod = 1000000007;
	private static Set<String> connSet = null;


	public static void main(String[] args) throws IOException {
		//File file = new File("C:/Users/Sundaram/Documents/My Docs/CodeEval/connectedHorses.txt");
        //BufferedReader buffer = new BufferedReader(new FileReader(file));
        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
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

        	connSet = new HashSet<String>();

        	for (int i = 0; i < horseMatrix.length; i++) {
				for (int j = 0; j < horseMatrix[0].length; j++) {
					if (horseMatrix[i][j] == 1) {
						testForMove(horseMatrix, i, j, noOfRows, noOfColumns, -1, -1);
					}
				}
			}

        	List<Integer> connList = new ArrayList<Integer>();
        	for (String string : connSet) {
				String[] strArr = string.split(",");
				connList.add(strArr.length);
			}
        	count = 1;
        	for(int i : connList) {
        		count *= getFactorial(i);
        		count = count%mod;
        	}
        	System.out.println(count);
        	horseMatrix = null;
        	noOfTestCases--;
        }
	}

	private static long getFactorial(int n) {
		int result = 1;
	       for (int i = 1; i <= n; i++) {
	           result = result * i;
	       }
		return result;
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
					updateSets(i, j, i+1, j+2);
				}

			} else if (i == horseMatrix.length - 1){
				if (horseMatrix[i-1][j+2] == 1 && i-1 != prohibitedRow) {
					updateSets(i, j, i-1, j+2);
				}
				
			} else if (i > 0 && j < horseMatrix.length - 1){
				if (horseMatrix[i+1][j+2] == 1 && i+1 != prohibitedRow) {
					updateSets(i, j, i+1, j+2);
				}
				if (horseMatrix[i-1][j+2] == 1 && i-1 != prohibitedRow) {
					updateSets(i, j, i-1, j+2);
				}
			}
		}
		
	}

	private static void updateSets(int i, int j, int k, int l) {
		String horse1 = String.valueOf(i) + String.valueOf(j);
		String horse2 = String.valueOf(k) + String.valueOf(l);
		String conn = null;
		
		for (String str : connSet) {
			if (str.contains(horse1) && str.contains(horse2))
				return;
			if (str.contains(horse1) && !str.contains(horse2)) {
				conn = str;
				break;
			}
		}

		if (conn != null) {
			connSet.remove(conn);
			conn += "," + horse2;
			connSet.add(conn);
		} else {
			connSet.add(horse1 + "," + horse2);
		}
		
	}

	private static void checkLeft(int[][] horseMatrix, int i, int j,
			int noOfRows, int noOfColumns, int prohibitedRow, int prohibitedColumn) {
		if (j >= 2 && j-2 != prohibitedColumn) {
			if (i == 0) {
				if (horseMatrix[i+1][j-2] == 1 && i+1 != prohibitedRow) {
					updateSets(i, j, i+1, j-2);
				}

			} else if (i == horseMatrix.length - 1){
				if (horseMatrix[i-1][j-2] == 1 && i-1 != prohibitedRow) {
					updateSets(i, j, i-1, j-2);
				}
				
			} else if (i > 0 && j < horseMatrix.length - 1){
				if (horseMatrix[i+1][j-2] == 1 && i+1 != prohibitedRow) {
					updateSets(i, j, i+1, j-2);
				}
				if (horseMatrix[i-1][j-2] == 1 && i-1 != prohibitedRow) {
					updateSets(i, j, i-1, j-2);
				}
			}
		}
		
	}

	private static void checkBottom(int[][] horseMatrix, int i, int j, int noOfRows, int noOfColumns, int prohibitedRow, int prohibitedColumn) {
		if (i < noOfRows-2 && i+2 != prohibitedRow) {

			if (j == 0) {
				if (horseMatrix[i+2][j+1] == 1 && j+1 != prohibitedColumn) {
					updateSets(i, j, i+2, j+1);
				}

			} else if (j == horseMatrix[i].length - 1){
				if (horseMatrix[i+2][j-1] == 1 && j-1 != prohibitedColumn) {
					updateSets(i, j, i+2, j-1);
				}
				
			} else {
				if (horseMatrix[i+2][j+1] == 1 && j+1 != prohibitedColumn) {
					updateSets(i, j, i+2, j+1);
				}
				if (horseMatrix[i+2][j-1] == 1 && j-1 != prohibitedColumn) {
					updateSets(i, j, i+2, j-1);
				}
			}
		}
	}

	private static void checkTop(int[][] horseMatrix, int i, int j, int noOfRows, int noOfColumns, int prohibitedRow
			, int prohibitedColumn) {
		if (i >= 2 && i-2 != prohibitedRow) {

			if (j == 0) {
				if (horseMatrix[i-2][j+1] == 1 && j+1 != prohibitedColumn) {
					updateSets(i, j, i-2, j+1);
				}

			} else if (j == horseMatrix[i].length - 1){
				if (horseMatrix[i-2][j-1] == 1 && j-1 != prohibitedColumn) {
					updateSets(i, j, i-2, j-1);
				}
				
			} else if (j > 0 && j < horseMatrix[i].length - 1){
				if (horseMatrix[i-2][j+1] == 1 && j+1 != prohibitedColumn) {
					updateSets(i, j, i-2, j+1);
				}
				if (horseMatrix[i-2][j-1] == 1 && j-1 != prohibitedColumn) {
					updateSets(i, j, i-2, j-1);
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
