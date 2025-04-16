package org.isep.cleancode.calculator;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {
    public static List<Token> tokenize(String input) {
        List<Token> tokens = new ArrayList<>();
        int i = 0;

        while (i < input.length()) {
            char c = input.charAt(i);

            if (Character.isWhitespace(c)) {
                i++;
                continue;
            }

            switch (c) {
                case '+':
                    tokens.add(new Token(TokenType.PLUS, "+"));
                    i++;
                    break;
                case '-':
                    tokens.add(new Token(TokenType.MINUS, "-"));
                    i++;
                    break;
                case '*':
                    tokens.add(new Token(TokenType.MULTIPLY, "*"));
                    i++;
                    break;
                case '(':
                    tokens.add(new Token(TokenType.LEFT_PAREN, "("));
                    i++;
                    break;
                case ')':
                    tokens.add(new Token(TokenType.RIGHT_PAREN, ")"));
                    i++;
                    break;
                default:
                    if (Character.isDigit(c) || c == '.') {
                        int start = i;
                        while (i < input.length() &&
                                (Character.isDigit(input.charAt(i)) || input.charAt(i) == '.')) {
                            i++;
                        }
                        String number = input.substring(start, i);
                        tokens.add(new Token(TokenType.NUMBER, number));
                    } else {
                        throw new IllegalArgumentException("Unexpected character: " + c);
                    }
                    break;
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}

