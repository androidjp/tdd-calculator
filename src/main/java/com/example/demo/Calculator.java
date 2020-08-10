package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Integer.parseInt;

/**
 * @author Jasper Wu
 * @date 2020/8/9
 **/
public class Calculator {
    public int calculate(String expression) {
        String[] nums = expression.split("[^\\d]+");
        String[] operations = expression.split("\\s?\\d+\\s?");
        List<String> ops = Stream.of(operations).filter(str -> str != null && str.length() > 0).collect(Collectors.toList());
        if ("+".equals(ops.get(0))) {
            return parseInt(nums[0]) + parseInt(nums[1]);
        }
        if ("-".equals(ops.get(0))) {
            return parseInt(nums[0]) - parseInt(nums[1]);
        }
        return 0;
    }
}
