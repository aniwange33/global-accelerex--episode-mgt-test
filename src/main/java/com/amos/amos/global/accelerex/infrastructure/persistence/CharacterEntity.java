package com.amos.amos.global.accelerex.infrastructure.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Data
@NoArgsConstructor
@Entity
public class CharacterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    public Long characterId;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public String origin;
    public String location;
    public String image;
    public String url;
    public String created;

}
