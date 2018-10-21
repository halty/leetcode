package com.lee.leetcode.pro0076_0100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 Given a string containing only digits, restore it by returning all possible valid IP address combinations.

 Example:

 Input: "25525511135"
 Output: ["255.255.11.135", "255.255.111.35"]
 *
 */
public class Pro_0093_RestoreIPAdresses {

    public static void main(String[] args) {
        String s = "25525511135";
        List<String> result = restoreIpAddresses(s);
        System.out.println(result);
    }

    public static List<String> restoreIpAddresses(String s) {
        int len = s.length();
        if(!isValidLength(4, len)) { return Collections.emptyList(); }
        List<String> list = new ArrayList<>();
        for(int i=1; i<4; i++) {    // ip segment 1
            if(!isValidLength(3, len-i) || !isValidIPSegment(s, 0, i)) { continue; }
            for(int j=1; j<4; j++) {
                if(!isValidLength(2, len-i-j) || !isValidIPSegment(s, i, j)) { continue; }
                int c = i+j;
                for(int k=1; k<4; k++) {
                    int r = len-c-k;
                    if(!isValidLength(1, r)
                        || !isValidIPSegment(s, c, k) || !isValidIPSegment(s, c+k, r)) {
                        continue;
                    }
                    list.add(buildIPAddress(s, i, j, k));
                }
            }
        }
        return list;
    }

    private static boolean isValidLength(int segmentCount, int digitLength) {
        return segmentCount <= digitLength && digitLength <= segmentCount * 3;
    }

    private static boolean isValidIPSegment(String s, int begin, int len) {
        switch(len) {
            case 1:
                return true;
            case 2:
                return s.charAt(begin) != '0';
            case 3:
                int v = s.charAt(begin++) - '0';
                v = v*10 + s.charAt(begin++) - '0';
                v = v*10 + s.charAt(begin) - '0';
                return v >= 100 && v <= 255;
            default:
                return false;
        }
    }

    private static String buildIPAddress(String s, int i, int j, int k) {
        StringBuilder buf = new StringBuilder(s.length()+3);
        int begin = 0, end = i;
        buf.append(s.substring(begin, end)).append(".");
        begin += i; end += j;
        buf.append(s.substring(begin, end)).append(".");
        begin += j; end += k;
        buf.append(s.substring(begin, end)).append(".");
        begin += k; end = s.length();
        buf.append(s.substring(begin, end));
        return buf.toString();
    }
}
