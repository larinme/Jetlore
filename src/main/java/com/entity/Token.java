package com.entity;

import java.text.MessageFormat;

public class Token {

    private final TokenType tokenType;
    private final String value;
    private final int startIndex;
    private final int endIndex;

    public Token(TokenType tokenType, String value, int startIndex, int endIndex) {
        this.tokenType = tokenType;
        this.value = value;
        this.startIndex = startIndex;
        this.endIndex = endIndex;

    }

    public int getStartIndex() {
        return startIndex;
    }

    public int getEndIndex() {
        return endIndex;
    }

    @Override
    public String toString() {
        String template = tokenType.getTemplate();
        return MessageFormat.format(template, value);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Token token = (Token) o;

        return tokenType == token.tokenType &&
                (value != null ?
                        value.equals(token.value)
                        : token.value == null);
    }

    @Override
    public int hashCode() {
        int result = tokenType != null ? tokenType.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }
}
