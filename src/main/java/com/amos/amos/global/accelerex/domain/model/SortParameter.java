package com.amos.amos.global.accelerex.domain.model;

import lombok.Data;

@Data
public class SortParameter {
    public String name;
    public String gender;
    public String species;

    public SortParameter(String name, String gender, String species) {
        this.name = name;
        this.gender = gender;
        this.species = species;
    }
}
