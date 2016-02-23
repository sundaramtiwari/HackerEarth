package number.theory;

import com.hackerearth.util.NumUtil;

public class ZerosInNFactorial {

	public static void main(String[] args) {
		long n = Long.parseLong("80591783621");
		long noOfFives = getNumOfFives(n);
		System.out.println(noOfFives);
	}

	private static long getNumOfFives(long n) {
		long mul = 5;
		long count = 0;
		long quo = n/mul;
		while (quo > 0) {
			count += quo;
			mul *= 5;
			quo = n/mul;
		}
		return count;
	}

	private static int getNumOfZerosInFactOf(int n) {
		
		return 0;
	}

}
