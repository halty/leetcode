package com.lee.leetcode.pro0126_0150;

import java.util.*;

/**
 *
 Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s) from beginWord to endWord, such that:
    1. Only one letter can be changed at a time
    2. Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 Note:
    1. Return an empty list if there is no such transformation sequence.
    2. All words have the same length.
    3. All words contain only lowercase alphabetic characters.
    4. You may assume no duplicates in the word list.
    5. You may assume beginWord and endWord are non-empty and are not the same.

 Example 1:
 Input:
    beginWord = "hit",
    endWord = "cog",
    wordList = ["hot","dot","dog","lot","log","cog"]
 Output:
    [
        ["hit","hot","dot","dog","cog"],
        ["hit","hot","lot","log","cog"]
    ]

 Example 2:
 Input:
    beginWord = "hit"
    endWord = "cog"
    wordList = ["hot","dot","dog","lot","log"]
 Output: []
 Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 *
 */
public class Pro_0126_WordLadderII {

    public static void main(String[] args) {
        String beginWord = "qa";
        String endWord = "sq";
        String[] array = {"si","go","se","cm","so","ph","mt","db","mb","sb","kr","ln","tm","le","av","sm","ar","ci","ca","br","ti","ba","to","ra","fa","yo","ow","sn","ya","cr","po","fe","ho","ma","re","or","rn","au","ur","rh","sr","tc","lt","lo","as","fr","nb","yb","if","pb","ge","th","pm","rb","sh","co","ga","li","ha","hz","no","bi","di","hi","qa","pi","os","uh","wm","an","me","mo","na","la","st","er","sc","ne","mn","mi","am","ex","pt","io","be","fm","ta","tb","ni","mr","pa","he","lr","sq","ye"};
        /*String beginWord = "hit";
        String endWord = "cog";
        String[] array = {"hot","dot","dog","lot","log","cog"};*/
        List<String> wordList = asArrayList(array);
        List<List<String>> ladders = findLadders2(beginWord, endWord, wordList);
        for(List<String> ladder : ladders) {
            System.out.println(ladder);
        }
        System.out.println(ladders.size());
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if(wordList.isEmpty()) { return Collections.emptyList(); }
        int wordLength = beginWord.length();
        int wordCount = wordList.size();
        Map<String, Integer> wordIndexMap = wordIndexMapping(wordList);
        Integer targetIndex = wordIndexMap.get(endWord);
        if(targetIndex == null) { return Collections.emptyList(); }
        Set<Integer> startSet = adjacentSet(new StringBuilder(beginWord), wordLength, wordIndexMap, wordCount);
        if(startSet.isEmpty()) { return Collections.emptyList(); }
        Map<Integer, Set<Integer>> adjacentSetMap = adjacentSetMapping(wordList, wordCount, wordIndexMap, wordLength);
//        return traversePath(wordList, wordCount, beginWord, targetIndex, startSet, adjacentSetMap);
         return shortestPath(wordList, wordCount, beginWord, targetIndex, startSet, adjacentSetMap);
    }

    private static Map<String, Integer> wordIndexMapping(List<String> wordList) {
        Map<String, Integer> wordIndexMap = new HashMap<>(wordList.size());
        int index = 0;
        for(String word : wordList) {
            wordIndexMap.put(word, index);
            index++;
        }
        return wordIndexMap;
    }

    private static Map<Integer, Set<Integer>> adjacentSetMapping(List<String> wordList, int wordCount, Map<String, Integer> wordIndexMap, int wordLength) {
        Map<Integer, Set<Integer>> adjacentSetMap = new HashMap<>(wordCount);
        StringBuilder buf = new StringBuilder(wordLength);
        int index = 0;
        for(String word : wordList) {
            buf.append(word);
            Set<Integer> adjacentSet = adjacentSet(buf, wordLength, wordIndexMap, wordCount);
            buf.setLength(0);
            adjacentSetMap.put(index, adjacentSet);
            index++;
        }
        return adjacentSetMap;
    }

