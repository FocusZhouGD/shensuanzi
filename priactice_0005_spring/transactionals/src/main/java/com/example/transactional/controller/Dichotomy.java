package com.example.transactional.controller;

import java.util.Arrays;
import java.util.List;

public class Dichotomy {

    public static void main(String[] args) {
        List<Integer> i = Arrays.asList(0, 1, 2, 3, 5, 6);
        int start = 1;
        int end = i.size();
        int middle = (start + end) / 2;

        int result = -1;
        while (end > start) {
            if (i.get(start) != start) {
                result = start - 1;
                break;
            }
            if (i.get(end - 1) == end - 1) {
                result = end - 1;
                break;
            }
            if (i.get(middle) != middle) {
                end = middle;
            } else {
                start = middle;
            }
            middle = (start + end) / 2;

        }
        System.out.println(i.get(result) + 1);
    }
}
