package com.amos.amos.global.accelerex.infrastructure.persistence.dao.impl;

import com.amos.amos.global.accelerex.domain.CharacterDomain;
import com.amos.amos.global.accelerex.infrastructure.persistence.CharacterEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.dao.CharacterEntityDaoService;
import com.amos.amos.global.accelerex.infrastructure.persistence.enumeration.Status;
import com.amos.amos.global.accelerex.infrastructure.persistence.repository.CharacterEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service("realData")
public class CharacterEntityDaoServiceImpl implements CharacterEntityDaoService {

    private final CharacterEntityRepository characterEntityRepository;

    @Autowired
    public CharacterEntityDaoServiceImpl(CharacterEntityRepository characterEntityRepository) {
        this.characterEntityRepository = characterEntityRepository;
    }

    @Override
    public void createNewCharacter(CharacterDomain characterDomain) {
        final Optional<CharacterEntity> characterEntityOld =
                selectCharacterEntityByCharacterId(characterDomain.id);
        if (characterEntityOld.isEmpty()) {
            CharacterEntity newCharacterEntity = new CharacterEntity();
            newCharacterEntity.characterId = characterDomain.id;
            newCharacterEntity.gender = characterDomain.gender;
            newCharacterEntity.name = characterDomain.name;
            newCharacterEntity.created = characterDomain.created;
            newCharacterEntity.image = characterDomain.image;
            newCharacterEntity.location = characterDomain.location.name;
            newCharacterEntity.origin = characterDomain.origin.name;
            newCharacterEntity.species = characterDomain.species;
            newCharacterEntity.type = characterDomain.type;
            newCharacterEntity.url = characterDomain.url;
            newCharacterEntity.status = characterDomain.status;
            characterEntityRepository.save(newCharacterEntity);
        }
    }

    @Override
    public Long countCharacters() {
        return characterEntityRepository.count();
    }

    @Override
    public Optional<CharacterEntity> selectCharacterEntityByCharacterId(Long characterId) {
        return characterEntityRepository.getByCharacterId(characterId);
    }

    @Override
    public List<CharacterEntity> selectAllCharacters(
            String name,
            String gender,
            String species,
            String filter) {
        final Iterable<CharacterEntity> result =
                characterEntityRepository.findAll(Sort.by(name, gender, species));
        List<CharacterEntity> characterEntityList = new ArrayList<>();
        result.forEach(characterEntityList::add);
        System.out.println("Before filter " + characterEntityList.size());
        if (filter.equals(Status.valueOf(filter).name())) {
            final List<CharacterEntity> characterEntities = characterEntityList.stream()
                    .filter(e -> e.status.equals(filter))
                    .collect(Collectors.toList());
            return characterEntities;
        } else {
            final List<CharacterEntity> characterEntities = characterEntityList.stream()
                    .filter(e -> e.gender.equals(gender))
                    .collect(Collectors.toList());
            System.out.println("After filter " + characterEntityList.size());
            return characterEntities;
        }
    }

    @Override
    public CharacterDomain convertCharacterEntityToCharacterDomain(CharacterEntity characterEntity) {
        CharacterDomain characterDomain = new CharacterDomain();
        characterDomain.created = characterEntity.created;
        characterDomain.id = characterEntity.characterId;
        characterDomain.gender = characterEntity.gender;
        characterDomain.name = characterEntity.name;
        characterDomain.image = characterEntity.image;
        characterDomain.location.name = characterEntity.location;
        characterDomain.origin.name = characterEntity.origin;
        characterDomain.species = characterEntity.species;
        characterDomain.type = characterEntity.type;
        characterDomain.url = characterEntity.url;
        characterDomain.status = characterEntity.status;
        return characterDomain;
    }
}
