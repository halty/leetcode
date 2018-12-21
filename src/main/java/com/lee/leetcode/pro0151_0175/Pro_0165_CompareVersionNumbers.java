package com.lee.leetcode.pro0151_0175;

/**
 *
 Compare two version numbers version1 and version2.
 If version1 > version2 return 1; if version1 < version2 return -1; otherwise return 0.

 You may assume that the version strings are non-empty and contain only digits and the . character.
 The . character does not represent a decimal point and is used to separate number sequences.
 For instance, 2.5 is not "two and a half" or "half way to version three", it is the fifth second-level revision of the second first-level revision.

 Example 1:
 Input: version1 = "0.1", version2 = "1.1"
 Output: -1

 Example 2:
 Input: version1 = "1.0.1", version2 = "1"
 Output: 1

 Example 3:
 Input: version1 = "7.5.2.4", version2 = "7.5.3"
 Output: -1
 *
 */
public class Pro_0165_CompareVersionNumbers {

    public static void main(String[] args) {
        String version1 = "1.0";
        String version2 = "1.0.0";
        int result = compareVersion(version1, version2);
        System.out.println(result);
    }

    public static int compareVersion(String version1, String version2) {
        int len1 = version1.length(), len2 = version2.length();
        int begin1 = 0, begin2 = 0;
        while(true) {
            int i = begin1, j = begin2;
            for(; i<len1 && j<len2 && version1.charAt(i)!='.' && version2.charAt(j)!='.'; i++,j++);
            for(; i<len1 && version1.charAt(i)!='.'; i++);
            for(; j<len2 && version2.charAt(j)!='.'; j++);

            int v1 = Integer.parseInt(version1.substring(begin1, i));
            int v2 = Integer.parseInt(version2.substring(begin2, j));
            if(v1 < v2) {
                return -1;
            }else if(v1 > v2) {
                return 1;
            }else {
                begin1 = i + 1;
                begin2 = j + 1;
                if(begin1 >= len1 || begin2 >= len2) { break; }
            }
        }
        if(begin1 >= len1) {
            for(; begin2<len2; begin2++) {
                char ch = version2.charAt(begin2);
                if(ch == '.' || ch == '0') { continue; }
                return -1;
            }
            return 0;
        }else {
            for(; begin1<len1; begin1++) {
                char ch = version1.charAt(begin1);
                if(ch == '.' || ch == '0') { continue; }
                return 1;
            }
            return 0;
        }
    }
}
