package com.modules.configs;

import com.google.inject.AbstractModule;
import com.modules.analyzer.TypeResolver;
import com.modules.analyzer.impl.DefaultTypeResolver;
import com.modules.crawler.Crawler;
import com.modules.crawler.DefaultCrawler;

public class AnalyzeModule extends AbstractModule {

    protected void configure() {
        bind(TypeResolver.class).to(DefaultTypeResolver.class);
        bind(Crawler.class).to(DefaultCrawler.class);
    }
}
