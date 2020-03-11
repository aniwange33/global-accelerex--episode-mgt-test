package com.amos.amos.global.accelerex.infrastructure.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class EpisodeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    public Long episodeId;
    public String name;
    public String airDate;
    public String episode;
    public String url;
    public String created;
    @OneToMany
    public List<CharacterEntity> characters = new ArrayList<>();
}
