package com.amos.amos.global.accelerex.infrastructure.persistence.enumeration;

import lombok.Getter;

@Getter
public enum Gender {
    MALE("Male"),
    FEMALE("Female");
    private final String name;

    Gender(String name) {
        this.name = name;
    }
}
