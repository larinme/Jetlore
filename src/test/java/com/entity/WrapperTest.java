package com.entity;


import com.google.inject.Guice;
import com.google.inject.Injector;
import com.modules.configs.AnalyzeModule;
import com.modules.configs.WrapperModule;
import com.modules.wrapper.Wrapper;
import org.junit.Assert;
import org.junit.Test;

public class WrapperTest {

    @Test
    public void testFakeWrappers1(){
        Injector injector = Guice.createInjector(new WrapperModule(), new AnalyzeModule());
        Wrapper wrapper = injector.getInstance(Wrapper.class);
        String wrappedMessage = wrapper.wrap("Obama respect @putinrf on http://twitter.com/");

        Assert.assertEquals("<strong>Obama</strong> respect @ <a href=\"http://twitter.com/putinrf\">putinrf</a> on <a href=\"http://twitter.com/\">http://twitter.com/</a>", wrappedMessage);
    }

    @Test
    public void testFakeWrappers2(){
        Injector injector = Guice.createInjector(new WrapperModule(), new AnalyzeModule());
        Wrapper wrapper = injector.getInstance(Wrapper.class);
        String wrappedMessage = wrapper.wrap("Obama visited Facebook headquarters: http://bit.ly/xyz @elversatile");


        Assert.assertEquals("<strong>Obama</strong> visited <strong>Facebook</strong> headquarters: <a href=\"http://bit.ly/xyz\">http://bit.ly/xyz</a> @ <a href=\"http://twitter.com/elversatile\">elversatile</a>", wrappedMessage);
    }
}
