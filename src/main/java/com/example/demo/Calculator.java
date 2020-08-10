package com.example.demo;

import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.util.stream.Stream.of;

/**
 * @author Jasper Wu
 * @date 2020/8/9
 **/
public class Calculator {
    public int calculate(String expression) {
        String[] nums = expression.split("\\s[+-]\\s");
        List<String> ops = of(expression.split("\\s?-?\\d+\\s?"))
                .filter(StringUtils::isNotBlank)
                .collect(Collectors.toList());
        int result = parseInt(nums[0]);
        for (int i = 0; i < ops.size(); i++) {
            if ("+".equals(ops.get(i))) {
                result += parseInt(nums[i+1]);
            }
            if ("-".equals(ops.get(i))) {
                result -= parseInt(nums[i+1]);
            }
        }
        return result;
    }
}
