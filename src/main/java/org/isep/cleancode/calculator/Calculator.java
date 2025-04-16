package org.isep.cleancode.calculator;

public class Calculator {
    private final ExpressionParser parser = new ExpressionParser();

    public double evaluateMathExpression(String expression) {
        return parser.parse(expression);
    }
}

