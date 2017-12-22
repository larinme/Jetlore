package com.modules.analyzer;

import com.entity.Message;

import java.util.Collection;

public interface MessageAnalyzer {

    Collection<Message> parseMessages(Collection<String> values);

    Message parseMessage(int id, String value);

    Collection<Message> parseFromURL(String url);
}
