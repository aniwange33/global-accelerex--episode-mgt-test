package com.amos.amos.global.accelerex.infrastructure.gatewayImpl;

import com.amos.amos.global.accelerex.Usecase.api.exception.InValidRequest;
import com.amos.amos.global.accelerex.domain.CommentDomain;
import com.amos.amos.global.accelerex.domain.gateway.DomainServiceGateway;
import com.amos.amos.global.accelerex.domain.model.CharacterCommand;
import com.amos.amos.global.accelerex.domain.model.EpisodeCommand;
import com.amos.amos.global.accelerex.infrastructure.persistence.CommentEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.EpisodeEntity;
import com.amos.amos.global.accelerex.infrastructure.persistence.dao.CommentEntityDaoService;
import com.amos.amos.global.accelerex.infrastructure.persistence.dao.EpisodeEntityDaoService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service("domainGateway")
public class ApplicationDomainGatewayImpl implements DomainServiceGateway {
    private final EpisodeEntityDaoService episodeEntityDaoService;
    private final CommentEntityDaoService commentEntityDaoService;

    @Autowired
    public ApplicationDomainGatewayImpl(EpisodeEntityDaoService episodeEntityDaoService,
                                        CommentEntityDaoService commentEntityDaoService) {
        this.episodeEntityDaoService = episodeEntityDaoService;
        this.commentEntityDaoService = commentEntityDaoService;
    }

    @Override
    public List<CharacterCommand> listEpisodeCharacters(Long episodeId) {
        final EpisodeEntity episodeEntity =
                episodeEntityDaoService.selectEpisodeEntityByEpisodeId(episodeId)
                        .orElseThrow(() ->
                                new InValidRequest("Episode with " + episodeId + " " + "not found"));
        val characterList = episodeEntity.characters;
        return characterList.stream()
                .map(e -> CharacterCommand
                        .builder()
                        .image(e.image)
                        .species(e.species)
                        .location(e.location)
                        .origin(e.origin)
                        .id(e.id)
                        .name(e.name)
                        .gender(e.gender)
                        .status(e.status)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<EpisodeCommand> listEpisode() {
        return episodeEntityDaoService.selectAllEpisodeEntity()
                .stream()
                .map(episodeEntity -> EpisodeCommand.builder()
                        .air_date(episodeEntity.airDate)
                        .commentCount(commentEntityDaoService
                                .getCommentsByEpisodeId(episodeEntity).size())
                        .id(episodeEntity.episodeId)
                        .episode(episodeEntity.episode)
                        .name(episodeEntity.name)
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentDomain> listEpisodeComments(Long episodeId) {
        val episode = episodeEntityDaoService.selectEpisodeEntityByEpisodeId(episodeId).orElseThrow
                (() -> new InValidRequest("Episode with this " + episodeId + " is not found"));
        return commentEntityDaoService.getCommentsByEpisodeId(episode).stream()
                .map(commentEntity ->
                        new CommentDomain(
                                commentEntity.id,
                                commentEntity.comment,
                                commentEntity.ipAddress,
                                commentEntity.dateCreated.toString()
                        )
                )
                .sorted(Comparator.comparing(CommentDomain::getComment).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public void postComments(Long episodeId, String comment) {
        val episode = episodeEntityDaoService.selectEpisodeEntityByEpisodeId(episodeId).orElseThrow
                (() -> new InValidRequest("Episode with this " + episodeId + " is not found"));
        if (comment.isEmpty()) throw new InValidRequest("comments cannot be empty");
        if (comment.length() > 500) throw new InValidRequest("comment  should be less than 500 characters");
        val commentEntity = new CommentEntity();
        try {
            InetAddress myIP = InetAddress.getLocalHost();
            commentEntity.comment = comment;
            commentEntity.dateCreated = new Timestamp(System.currentTimeMillis());
            commentEntity.ipAddress = myIP.getHostAddress();
            commentEntity.setEpisodeEntity(episode);
        } catch (UnknownHostException e) {
            throw new InValidRequest("Host not found");
        }
        commentEntityDaoService.saveRecord(commentEntity);
    }
}
