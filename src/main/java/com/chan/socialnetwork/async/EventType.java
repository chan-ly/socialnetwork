package com.chan.socialnetwork.async;

public enum EventType {
    LIKE(0),
    COMMENT(1),
    LOGIN(2),
    MAIL(3),
    SOCRE(4);

    private int value;
    EventType(int value) {
        this.value = value;
    }
}
