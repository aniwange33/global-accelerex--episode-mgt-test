package com.amos.amos.global.accelerex.infrastructure.persistence.dao;

import com.amos.amos.global.accelerex.infrastructure.persistence.CommentEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.EpisodeEntity;

import java.util.List;
import java.util.Optional;

public interface CommentEntityDaoService {

    CommentEntity saveRecord(CommentEntity commentEntity);

    Long countCommentEntity();

    Optional<CommentEntity> selectCommentEntityByCommentId(Long commentId);

    List<CommentEntity> getCommentsByEpisodeId(EpisodeEntity episodeEntity);

}
