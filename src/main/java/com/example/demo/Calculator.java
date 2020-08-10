package com.example.demo;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

import static java.lang.Integer.parseInt;
import static java.util.stream.Stream.of;
import static org.apache.commons.lang3.ArrayUtils.remove;
import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * @author Jasper Wu
 * @date 2020/8/9
 **/
public class Calculator {
    public static final String SPLIT_FOR_NUMBER = "\\s[+\\-*]\\s";
    public static final String SPLIT_FOR_OPERATION = "\\s?-?\\d+\\s?";
    private final Map<String, Integer> priorityMap;

    {
        priorityMap = new HashMap<>(8);
        priorityMap.put("+", 0);
        priorityMap.put("-", 0);
        priorityMap.put("*", 1);
    }

    private final Map<String, Consumer<Stack<Integer>>> calculateOperation;

    {
        calculateOperation = new HashMap<>(8);
        calculateOperation.put("+", stack -> {
            int b = stack.pop();
            int a = stack.pop();
            stack.add(a + b);
        });
        calculateOperation.put("-", stack -> {
            int b = stack.pop();
            int a = stack.pop();
            stack.add(a - b);
        });
        calculateOperation.put("*", stack -> {
            int b = stack.pop();
            int a = stack.pop();
            stack.add(a * b);
        });
    }

    public int calculate(String expression) {
        String[] nums = expression.split(SPLIT_FOR_NUMBER);
        String[] ops = getOperations(expression);
        List<String> postRetrieveList = changeToPostOrderTraversal(nums, ops);
        return calculate(postRetrieveList);
    }

    private String[] getOperations(String expression) {
        String[] ops = expression.split(SPLIT_FOR_OPERATION);
        if (isBlank(ops[0])) {
            remove(ops, 0);
        }
        if (isBlank(ops[ops.length - 1])) {
            remove(ops, ops.length - 1);
        }
        return ops;
    }

    private List<String> changeToPostOrderTraversal(String[] nums, String[] ops) {
        Stack<String> myStack = new Stack<>();
        List<String> list = new ArrayList<>(nums.length + ops.length);
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
                calculateOperation.get(obj).accept(stack);
            }
        }
        return stack.get(0);
    }
}