    private static Set<Integer> adjacentSet(StringBuilder wordBuf, int wordLength, Map<String, Integer> wordIndexMap, int wordCount) {
        Set<Integer> adjacentSet = new HashSet<>();
        for(int i=0; i<wordLength; i++) {
            char ch = wordBuf.charAt(i);
            for(char c='a'; c<'z'; c++) {
                if(c != ch) {
                    wordBuf.setCharAt(i, c);
                    Integer index = wordIndexMap.get(wordBuf.toString());
                    if(index != null) {
                        adjacentSet.add(index);
                    }
                }
            }
            wordBuf.setCharAt(i, ch);
        }
        return adjacentSet;
    }

    private static Set<Integer> initCandidateSet(int wordCount) {
        Set<Integer> candidateSet = new HashSet<>(wordCount);
        for(int i=0; i<wordCount; i++) {
            candidateSet.add(i);
        }
        return candidateSet;
    }

    private static List<List<String>> traversePath(List<String> wordList, int wordCount, String beginWord, int targetIndex,
                                            Set<Integer> startSet, Map<Integer, Set<Integer>> adjacentSetMap) {
        Set<Integer> candidateSet = initCandidateSet(wordCount);
        List<List<String>> resultList = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();
        traverseRecursively(wordList, beginWord, targetIndex, candidateSet, stack, startSet, adjacentSetMap, resultList);
        resultList = findShortest(resultList);
        return resultList;
    }

    private static void traverseRecursively(List<String> wordList, String beginWord, Integer targetIndex,
                                            Set<Integer> candidateSet, Stack<Integer> stack, Set<Integer> outputSet,
                                            Map<Integer, Set<Integer>> adjacentSetMap, List<List<String>> resultList) {
        if(outputSet.contains(targetIndex)) {
            stack.push(targetIndex);
            resultList.add(collect(wordList, beginWord, stack));
            stack.pop();
        }else {
            for(Integer output : outputSet) {
                if(candidateSet.remove(output)) {
                    stack.push(output);
                    Set<Integer> adjacentSet = adjacentSetMap.get(output);
                    traverseRecursively(wordList, beginWord, targetIndex, candidateSet, stack,
                            adjacentSet, adjacentSetMap, resultList);
                    stack.pop();
                    candidateSet.add(output);
                }
            }
        }
    }

    private static List<String> collect(List<String> wordList, String beginWord, Stack<Integer> stack) {
        List<String> result = new ArrayList<>(stack.size()+1);
        result.add(beginWord);
        for(Integer index : stack) {
            result.add(wordList.get(index));
        }
        return result;
    }

    private static List<List<String>> findShortest(List<List<String>> resultList) {
        int shortestSize = Integer.MAX_VALUE;
        for(List<String> result : resultList) {
            int size = result.size();
            if(shortestSize > size) {
                shortestSize = size;
            }
        }
        List<List<String>> shortestList = new ArrayList<>();
        for(List<String> result : resultList) {
            if(shortestSize == result.size()) {
                shortestList.add(result);
            }
        }
        return shortestList;
    }

    private static List<List<String>> shortestPath(List<String> wordList, int wordCount, String beginWord, int targetIndex,
                                                   Set<Integer> startSet, Map<Integer, Set<Integer>> adjacentSetMap) {
        Shortest[] shortestPaths = new Shortest[wordCount];
        for(Integer outIndex : startSet) {
            shortestPaths[outIndex] = new Shortest(outIndex);
        }
        Set<Integer> candidateSet = initCandidateSet(wordCount);
        while(!candidateSet.isEmpty()) {
            Integer minIndex = null;
            int minHopCount = Integer.MAX_VALUE;
            for(Integer candidate : candidateSet) {
                Shortest shortest = shortestPaths[candidate];
                if(shortest != null && shortest.hopCount < minHopCount) {
                    minHopCount = shortest.hopCount;
                    minIndex = candidate;
                }
            }
            Shortest target = shortestPaths[targetIndex];
            if(target != null && minHopCount == target.hopCount) { break; }
            Shortest min = shortestPaths[minIndex];
            int nextHopCount = minHopCount + 1;
            Set<Integer> outSet = adjacentSetMap.get(minIndex);
            for(Integer outIndex : outSet) {
                Shortest out = shortestPaths[outIndex];
                if(out == null) {
                    shortestPaths[outIndex] = new Shortest(nextHopCount, min, outIndex);
                }else {
                    if(nextHopCount < out.hopCount) {
                        shortestPaths[outIndex] = new Shortest(nextHopCount, min, outIndex);
                    }else if(nextHopCount == out.hopCount) {
                        out.addHops(min, outIndex);
                    }
                }
            }
            candidateSet.remove(minIndex);
        }
        return shortestLadders(wordList, beginWord, shortestPaths[targetIndex]);
    }

