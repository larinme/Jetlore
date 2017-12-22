package com.modules.crawler;

import java.util.Collection;

public interface Crawler {

    Collection<String> crawl(String url);

}
