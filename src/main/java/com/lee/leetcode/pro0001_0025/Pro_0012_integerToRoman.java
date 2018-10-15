package com.lee.leetcode.pro0001_0025;

/**
 * 
Given an integer, convert it to a roman numeral.

Input is guaranteed to be within the range from 1 to 3999.
 * 
 */
public class Pro_0012_integerToRoman {

	public static void main(String[] args) {
		System.out.println(intToRoman1(3888));
	}
	
	private static String[] THU = new String[]{"", "M", "MM", "MMM"};
	private static String[] HUN = new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
	private static String[] TEN = new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
	private static String[] ONE = new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
	
	public static String intToRoman1(int num) {
		StringBuilder buf = new StringBuilder(15);
		buf.append(THU[num/1000]); num = num % 1000;
		buf.append(HUN[num/100]); num = num % 100;
		buf.append(TEN[num/10]); num = num % 10;
		buf.append(ONE[num]);
		return buf.toString();
	}
	
	public static String intToRoman(int num) {
		char[] buf = new char[15];
		int index = 0;
		index = thousands(num/1000, buf, index); num = num % 1000;
		index = hundreds(num/100, buf, index); num = num % 100;
		index = tens(num/10, buf, index); num = num % 10;
		index = ones(num, buf, index);
        return new String(buf, 0, index);
    }
	
	public static int thousands(int num, char[] buf, int index) {
		switch(num) {
		case 3: buf[index++]='M';
		case 2: buf[index++]='M';
		case 1: buf[index++]='M';
		default:
		}
		return index;
	}
	
	public static int hundreds(int num, char[] buf, int index) {
		switch(num) {
		case 9: buf[index++]='C'; buf[index++]='M'; break;
		case 8: buf[++index]='C';
		case 7: buf[++index]='C';
		case 6: buf[++index]='C';
		case 5: buf[(++index)-num+4] = 'D'; break;
		case 4: buf[index++]='C'; buf[index++]='D'; break;
		case 3: buf[index++]='C';
		case 2: buf[index++]='C';
		case 1: buf[index++]='C';
		default:
		}
		return index;
	}
	
	public static int tens(int num, char[] buf, int index) {
		switch(num) {
		case 9: buf[index++]='X'; buf[index++]='C'; break;
		case 8: buf[++index]='X';
		case 7: buf[++index]='X';
		case 6: buf[++index]='X';
		case 5: buf[(++index)-num+4] = 'L'; break;
		case 4: buf[index++]='X'; buf[index++]='L'; break;
		case 3: buf[index++]='X';
		case 2: buf[index++]='X';
		case 1: buf[index++]='X';
		default:
		}
		return index;
	}
	
	public static int ones(int num, char[] buf, int index) {
		switch(num) {
		case 9: buf[index++]='I'; buf[index++]='X'; break;
		case 8: buf[++index]='I';
		case 7: buf[++index]='I';
		case 6: buf[++index]='I';
		case 5: buf[(++index)-num+4] = 'V'; break;
		case 4: buf[index++]='I'; buf[index++]='V'; break;
		case 3: buf[index++]='I';
		case 2: buf[index++]='I';
		case 1: buf[index++]='I';
		default:
		}
		return index;
	}
}
