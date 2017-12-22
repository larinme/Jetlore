package com.entity;

import java.util.List;

public class Message {

    private final long id;
    private final String value;
    private final List<Token> tokens;

    public Message(long id, String value, List<Token> tokens) {
        this.id = id;
        this.value = value;
        this.tokens = tokens;
    }

    public long getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Message message = (Message) o;

        return id == message.id;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", value='" + value + '\'' +
                '}';
    }
}
