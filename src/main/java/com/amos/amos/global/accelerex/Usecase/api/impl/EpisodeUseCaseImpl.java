package com.amos.amos.global.accelerex.Usecase.api.impl;

import com.amos.amos.global.accelerex.Usecase.api.EpisodeUseCase;
import com.amos.amos.global.accelerex.Usecase.api.exception.InValidRequest;
import com.amos.amos.global.accelerex.Usecase.api.model.CharacterCommandResponse;
import com.amos.amos.global.accelerex.Usecase.api.model.SortParameter;
import com.amos.amos.global.accelerex.domain.CommentDomain;
import com.amos.amos.global.accelerex.domain.gateway.DomainServiceGateway;
import com.amos.amos.global.accelerex.domain.model.CharacterCommand;
import com.amos.amos.global.accelerex.domain.model.EpisodeCommand;
import com.amos.amos.global.accelerex.infrastructure.persistence.enumeration.Gender;
import com.amos.amos.global.accelerex.infrastructure.persistence.enumeration.Status;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EpisodeUseCaseImpl implements EpisodeUseCase {

    private final DomainServiceGateway applicationDomainGateway;


    @Autowired
    public EpisodeUseCaseImpl(@Qualifier("domainGateway")
                                      DomainServiceGateway applicationDomainGateway) {
        this.applicationDomainGateway = applicationDomainGateway;
    }


    @Override
    public CharacterCommandResponse getCharactersOfAnEpisode(
            Long episodeId,
            String sortParameter,
            String filter) {

        if (!isGender(filter) && !isStatus(filter)) throw new InValidRequest("Invalid filter parameters, " +
                "kindly use gender or status");
        if (!isSortParameterEnum(sortParameter)) throw new InValidRequest("Invalid sort parameter");
        List<CharacterCommand> characterCommands = applicationDomainGateway.listEpisodeCharacters(episodeId);
        if (isGender(filter)) {
            final val filterList = characterCommands.stream()
                    .filter(characterCommand -> characterCommand.gender.equalsIgnoreCase(filter))
                    .collect(Collectors.toList());
            if (SortParameter.GENDER.getName().equalsIgnoreCase(sortParameter)) {
                final val sortedList = filterList.stream()
                        .sorted(Comparator.comparing(CharacterCommand::getGender))
                        .collect(Collectors.toList());
                return new CharacterCommandResponse(sortedList, sortedList.size());
            }
            if (SortParameter.NAME.getName().equalsIgnoreCase(sortParameter)) {
                final val sortedList = filterList.stream()
                        .sorted(Comparator.comparing(CharacterCommand::getName))
                        .collect(Collectors.toList());
                return new CharacterCommandResponse(sortedList, sortedList.size());
            }
            if (SortParameter.SPECIES.getName().equalsIgnoreCase(sortParameter)) {
                final List<CharacterCommand> sortedList = filterList.stream()
                        .sorted(Comparator.comparing(CharacterCommand::getSpecies).reversed())
                        .collect(Collectors.toList());
                return new CharacterCommandResponse(sortedList, sortedList.size());
            }
        } else {
            final val filterList = characterCommands.stream()
                    .filter(characterCommand -> characterCommand.status.equalsIgnoreCase(filter))
                    .collect(Collectors.toList());
            if (SortParameter.GENDER.getName().equalsIgnoreCase(sortParameter)) {
                val sortedList = filterList.stream()
                        .sorted(Comparator.comparing(CharacterCommand::getGender).reversed())
                        .collect(Collectors.toList());
                return new CharacterCommandResponse(sortedList, sortedList.size());
            }
            if (SortParameter.NAME.getName().equalsIgnoreCase(sortParameter)) {
                val sortedList = filterList.stream()
                        .sorted(Comparator.comparing(CharacterCommand::getName))
                        .collect(Collectors.toList());
                return new CharacterCommandResponse(sortedList, sortedList.size());
            }
            if (SortParameter.SPECIES.getName().equalsIgnoreCase(sortParameter)) {
                val sortedList = filterList.stream()
                        .sorted(Comparator.comparing(CharacterCommand::getSpecies).reversed())
                        .collect(Collectors.toList());
                return new CharacterCommandResponse(sortedList, sortedList.size());
            }

        }
        return new CharacterCommandResponse(characterCommands, characterCommands.size());
    }


    @Override
    public List<EpisodeCommand> getListOfEpisode() {
        return applicationDomainGateway.listEpisode();
    }

    @Override
    public List<CommentDomain> getCommentsOfAnEpisode(Long episodeId) {
        return applicationDomainGateway.listEpisodeComments(episodeId);
    }


    @Override
    public void postComment(Long episodeId, String comment) {
        if (comment.isEmpty()) throw new InValidRequest("comment can not be empty");
        applicationDomainGateway.postComments(episodeId, comment);
    }

    private boolean isGender(String gender) {
        try {
            Gender.valueOf(gender);
            return true;
        } catch (IllegalArgumentException e) {
            log.info(" {}", "wrong filter parameter " + gender);
        }
        return false;
    }

    private boolean isStatus(String status) {
        try {
            Status.valueOf(status);
            return true;
        } catch (IllegalArgumentException e) {
            log.info(" {}", "wrong filter parameter " + status);
        }
        return false;
    }

    private boolean isSortParameterEnum(String sort) {
        try {
            SortParameter.valueOf(sort);
            return true;
        } catch (IllegalArgumentException e) {
            log.info(" {}", "wrong sort parameter " + sort);
        }
        return false;
    }

}
