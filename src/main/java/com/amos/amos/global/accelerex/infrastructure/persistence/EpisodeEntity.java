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
    Long episodeId;
    String name;
    String airDate;
    String episode;
    String url;
    String created;
    @OneToMany
    List<CharacterEntity> characters = new ArrayList<>();
}
