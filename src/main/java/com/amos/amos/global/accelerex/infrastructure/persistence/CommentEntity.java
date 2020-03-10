package com.amos.amos.global.accelerex.infrastructure.persistence;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long  id;
    String comment;
    String ip;
    Timestamp dateCreated;
    @ManyToOne(optional = false)
    EpisodeEntity episodeEntity;

}
