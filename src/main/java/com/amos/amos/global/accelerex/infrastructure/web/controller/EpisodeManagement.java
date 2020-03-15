package com.amos.amos.global.accelerex.infrastructure.web.controller;

import com.amos.amos.global.accelerex.Usecase.api.EpisodeUseCase;
import com.amos.amos.global.accelerex.Usecase.api.model.CharacterCommandResponse;
import com.amos.amos.global.accelerex.domain.CommentDomain;
import com.amos.amos.global.accelerex.domain.model.EpisodeCommand;
import com.amos.amos.global.accelerex.infrastructure.web.model.ApplicationApiResponse;
import com.amos.amos.global.accelerex.infrastructure.web.model.CommentRequest;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/episode/")
public class EpisodeManagement {
    private final EpisodeUseCase episodeUseCase;

    @Autowired
    public EpisodeManagement(EpisodeUseCase episodeUseCase) {
        this.episodeUseCase = episodeUseCase;
    }

    @GetMapping
    public ResponseEntity<ApplicationApiResponse<List<EpisodeCommand>>> listEpisodes() {
        val response = new ApplicationApiResponse<List<EpisodeCommand>>("success");
        response.data = episodeUseCase.getListOfEpisode();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "List  characters of an episode", notes = "accepted sort term are: [name, gender or species] " +
            "and filter term are [male, female, alive, dead, unknown] ")
    @GetMapping("{episodeId}/characters")
    public ResponseEntity<ApplicationApiResponse<CharacterCommandResponse>> listCharactersOfAnEpisode(
            @PathVariable("episodeId") Long episodeId,
            @RequestParam(value = "sortTerm", required = true) String sortTerm,
            @RequestParam(value = "filterTerm", required = true) String filterTerm) {
        val response = new ApplicationApiResponse<CharacterCommandResponse>("success");
        response.data =
                episodeUseCase.getCharactersOfAnEpisode(
                        episodeId,
                        sortTerm.toUpperCase(),
                        filterTerm.toUpperCase());
        return ResponseEntity.ok(response);
    }

    @GetMapping("{episodeId}/comments")
    public ResponseEntity<ApplicationApiResponse<CommentDomain>> listCommentsOfAnEpisode(
            @PathVariable("episodeId") Long episodeId) {
        val response = new ApplicationApiResponse<CommentDomain>("success");
        response.data = episodeUseCase.getCommentsOfAnEpisode(episodeId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("{episodeId}/comment")
    public ResponseEntity<ApplicationApiResponse<String>> postAComment(
            @RequestBody CommentRequest comment, @PathVariable("episodeId") Long episodeId) {
        episodeUseCase.postComment(episodeId, comment.getComment());
        val response = new ApplicationApiResponse<String>("success", "comment posted successful");
        return ResponseEntity.ok(response);
    }

}
