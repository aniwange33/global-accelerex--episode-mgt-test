package com.amos.amos.global.accelerex.infrastructure.persistence.repository;

import com.amos.amos.global.accelerex.infrastructure.persistence.CharacterEntity;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CharacterEntityRepository extends PagingAndSortingRepository<CharacterEntity, Long> {
    List<CharacterEntity> getAllByCreatedNotNull();

    Optional<CharacterEntity> getByCharacterId(Long characterId);
}
