package com.modules.configs;

import com.google.inject.AbstractModule;
import com.modules.analyzer.MessageAnalyzer;
import com.modules.analyzer.impl.DefaultMessageAnalyzer;
import com.modules.wrapper.Wrapper;
import com.modules.wrapper.WrapperImpl;

public class WrapperModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Wrapper.class).to(WrapperImpl.class);
        bind(MessageAnalyzer.class).to(DefaultMessageAnalyzer.class);
    }
}
