package org.isep.cleancode.calculator;

public class Token {
    public final TokenType type;
    public final String lexeme;

    public Token(TokenType type, String lexeme) {
        this.type = type;
        this.lexeme = lexeme;
    }
}

