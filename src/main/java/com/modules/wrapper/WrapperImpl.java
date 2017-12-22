package com.modules.wrapper;

import com.entity.Message;
import com.entity.Token;
import com.google.inject.Inject;
import com.modules.analyzer.MessageAnalyzer;

import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class WrapperImpl implements ExtendedWrapper {

    private final MessageAnalyzer messageAnalyzer;

    @Inject
    public WrapperImpl(MessageAnalyzer messageAnalyzer) {
        this.messageAnalyzer = messageAnalyzer;
    }

    public String wrap(List<Token> tokens) {
        return tokens.stream().map(Token::toString).collect(Collectors.joining(" "));
    }

    @Override
    public String wrap(Message message) {
        return wrap(message.getTokens());
    }

    @Override
    public Collection<String> wrap(Collection<Message> messages) {
        return messages.stream()
                .map((this::wrap))
                .collect(Collectors.toList());
    }

    @Override
    public String wrap(String text) {
        Random random = new Random();
        Message message = messageAnalyzer.parseMessage(random.nextInt() * 100, text);
        return wrap(message);
    }
}
