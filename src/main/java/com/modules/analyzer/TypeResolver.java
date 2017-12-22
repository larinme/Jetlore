package com.modules.analyzer;

import com.entity.TokenType;

public interface TypeResolver {

    TokenType getTypeFor(String token);
}
