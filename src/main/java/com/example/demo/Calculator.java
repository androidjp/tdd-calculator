package com.example.demo;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Stream.of;

/**
 * @author Jasper Wu
 * @date 2020/8/9
 **/
public class Calculator {
    public int calculate(String expression) {
        String[] nums = expression.split("\\s[+\\-*]\\s");
        String[] ops = expression.split("\\s?-?\\d+\\s?");
        if (StringUtils.isBlank(ops[0])) {
            ArrayUtils.remove(ops, 0);
        }
        if (StringUtils.isBlank(ops[ops.length - 1])) {
            ArrayUtils.remove(ops, ops.length - 1);
        }
        List<String> postRetrieveList = changeToPostRetrieve(nums, ops);
        return calculate(postRetrieveList);
    }

    private List<String> changeToPostRetrieve(String[] nums, String[] ops) {
        Map<String, Integer> priorityMap = new HashMap<>(8);
        priorityMap.put("+", 0);
        priorityMap.put("-", 0);
        priorityMap.put("*", 1);

        Stack<String> myStack = new Stack<>();
        List<String> list = new ArrayList<>(nums.length + ops.length);
        int point = -1;
        for (int i = 0; i < nums.length; i++) {
            if (ops[i].length() != 0) {
                while (!myStack.isEmpty() && priorityMap.get(ops[i]) <= priorityMap.get(myStack.peek())) {
                    list.add(myStack.pop());
                }
                myStack.add(ops[i]);
            }
            list.add(nums[i]);
        }
        while (!myStack.isEmpty()) {
            list.add(myStack.pop());
        }
        return list;
    }

    private int calculate(List<String> postRetrieveList) {
        final Stack<Integer> stack = new Stack<>();
        for (String obj : postRetrieveList) {
            if (obj.matches("-?\\d+")) {
                stack.add(parseInt(obj));
            } else {
                int b = stack.pop();
                int a = stack.pop();
                if (obj.equals("+"))
                    stack.add(a + b);
                if (obj.equals("-"))
                    stack.add(a - b);
                if (obj.equals("*"))
                    stack.add(a * b);
            }
        }
        return stack.get(0);
    }
}
