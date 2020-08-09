package com.example.demo;

/**
 * @author Jasper Wu
 * @date 2020/8/9
 **/
public class Calculator {
    public int calculate(String expression) {
        String[] nums = expression.split("[^\\d]+");
        return Integer.parseInt(nums[0]) + Integer.parseInt(nums[1]);
    }
}
