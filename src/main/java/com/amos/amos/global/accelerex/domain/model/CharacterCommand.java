package com.amos.amos.global.accelerex.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CharacterCommand {
    public String name;
    public String status;
    public String species;
    public String gender;
    public String origin;
    public String location;
    public String image;
    private Long id;

}
