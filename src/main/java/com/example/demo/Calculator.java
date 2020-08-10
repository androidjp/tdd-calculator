package com.example.demo;

import static java.lang.Integer.parseInt;

/**
 * @author Jasper Wu
 * @date 2020/8/9
 **/
public class Calculator {
    public int calculate(String expression) {
        String[] nums = expression.split("[^\\d]+");
        return parseInt(nums[0]) + parseInt(nums[1]);
    }
}
