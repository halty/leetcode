package com.lee.leetcode;

public class Pro_0004_medianOfTwoSortedArrays {

	public static void main(String[] args) {
		int[] a = {2};
		int[] b = {1,3,4};
		System.out.println(findMedianSortedArrays(a, b));
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if(m == 0) {
        	if(n == 0) { return 0; }
        	// nums2[]
        	int idx = n >> 1;
        	return (n & 0x01) == 1 ? nums2[idx] : ((double)nums2[idx]+nums2[idx-1]) / 2;
        }
        if(n == 0) {
        	int idx = m >> 1;
        	return (m & 0x01) == 1 ? nums1[idx] : ((double)nums1[idx]+nums1[idx-1]) / 2;
        }
        // nums1[] + nums2[]
        int total = m + n;
        int idx = total >> 1;
        int cur = 0, prev = 0;
        int k = 0, i = 0, j = 0;
        for(; k<=idx; k++) {
        	prev = cur;
        	if(nums1[i] < nums2[j]) {
        		cur = nums1[i];
        		if(++i == m)  { break; }
        	}else {
        		cur = nums2[j];
        		if(++j == n) { break; }
        	}
        }
        if(k < idx) {
	        if(i == m) {
	        	j += idx - k - 1;
	        	prev = (idx==k+1) ? cur : nums2[j-1];
	        	cur = nums2[j];
	        }
	        if(j == n) {
	        	i += idx - k - 1;
	        	prev = (idx==k+1) ? cur : nums1[i-1];
	        	cur = nums1[i];
	        }
        }

        return (total & 0x01) == 1 ? cur : ((double)cur+prev) / 2;
    }
}
