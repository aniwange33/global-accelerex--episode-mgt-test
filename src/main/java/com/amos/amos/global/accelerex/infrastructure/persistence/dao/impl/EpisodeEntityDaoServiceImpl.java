package com.amos.amos.global.accelerex.infrastructure.persistence.dao.impl;

import com.amos.amos.global.accelerex.domain.EpisodeDomain;
import com.amos.amos.global.accelerex.infrastructure.persistence.CharacterEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.EpisodeEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.dao.EpisodeEntityDaoService;
import com.amos.amos.global.accelerex.infrastructure.persistence.repository.CharacterEntityRepository;
import com.amos.amos.global.accelerex.infrastructure.persistence.repository.EpisodeEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EpisodeEntityDaoServiceImpl implements EpisodeEntityDaoService {
    private final EpisodeEntityRepository episodeEntityRepository;
    private final CharacterEntityRepository characterEntityRepository;

    @Autowired
    public EpisodeEntityDaoServiceImpl(EpisodeEntityRepository episodeEntityRepository,
                                       CharacterEntityRepository characterEntityRepository) {
        this.episodeEntityRepository = episodeEntityRepository;
        this.characterEntityRepository = characterEntityRepository;
    }

    public static int getCharacterIdFromUrl(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();
            String idStr = path.substring(path.lastIndexOf('/') + 1);
            return Integer.parseInt(idStr);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void createNewEpisodeEntity(EpisodeDomain episodeDomain) {
        Optional<EpisodeEntity> episodeEntity = episodeEntityRepository.findById(episodeDomain.id);
        if (episodeEntity.isEmpty()) {
            EpisodeEntity newEpisodeEntity = new EpisodeEntity();
            newEpisodeEntity.airDate = episodeDomain.air_date;
            newEpisodeEntity.episode = episodeDomain.episode;
            newEpisodeEntity.episodeId = episodeDomain.id;
            newEpisodeEntity.name = episodeDomain.name;
            newEpisodeEntity.url = episodeDomain.url;
            newEpisodeEntity.url = episodeDomain.created;
            newEpisodeEntity.characters = episodeDomain.characters.stream()
                    .map(this::getCharacterEntity)
                    .collect(Collectors.toList());
            episodeEntityRepository.save(newEpisodeEntity);
        }
    }

    @Override
    public Long countEpisodeEntity() {
        return episodeEntityRepository.count();
    }

    @Override
    public Optional<EpisodeEntity> selectEpisodeEntityByEpisodeId(Long episodeId) {
        return episodeEntityRepository.findById(episodeId);
    }

    @Override
    public List<EpisodeEntity> selectAllEpisodeEntity() {
        return episodeEntityRepository.getAllByCreatedNullOrderByCreatedDesc();
    }

    private CharacterEntity getCharacterEntity(String url) {
        try {
            return characterEntityRepository
                    .getByCharacterId((long) getCharacterIdFromUrl(url))
                    .orElseThrow(() -> new Exception("not found"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
