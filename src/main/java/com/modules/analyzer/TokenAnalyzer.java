package com.modules.analyzer;

import com.entity.Token;

import java.util.List;

public interface TokenAnalyzer extends MessageAnalyzer {

    List<Token> parseString(String message);
}
