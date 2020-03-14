package com.amos.amos.global.accelerex.Usecase.api.model;

import lombok.Getter;

@Getter
public enum SortParameter {
    NAME("name"),
    GENDER("gender"),
    SPECIES("species");

    private final String name;

    SortParameter(String name) {
        this.name = name;
    }

}
