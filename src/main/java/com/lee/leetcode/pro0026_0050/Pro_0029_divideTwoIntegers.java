package com.lee.leetcode.pro0026_0050;

public class Pro_0029_divideTwoIntegers {

	public static void main(String[] args) {
		int dividend = -1375621307;
		int divisor =  -1102871504;
		System.out.println(dividend + divisor);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(dividend / divisor);
		System.out.println(divide(dividend, divisor));
	}

	public static int divide(int dividend, int divisor) {
		if(divisor == 0) { return Integer.MAX_VALUE; }
		if(dividend == 0) { return 0; }
		if(divisor == -1) {
			if(dividend == Integer.MIN_VALUE) { return Integer.MAX_VALUE; }
			return -dividend;
		}
		if(divisor == 1) { return dividend; }
		
		long nDend, nDsor;
		boolean isNegative = true;
		if(dividend > 0) {
			nDend = -dividend;
			if(divisor > 0) {
				nDsor = -divisor;
				isNegative = false;
			}else {
				nDsor = divisor;
			}
		}else {
			nDend = dividend;
			if(divisor > 0) {
				nDsor = -divisor;
			}else {
				nDsor = divisor;
				isNegative = false;
			}
		}
		
		if(nDend > nDsor) { return 0; }
		if(nDend == nDsor) { return isNegative ? -1 : 1; }
		
		long result = nDend - nDsor, d = nDsor;
		int q = 0, factor = 1;
		while(true) {
			if(result == 0) {
				q += factor;
				break;
			}else if(result < 0) {
				q += factor;
				nDend = result;
				factor *= 2;
				nDsor *= 2;
			}else {
				if(nDsor == d) { break; }
				factor /= 2;
				nDsor /= 2;
			}
			result = nDend - nDsor;
		}
        return isNegative ? -q : q;
    }
}
