package org.isep.cleancode.calculator;

import java.util.List;

public class ExpressionParser {
    private List<Token> tokens;
    private int current;

    public double parse(String expression) {
        this.tokens = Tokenizer.tokenize(expression);
        this.current = 0;
        return parseExpression();
    }

    private double parseExpression() {
        double result = parseTerm();

        while (match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = previous();
            double right = parseTerm();

            if (operator.type == TokenType.PLUS) {
                result += right;
            } else {
                result -= right;
            }
        }

        return result;
    }

    private double parseTerm() {
        double result = parseFactor();

        while (match(TokenType.MULTIPLY)) {
            Token operator = previous();
            double right = parseFactor();
            if (operator.type == TokenType.MULTIPLY) {
                result *= right;
            }
        }

        return result;
    }

    private double parseFactor() {
        if (match(TokenType.MINUS)) {
            return -parseFactor(); // unary negation
        }

        if (match(TokenType.NUMBER)) {
            return Double.parseDouble(previous().lexeme);
        }

        if (match(TokenType.LEFT_PAREN)) {
            double value = parseExpression();
            consume(TokenType.RIGHT_PAREN);
            return value;
        }

        throw new IllegalStateException("Expected number, '(', or '-' but found: " + peek().lexeme);
    }

    private boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    private Token consume(TokenType expected) {
        if (check(expected)) return advance();
        throw new IllegalStateException("Expected token: " + expected);
    }

    private boolean check(TokenType type) {
        return !isAtEnd() && peek().type == type;
    }

    private Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    private boolean isAtEnd() {
        return peek().type == TokenType.EOF;
    }

    private Token peek() {
        return tokens.get(current);
    }

    private Token previous() {
        return tokens.get(current - 1);
    }
}

