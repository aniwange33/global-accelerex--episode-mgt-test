package com.amos.amos.global.accelerex.infrastructure.persistence.dao;

import com.amos.amos.global.accelerex.domain.EpisodeDomain;
import com.amos.amos.global.accelerex.infrastructure.persistence.EpisodeEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface EpisodeEntityDaoService {
    EpisodeEntity createNewEpisodeEntity(EpisodeDomain episodeDomain);

    Long countEpisodeEntity();

    List<EpisodeEntity> selectAllEpisodeEntity(Pageable pageable);
}
