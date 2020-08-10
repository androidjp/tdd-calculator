package com.example.demo;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
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
        List<String> myStack = new ArrayList<>(nums.length + ops.length);
        List<String> list = new ArrayList<>(nums.length + ops.length);
        int point = -1;
        for (int i = 0; i < nums.length; i++) {
            if (ops[i].length() != 0) {
                while (!myStack.isEmpty() &&
                        (
                                (("+".equals(ops[i]) || "-".equals(ops[i])) && ("+".equals(myStack.get(myStack.size() - 1)) || "-".equals(myStack.get(myStack.size() - 1))))
                                        ||
                                        (("+".equals(ops[i]) || "-".equals(ops[i])) && ("*".equals(myStack.get(myStack.size() - 1))))
                        )
                ) {
                    list.add(myStack.get(point));
                    myStack.remove(point);
                    point--;
                }
                myStack.add(ops[i]);
                point++;
            }
            list.add(nums[i]);
        }
        while (!myStack.isEmpty()) {
            list.add(myStack.get(myStack.size() - 1));
            myStack.remove(myStack.size() - 1);
        }
        return list;
    }

    private int calculate(List<String> postRetrieveList) {
        final List<Integer> stack = new ArrayList<>();
        for (String obj : postRetrieveList) {
            if (obj.matches("-?\\d+")) {
                stack.add(parseInt(obj));
            } else {
                int b = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
                int a = stack.get(stack.size() - 1);
                stack.remove(stack.size() - 1);
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
