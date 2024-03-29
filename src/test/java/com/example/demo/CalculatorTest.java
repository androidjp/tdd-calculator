package com.example.demo;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {
    private static Calculator calculator;

    @BeforeAll
    static void setUp() {
        calculator = new Calculator();
    }

    @Test
    public void should_return_integer_3_when_calculate_given_expression_1_plus_2() {
        // given
        String expression = "1 + 2";
        // when
        int result = calculator.calculate(expression);
        // then
        assertThat(result).isEqualTo(3);
    }

    @Test
    public void should_return_integer_minus_1_when_calculate_given_expression_1_subtract_2() {
        // given
        String expression = "1 - 2";
        // when
        int result = calculator.calculate(expression);
        // then
        assertThat(result).isEqualTo(-1);
    }

    @Test
    public void should_return_integer_minus_2_when_calculate_given_expression_1_subtract_2_plus_minus_1() {
        // given
        String expression = "1 - 2 + -1";
        // when
        int result = calculator.calculate(expression);
        // then
        assertThat(result).isEqualTo(-2);
    }
    
    @Test
    public void should_return_integer_7_when_calculate_given_expression_1_subtract_2_plus_minus_1_multi_minus_8() {
        // given
        String expression = "1 - 2 + -1 * -8";
        // when
        int result = calculator.calculate(expression);
        // then
        assertThat(result).isEqualTo(7);
    }
}