package org.isep.cleancode;

import org.isep.cleancode.calculator.Calculator;

public class Main {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        Double res = calculator.evaluateMathExpression("-15");
        System.out.println("res : " + res);

    }
}