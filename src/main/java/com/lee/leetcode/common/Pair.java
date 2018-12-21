package com.lee.leetcode.common;

public final class Pair<T1, T2> {
    public final T1 one;
    public final T2 two;

    private Pair(T1 one, T2 two) {
        this.one = one;
        this.two = two;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 one, T2 two) {
        return new Pair<>(one, two);
    }
}
