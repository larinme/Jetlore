package com.modules.analyzer.impl;

import com.entity.TokenType;
import com.google.inject.Inject;
import com.modules.analyzer.TokenFormatter;
import com.modules.analyzer.TypeResolver;
import com.modules.crawler.Crawler;
import com.modules.crawler.DefaultCrawler;

import java.util.HashMap;
import java.util.Map;

public class DefaultMessageAnalyzer extends AbstractMessageAnalyzer {

    private static final Map<TokenType, TokenFormatter> TOKEN_FORMATTERS = new HashMap<TokenType, TokenFormatter>() {{
        put(TokenType.USERNAME, text -> text.replaceFirst("@", ""));
        put(TokenType.ENTITY, DEFAULT_FORMATTER);
        put(TokenType.LINK, DEFAULT_FORMATTER);
        put(TokenType.PLAINTEXT, DEFAULT_FORMATTER);
    }};
    private final TypeResolver resolver;
    private final Crawler crawler;

    @Inject
    public DefaultMessageAnalyzer(TypeResolver resolver, Crawler crawler) {
        this.resolver = resolver;
        this.crawler = crawler;
    }

    public DefaultMessageAnalyzer() {
        this.crawler = new DefaultCrawler();
        this.resolver = new DefaultTypeResolver();
    }

    @Override
    Map<TokenType, TokenFormatter> getFormatters() {
        return TOKEN_FORMATTERS;
    }
}
