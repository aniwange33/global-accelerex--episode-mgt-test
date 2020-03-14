package com.amos.amos.global.accelerex.Usecase.api;

import com.amos.amos.global.accelerex.Usecase.api.model.CharacterCommandResponse;
import com.amos.amos.global.accelerex.domain.CommentDomain;
import com.amos.amos.global.accelerex.domain.model.EpisodeCommand;

import java.util.List;

public interface EpisodeUseCase {

    CharacterCommandResponse getCharactersOfAnEpisode(Long episodeId, String sortParameter, String filter);

    List<EpisodeCommand> getListOfEpisode();

    List<CommentDomain> getCommentsOfAnEpisode(Long episodeId);

    void postComment(Long episodeId, String comment);
}
