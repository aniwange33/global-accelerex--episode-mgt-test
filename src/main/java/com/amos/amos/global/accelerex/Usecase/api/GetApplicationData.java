package com.amos.amos.global.accelerex.Usecase.api;

import com.amos.amos.global.accelerex.domain.model.CharacterApiResponse;
import com.amos.amos.global.accelerex.domain.model.EpisodeApiResponse;

public interface GetApplicationData {
    EpisodeApiResponse getEpisodeData(String url);
    CharacterApiResponse getCharacterData(String url);
}
