package com.amos.amos.global.accelerex.infrastructure.persistence.repository;

import com.amos.amos.global.accelerex.infrastructure.persistence.CommentEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.EpisodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentEntityRepository extends JpaRepository<CommentEntity, Long> {
    List<CommentEntity> getCommentEntitiesByEpisodeEntity(EpisodeEntity episodeEntity);

}
