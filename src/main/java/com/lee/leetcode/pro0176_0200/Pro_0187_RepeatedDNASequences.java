package com.lee.leetcode.pro0176_0200;

import java.util.*;

/**
 *
 All DNA is composed of a series of nucleotides abbreviated as A, C, G, and T, for example: "ACGAATTCCG".
 When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.

 Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.

 Example:
 Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 Output: ["AAAAACCCCC", "CCCCCAAAAA"]
 *
 */
public class Pro_0187_RepeatedDNASequences {

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        List<String> list = findRepeatedDnaSequences(s);
        for(String str : list) {
            System.out.println(str);
        }
    }

    public static List<String> findRepeatedDnaSequences(String s) {
        int n = s == null ? 0 : s.length();
        if(n <= 10) { return Collections.emptyList(); }
        int id = 0;
        for(int i=0; i<10; i++) {
            id = (id << 2) | codePoint(s.charAt(i));
        }
        List<String> list = new ArrayList<>();
        Map<Integer, Integer> countMap = new HashMap<>();
        countMap.put(id, 1);
        for( int i=10; i<n; i++) {
            id = 0xfffff & (id << 2) | codePoint(s.charAt(i));
            Integer count = countMap.get(id);
            if(count == null) {
                countMap.put(id, 1);
            }else {
                if(count == 1) {
                    list.add(s.substring(i-9, i+1));
                    count += 1;
                    countMap.put(id, count);
                }
            }
        }
        return list;
    }

    private static int codePoint(char ch) {
        switch(ch) {
            case 'A': return 0x00;
            case 'C': return 0x01;
            case 'G': return 0x02;
            case 'T': return 0x03;
            default: return 0x00;
        }
    }
}
