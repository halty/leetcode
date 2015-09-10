package com.lee.leetcode.pro0001_0025;

/*
 * 
Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

Note: You may not slant the container.
 * 
 */
public class Pro_0011_containerWithMostWater {

	public static void main(String[] args) {
		int max = 15000;
		int[] height = new int[max];
		for(int i=0; i<max; i++) {
			height[i] = max - i;
		}

		System.out.println(maxArea(height));
		System.out.println(maxArea3(height));
	}
	
	public static int maxArea(int[] height) {
		int len = height.length;
		int end = len - 1;
		int area = 0;
		for(int i=0; i<end; i++) {
			int l = height[i];
			for(int j=i+1; j<len; j++) {
				int r = height[j];
				int tmp = (j-i)*(l < r ? l : r);
				if(tmp > area) { area = tmp; }
			}
		}
		
        return area;
    }
	
	public static int maxArea1(int[] height) {
		int len = height.length;
		int area = 0;
		for(int gap=1; gap<len; gap++) {
			int h = 0;
			for(int first=0, second=first+gap; second<len; first++, second++) {
				int l = height[first], r = height[second];
				int curH = l < r ? l : r;
				if(h < curH) { h = curH; }
			}
			int tmp = gap * h;
			if(tmp > area) { area = tmp; }
		}
		
        return area;
    }
	
	public static int maxArea2(int[] height) {
		int len = height.length;
		int l = 0, r = 1, min = Math.min(height[l], height[r]);
        int area = min;
        for(int i=2; i<len; i++) {
        	int h = height[i];
        	int lb, rb;
        	if(h < min) {
        		lb = 0; rb = l;
        	}else {
        		lb = l; rb = r;
        	}
        	for(int li=lb, d=i-li, tMin=0, tArea=0; li<=rb; li++, d--) {
    			int hl = height[li];
        		if(hl <= tMin) { continue; }
    			tMin = Math.min(hl, h);
    			tArea = d * tMin;
    			if(tArea > area) {
    				area = tArea;
    				l = li; r = i; min = tMin;
    			}
    		}
        }
		return area;
    }
	
	public static int maxArea3(int[] height) {
		int l = 0, r = height.length - 1;
		int area = r * Math.min(height[l], height[r]);
		boolean leftToRight = height[l] < height[r];
		int start = l, end = r;
		while(true) {
			if(leftToRight) {
				while(++start < end && height[start] <= height[l]);
			}else {
				while(start < --end && height[end] <= height[r]);
			}
			if(start == end) { break; }
			int tArea = (end - start) * Math.min(height[start], height[end]);
			if(tArea > area) {
				area = tArea;
				l = start;
				r = end;
			}
			leftToRight =  height[start] < height[end];
		}
		return area;
    }
}
