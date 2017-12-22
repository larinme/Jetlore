package com.modules.analyzer.impl;

import com.entity.TokenType;
import com.modules.analyzer.TypeResolver;
import com.utils.PropertyFinder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultTypeResolver implements TypeResolver {

    private final String urlPattern = PropertyFinder.getInstance().getProperty("url_pattern");

    public TokenType getTypeFor(String token) {
        if (isLink(token)) {
            return TokenType.LINK;
        }

        if (isEntity(token)) {
            return TokenType.ENTITY;
        }

        if (isUserName(token)) {
            return TokenType.USERNAME;
        }

        return TokenType.PLAINTEXT;
    }

    private boolean isUserName(String token) {
        return token.charAt(0) == '@';
    }

    private boolean isEntity(String token) {
        char firstCharacter = token.charAt(0);
        return firstCharacter >= 65 && firstCharacter <= 90;
    }

    private boolean isLink(String token) {
        Pattern compile = Pattern.compile(urlPattern);
        Matcher matcher = compile.matcher(token);
        return matcher.find();
    }
}
