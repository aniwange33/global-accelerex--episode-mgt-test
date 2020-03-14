package com.amos.amos.global.accelerex.infrastructure.persistence.dao.impl;

import com.amos.amos.global.accelerex.infrastructure.persistence.CommentEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.EpisodeEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.dao.CommentEntityDaoService;
import com.amos.amos.global.accelerex.infrastructure.persistence.repository.CommentEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentEntityDaoServiceImp implements CommentEntityDaoService {

    private final CommentEntityRepository commentEntityRepository;

    @Autowired
    public CommentEntityDaoServiceImp(CommentEntityRepository commentEntityRepository) {
        this.commentEntityRepository = commentEntityRepository;
    }


    @Override
    public CommentEntity saveRecord(CommentEntity commentEntity) {
        return commentEntityRepository.save(commentEntity);
    }

    @Override
    public Long countCommentEntity() {
        return null;
    }

    @Override
    public Optional<CommentEntity> selectCommentEntityByCommentId(Long commentId) {
        return Optional.empty();
    }

    @Override
    public List<CommentEntity> getCommentsByEpisodeId(EpisodeEntity episodeEntity) {
        return commentEntityRepository.getCommentEntitiesByEpisodeEntity(episodeEntity);
    }


}
