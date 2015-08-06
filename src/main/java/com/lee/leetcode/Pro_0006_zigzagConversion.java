package com.lee.leetcode;

public class Pro_0006_zigzagConversion {

	public static void main(String[] args) {
		/**
		 * "PAYPALISHIRING" with 3 rows zigzag pattern like this:
		 * P   A   H   N
		 * A P L S I I G
		 * Y   I   R
		 * 
		 * P Y A I H R N
		 * A P L S I I G
		 * 
		 * P     I     N
		 * A   L S   I G
		 * Y A   H R
		 * P     I
		 * 
		 * P       H
		 * A     S I
		 * Y   I   R
		 * P L     I G
		 * A       N
		 * 
		 * convert("PAYPALISHIRING", 3) to: "PAHNAPLSIIGYIR"
		 */
		String s = "A";
		int numRows = 2;
		System.out.println(convert(s, numRows));
	}

	public static String convert(String s, int numRows) {
		if(numRows <= 1) { return s; }
		int len = s.length();
		if(len <= numRows) { return s; }
		char[] array = new char[len];
		int index = 0;
		int maxRow = numRows - 1;
		int trip = 2 * maxRow;
		for(int row=0; row<numRows; row++) {
			int location = row;
			int gap = row == maxRow ? trip : 2*(maxRow-row);
			do {
				array[index++] = s.charAt(location);
				location += gap;
				if(gap < trip) { gap = trip - gap; }
			}while(location < len);
		}
        return new String(array);
    }
}
