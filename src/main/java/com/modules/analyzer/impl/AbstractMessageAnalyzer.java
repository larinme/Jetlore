package com.modules.analyzer.impl;

import com.entity.Message;
import com.entity.Token;
import com.entity.TokenType;
import com.google.inject.Inject;
import com.modules.analyzer.TokenAnalyzer;
import com.modules.analyzer.TokenFormatter;
import com.modules.analyzer.TypeResolver;
import com.modules.crawler.Crawler;
import com.modules.crawler.DefaultCrawler;

import java.util.*;

public abstract class AbstractMessageAnalyzer implements TokenAnalyzer {

    protected static final TokenFormatter DEFAULT_FORMATTER = text -> text;
    private final TypeResolver resolver;
    private final Crawler crawler;

    @Inject
    public AbstractMessageAnalyzer(TypeResolver resolver, Crawler crawler) {
        this.resolver = resolver;
        this.crawler = crawler;
    }

    public AbstractMessageAnalyzer() {
        this.crawler = new DefaultCrawler();
        this.resolver = new DefaultTypeResolver();
    }

    abstract Map<TokenType, TokenFormatter> getFormatters();

    @Override
    public Collection<Message> parseMessages(Collection<String> values) {
        int id = 0;
        Collection<Message> messages = new HashSet<>();
        for (String value : values) {
            Message message = parseMessage(id++, value);
            messages.add(message);
        }
        return messages;
    }

    @Override
    public Message parseMessage(int id, String value) {
        List<Token> tokens = parseString(value);
        return new Message(id, value, tokens);
    }

    @Override
    public List<Token> parseString(String message) {
        List<Token> tokens = new ArrayList<>();
        char[] charArray = message.toCharArray();
        int tempStartIndex = 0;
        int tokenLength = 0;
        StringBuilder tokenBuilder = new StringBuilder();

        for (int i = 0; i < charArray.length; i++) {
            char currChar = charArray[i];
            tokenLength++;
            boolean isFinalChar = i + 1 == charArray.length;
            if (currChar == ' ' || isFinalChar) {
                if (isFinalChar) {
                    tokenBuilder.append(currChar);
                }
                String token = tokenBuilder.toString();
                TokenType type = resolver.getTypeFor(token);
                if (TokenType.USERNAME.equals(type)) {
                    token = token.replaceFirst("@", "");
                }
                tokens.add(new Token(type, token, tempStartIndex, tempStartIndex + tokenLength));

                tokenBuilder = new StringBuilder();
                tempStartIndex += tokenLength;
                tokenLength = 0;
            } else {
                tokenBuilder.append(currChar);
            }
        }
        return tokens;
    }

    public Collection<Message> parseFromURL(String url) {
        Collection<String> messagesAsPlainText = crawler.crawl(url);
        return parseMessages(messagesAsPlainText);
    }

}