    private static List<List<String>> shortestLadders(List<String> wordList, String beginWord, Shortest target) {
        if(target == null) { return Collections.emptyList(); }
        List<List<String>> shortestLadders = new ArrayList<>(target.paths.size());
        for(List<Integer> path : target.paths) {
            List<String> ladder = new ArrayList<>(path.size() + 1);
            ladder.add(beginWord);
            for(Integer index : path) {
                ladder.add(wordList.get(index));
            }
            shortestLadders.add(ladder);
        }
        return shortestLadders;
    }

    private static class Shortest {
        int hopCount;
        List<List<Integer>> paths;

        Shortest(Integer hop) {
            this.hopCount = 1;
            paths = new ArrayList<>();
            List<Integer> path = new ArrayList<>();
            path.add(hop);
            paths.add(path);
        }

        Shortest(int hopCount, Shortest prev, Integer hop) {
            this.hopCount = hopCount;
            this.paths = newPaths(prev.paths, hop);
        }

        void addHops(Shortest prev, Integer currentHop) {
            for(List<Integer> path : prev.paths) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(currentHop);
                this.paths.add(newPath);
            }
        }

        static List<List<Integer>> newPaths(List<List<Integer>> prevPaths, Integer currentHop) {
            List<List<Integer>> currentPaths = new ArrayList<>(prevPaths.size());
            for(List<Integer> path : prevPaths) {
                List<Integer> newPath = new ArrayList<>(path);
                newPath.add(currentHop);
                currentPaths.add(newPath);
            }
            return currentPaths;
        }
    }

    public static List<List<String>> findLadders2(String beginWord, String endWord, List<String> wordList) {
        if(wordList.isEmpty()) { return Collections.emptyList(); }
        int wordLength = beginWord.length();
        Set<String> candidateSet = new HashSet<>(wordList);
        if(!candidateSet.contains(endWord)) { return Collections.emptyList(); }
        StringBuilder wordBuf = new StringBuilder(wordLength);
        Set<String> level0 = new HashSet<>();
        appendToNextLevel(wordLength, wordBuf, beginWord, endWord, candidateSet, level0);
        if(level0.isEmpty()) { return Collections.emptyList(); }
        if(level0.contains(endWord)) {
            return Arrays.asList(Arrays.asList(beginWord, endWord));
        }
        // BFS
        List<Set<String>> levels = new ArrayList<>();
        levels.add(level0);
        Set<String> currentLevel = level0;
        boolean canReach = false;
        while(!candidateSet.isEmpty()) {
            Set<String> nextLevel = new HashSet<>();
            if(generateNextLevel(wordLength, wordBuf, endWord, currentLevel, candidateSet, nextLevel)) {
                canReach = true;
                break;
            }else {
                if(nextLevel.isEmpty()) { return Collections.emptyList(); }
                levels.add(nextLevel);
                currentLevel = nextLevel;
            }
        }
        if(!canReach) { return Collections.emptyList(); }
        // reversed BFS
        int levelCount = levels.size();
        Set<String> lastLevel = levels.get(levelCount-1);
        Map<String, ArrayList<List<String>>> lastLadderMap = generateLevelLadder(wordLength, endWord, lastLevel);
        int count = lastLadderMap.size();
        for(int i=levelCount-2; i>=0; i--) {
            Map<String, ArrayList<List<String>>> currentLadderMap = new HashMap<>(count * 2);
            count = 0;
            for(String word : levels.get(i)) {
                count += addLevelLadder(wordLength, word, lastLadderMap, currentLadderMap);
            }
            lastLadderMap = currentLadderMap;
        }
        return merge(count, beginWord, lastLadderMap);
    }

    private static boolean generateNextLevel(int wordLength, StringBuilder wordBuf, String endWord,
                                                 Set<String> currentLevel, Set<String> candidateSet, Set<String> nextLevel) {
        for(String word : currentLevel) {
            if(appendToNextLevel(wordLength, wordBuf, word, endWord, candidateSet, nextLevel)) {
                return true;
            }
        }
        return false;
    }

    private static boolean appendToNextLevel(int wordLength, StringBuilder wordBuf, String word, String endWord,
                                          Set<String> candidateSet, Set<String> nextLevel) {
        if(candidateSet.size() > 26) {  // lowercase alphabetic traversal
            wordBuf.setLength(0);
            wordBuf.append(word);
            for(int i = 0; i < wordLength; i++) {
                char ch = wordBuf.charAt(i);
                for(char c = 'a'; c < 'z'; c++) {
                    if (c != ch) {
                        wordBuf.setCharAt(i, c);
                        String w = wordBuf.toString();
                        if (candidateSet.remove(w)) {
                            nextLevel.add(w);
                            if (w.equals(endWord)) {
                                return true;
                            }
                        }
                    }
                }
                wordBuf.setCharAt(i, ch);
            }
        }else {
            for(Iterator<String> it=candidateSet.iterator(); it.hasNext();) {
                String w = it.next();
                if(oneDiff(word, w, wordLength)) {
                    it.remove();
                    nextLevel.add(w);
                    if(w.equals(endWord)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static boolean oneDiff(String one, String another, int wordLength) {
        boolean exactOne = false;
        for(int i=0; i<wordLength; i++) {
            if(one.charAt(i) == another.charAt(i)) { continue; }
            if(exactOne) {
                return false;
            }
            exactOne = true;
        }
        return exactOne;
    }

    private static Map<String, ArrayList<List<String>>> generateLevelLadder(int wordLength, String endWord, Set<String> level) {
        Map<String, ArrayList<List<String>>> map = new HashMap<>();
        for(String w : level) {
            if(oneDiff(w, endWord, wordLength)) {
                List<String> list = new ArrayList<>(2);
                list.add(w);
                list.add(endWord);
                ArrayList<List<String>> ladder = new ArrayList<>();
                ladder.add(list);
                map.put(w, ladder);
            }
        }
        return map;
    }

    private static int addLevelLadder(int wordLength, String word, Map<String, ArrayList<List<String>>> lastMap,
                                      Map<String, ArrayList<List<String>>> currentMap) {
        int count = 0;
        for(Map.Entry<String, ArrayList<List<String>>> entry : lastMap.entrySet()) {
            if(oneDiff(word, entry.getKey(), wordLength)) {
                List<List<String>> lastLadders = entry.getValue();
                putCurrentLadders(word, lastLadders, currentMap);
                count += lastLadders.size();
            }
        }
        return count;
    }

    private static void putCurrentLadders(String word, List<List<String>> lastLadders, Map<String, ArrayList<List<String>>> currentMap) {
        ArrayList<List<String>> currentLadders = currentMap.get(word);
        if(currentLadders == null) {
            currentLadders = new ArrayList<>(lastLadders.size());
            currentMap.put(word, currentLadders);
        }else {
            currentLadders.ensureCapacity(currentLadders.size()+lastLadders.size());
        }
        for(List<String> lastLadder : lastLadders) {
            List<String> currentLadder = new ArrayList<>(lastLadder.size()+1);
            currentLadder.add(word);
            currentLadder.addAll(lastLadder);
            currentLadders.add(currentLadder);
        }
    }

    private static List<List<String>> merge(int count, String beginWord, Map<String, ArrayList<List<String>>> lastMap) {
        List<List<String>> resultList = new ArrayList<>(count);
        for(ArrayList<List<String>> lastLadders : lastMap.values()) {
            for(List<String> ladder : lastLadders) {
                ladder.add(0, beginWord);
                resultList.add(ladder);
            }
        }
        return resultList;
    }

    private static List<String> asArrayList(String[] array) {
        List<String> list = new ArrayList<>(array.length);
        for(String e : array) {
            list.add(e);
        }
        return list;
    }
}
