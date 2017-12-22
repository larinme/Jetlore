package com.modules.wrapper;

import com.entity.Message;

import java.util.Collection;

public interface ExtendedWrapper extends Wrapper {

    String wrap(Message message);

    Collection<String> wrap(Collection<Message> messages);
}
