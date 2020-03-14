package com.amos.amos.global.accelerex.infrastructure.persistence.enumeration;

import lombok.Getter;

@Getter
public enum Status {
    ALIVE("Alive"),
    DEAD("Dead"),
    UNKNOWN("unknown");
    private final String name;

    Status(String name) {
        this.name = name;
    }

}
