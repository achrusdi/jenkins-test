package com.ilu.loan.utils;

import java.math.BigDecimal;

public class NumberConverter {
    public static String convertScientificNotation(double number) {
        BigDecimal bd = new BigDecimal(String.valueOf(number));
        return bd.toPlainString();
    }

}