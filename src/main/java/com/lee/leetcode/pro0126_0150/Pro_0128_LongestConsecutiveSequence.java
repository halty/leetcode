package com.lee.leetcode.pro0126_0150;

import java.util.*;

/**
 *
 Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 Your algorithm should run in O(n) complexity.

 Example:
 Input: [100, 4, 200, 1, 3, 2]
 Output: 4
 Explanation: The longest consecutive elements sequence is [1, 2, 3, 4]. Therefore its length is 4.
 *
 */
public class Pro_0128_LongestConsecutiveSequence {

    public static void main(String[] args) {
        int[] nums = {1,2,0,1};
//        int length = longestConsecutive1(nums);
        int length = longestConsecutive2(nums);
//        int length = longestConsecutive3(nums);
        System.out.println(length);
    }

    public static int longestConsecutive1(int[] nums) {
        if(nums.length < 2) { return nums.length; }
        Arrays.sort(nums);
        int prev = nums[0];
        int maxLength = 1;
        int len = 1;
        for(int i=1; i<nums.length; i++) {
            int current = nums[i];
            int diff = current - prev;
            if(diff > 1) {
                if(len > maxLength) {
                    maxLength = len;
                }
                len = 1;
            }else if(diff == 1) {
                len += 1;
            }else { }
            prev = current;
        }
        if(len > maxLength) { maxLength = len; }
        return maxLength;
    }

    public static int longestConsecutive2(int[] nums) {
        if(nums.length < 2) { return nums.length; }
        List<Integer> list = cardinalitySort(nums);
        int prev = list.get(0);
        int maxLength = 1;
        int len = 1;
        for(int i=1; i<nums.length; i++) {
            int current = list.get(i);
            int diff = current - prev;
            if(diff > 1) {
                if(len > maxLength) {
                    maxLength = len;
                }
                len = 1;
            }else if(diff == 1) {
                len += 1;
            }else { }
            prev = current;
        }
        if(len > maxLength) { maxLength = len; }
        return maxLength;
    }

    private static List<Integer> cardinalitySort(int[] nums) {
        ArrayList<Integer> negative = new ArrayList<>();
        ArrayList<Integer> nonNegative = new ArrayList<>();
        int maxNegative = 0;
        int maxNonNegative = 0;
        for(int num : nums) {
            if(num < 0) {
                num &= 0x7fffffff;  // convert to positive
                negative.add(num);
                if(num > maxNegative) { maxNegative = num; }
            }else {
                nonNegative.add(num);
                if(num > maxNonNegative) { maxNonNegative = num; }
            }
        }
        if(!negative.isEmpty()) {
            negative = sortNonNegative(negative, maxNegative);
        }
        if(!nonNegative.isEmpty()) {
            nonNegative = sortNonNegative(nonNegative, maxNonNegative);
        }
        int negativeCount = negative.size();
        negative.ensureCapacity(negativeCount+nonNegative.size());
        for(int i=0; i<negativeCount; i++) {
            int num = negative.get(i);
            num |= 0x80000000;  // recover original negative
            negative.set(i, num);
        }
        negative.addAll(nonNegative);
        return negative;
    }

    private static ArrayList<Integer> sortNonNegative(ArrayList<Integer> numList, int maxNonNegative) {
        int maxIndex = (31-Integer.numberOfLeadingZeros(maxNonNegative)) / 4;
        List<Integer>[] cards = new List[16];
        for(int num : numList) {
            int index = num & 0x0f;
            List<Integer> card = cards[index];
            if(card == null) {
                cards[index] = card = new ArrayList<>();
            }
            card.add(num);
        }
        int shiftCount = 0;
        for(int index=1; index<=maxIndex; index++) {
            shiftCount = index << 2; // index * 4
            List<Integer>[] newCards = new List[16];
            for(int i=0; i<16; i++) {
                List<Integer> card = cards[i];
                if(card == null) { continue; }
                for(Integer num : card) {
                    int newIndex = (num >>> shiftCount) & 0x0f;
                    List<Integer> newCard = newCards[newIndex];
                    if(newCard == null) {
                        newCards[newIndex] = newCard = new ArrayList<>();
                    }
                    newCard.add(num);
                }
            }
            cards = newCards;
        }
        ArrayList<Integer> resultList = new ArrayList<>(numList.size());
        for(int i=0; i<16; i++) {
            List<Integer> card = cards[i];
            if(card != null) {
                resultList.addAll(card);
            }
        }
        return resultList;
    }

    public static int longestConsecutive3(int[] nums) {
        if(nums.length < 2) { return nums.length; }
        Map<Integer, Integer> map = new HashMap<>();
        int maxLength = 1;
        for(int num : nums) {   // union/find
            if(map.containsKey(num)) { continue; }
            Integer left = map.get(num-1);
            Integer right = map.get(num+1);
            if(left == null) {
                if(right == null) {
                    map.put(num, 1);
                }else {
                    Integer len = right + 1;
                    if(maxLength < len) { maxLength = len; }
                    map.put(num, len);
                    map.put(num+right, len);
                }
            }else {
                Integer len = left + 1;
                if(right != null) {
                    len += right;
                }
                if(maxLength < len) { maxLength = len; }
                map.put(num, len);
                map.put(num-left, len);
                if(right != null) {
                    map.put(num+right, len);
                }
            }
        }
        return maxLength;
    }
}
