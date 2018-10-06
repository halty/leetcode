package com.lee.leetcode.pro0051_0075;

import java.util.ArrayList;
import java.util.List;

/*
 *
Given an absolute path for a file (Unix-style), simplify it.

For example,
path = "/home/", => "/home"
path = "/a/./b/../../c/", => "/c"
path = "/a/../../b/../c//.//", => "/c"
path = "/a//b////c/d//././/..", => "/a/b/c"

In a UNIX-style file system, a period ('.') refers to the current directory, so it can be ignored in a simplified path.
Additionally, a double period ("..") moves up a directory, so it cancels out whatever the last directory was.
For more information, look here: https://en.wikipedia.org/wiki/Path_(computing)#Unix_style

Corner Cases:

Did you consider the case where path = "/../"?
In this case, you should return "/".
Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
In this case, you should ignore redundant slashes and return "/home/foo".
 *
 */
public class Pro_0071_SimplifyPath {

    public static void main(String[] args) {
        String path = "/a//b////c/d//././/..";
        String result = simplifyPath(path);
        System.out.println(result);

    }

    public static String simplifyPath(String path) {
        List<String> dirs = new ArrayList<String>();
        int len = path.length();
        int last = 0;
        for(int i=1; i<len; i++) {
            if(path.charAt(i) == '/') {
                handleSlash(path, last, i, dirs);
                last = i;
            }
        }
        handleSlash(path, last, len, dirs);
        if(dirs.size() == 0) {
            return "/";
        }
        StringBuilder buf = new StringBuilder();
        for(String dir : dirs) {
            buf.append("/").append(dir);
        }
        return buf.toString();
    }

    private static void handleSlash(String path, int last, int current, List<String> dirs) {
        int len = current - last - 1;
        switch(len) {
            case 0:
                break;
            case 1:
                char ch = path.charAt(current-1);
                if(ch != '.') {
                    dirs.add(String.valueOf(ch));
                }
                break;
            case 2:
                if(path.charAt(current-1) == '.' && path.charAt(current-2) == '.') {
                    fallback(dirs);
                }else {
                    dirs.add(path.substring(last+1, current));
                }
                break;
            default:
                dirs.add(path.substring(last+1, current));
                break;
        }
    }

    private static void fallback(List<String> dirs) {
        int len = dirs.size();
        if(len > 0) {
            dirs.remove(len - 1);
        }
    }
}
