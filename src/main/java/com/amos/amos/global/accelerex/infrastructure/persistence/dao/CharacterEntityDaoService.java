package com.amos.amos.global.accelerex.infrastructure.persistence.dao;

import com.amos.amos.global.accelerex.domain.CharacterDomain;
import com.amos.amos.global.accelerex.infrastructure.persistence.CharacterEntity;

import java.awt.print.Pageable;
import java.util.List;

public interface CharacterEntityDaoService {
    CharacterEntity createNewCharacter(CharacterDomain characterDomain);
    Long countCharacters();
    List<CharacterEntity> selectAllCharacters(Pageable pageable, String filterParameter, String sortParameter);
}
