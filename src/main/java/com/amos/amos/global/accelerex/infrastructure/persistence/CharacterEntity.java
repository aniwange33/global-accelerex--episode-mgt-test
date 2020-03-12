package com.amos.amos.global.accelerex.infrastructure.persistence;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


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
    @ManyToMany(mappedBy = "characters")
    public List<EpisodeEntity> episodeEntities = new ArrayList<>();
}
