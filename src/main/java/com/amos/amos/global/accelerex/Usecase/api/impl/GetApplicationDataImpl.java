package com.amos.amos.global.accelerex.Usecase.api.impl;

import com.amos.amos.global.accelerex.Usecase.api.GetApplicationData;
import com.amos.amos.global.accelerex.domain.model.CharacterApiResponse;
import com.amos.amos.global.accelerex.domain.model.EpisodeApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import javax.inject.Named;


@Named
@Slf4j
public class GetApplicationDataImpl implements GetApplicationData {
    private final RestTemplate restTemplate;

    @Autowired
    public GetApplicationDataImpl(@Qualifier("template") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public EpisodeApiResponse getEpisodeData(String url) {
        EpisodeApiResponse responseBody = null;
        try {
            final ResponseEntity<EpisodeApiResponse> response =
                    restTemplate.getForEntity(url, EpisodeApiResponse.class);
            if (response.hasBody()) {
                responseBody = response.getBody();
                log.info("response body {}", responseBody);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return responseBody;
    }

    @Override
    public CharacterApiResponse getCharacterData(String url) {
        CharacterApiResponse responseBody = null;
        try {
            final ResponseEntity<CharacterApiResponse> response =
                    restTemplate.getForEntity(url,  CharacterApiResponse.class);
            if (response.hasBody()) {
                responseBody = response.getBody();
                log.info("response body {}", responseBody);
            }
        } catch (RestClientException e) {
            e.printStackTrace();
        }
        return responseBody;
    }


}
