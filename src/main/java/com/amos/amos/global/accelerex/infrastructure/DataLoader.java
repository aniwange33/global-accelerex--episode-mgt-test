package com.amos.amos.global.accelerex.infrastructure;

import com.amos.amos.global.accelerex.Usecase.api.ApplicationDataUseCase;
import com.amos.amos.global.accelerex.Usecase.api.impl.ApplicationPropertyConfig;
import com.amos.amos.global.accelerex.domain.model.CharacterApiResponse;
import com.amos.amos.global.accelerex.domain.model.EpisodeApiResponse;
import com.amos.amos.global.accelerex.infrastructure.persistence.dao.CharacterEntityDaoService;
import com.amos.amos.global.accelerex.infrastructure.persistence.dao.EpisodeEntityDaoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {
    private final ApplicationDataUseCase getApplicationData;
    private final ApplicationPropertyConfig applicationPropertyConfig;
    private final EpisodeEntityDaoService episodeEntityDaoService;
    private final CharacterEntityDaoService characterEntityDaoService;

    @Autowired
    public DataLoader(ApplicationDataUseCase getApplicationData,
                      ApplicationPropertyConfig applicationPropertyConfig,
                      EpisodeEntityDaoService episodeEntityDaoService,
                      @Qualifier("realData") CharacterEntityDaoService characterEntityDaoService) {
        this.getApplicationData = getApplicationData;
        this.applicationPropertyConfig = applicationPropertyConfig;
        this.episodeEntityDaoService = episodeEntityDaoService;
        this.characterEntityDaoService = characterEntityDaoService;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        // this order is important

        if (characterEntityDaoService.countCharacters() == 0) {
            CharacterApiResponse characterData = getApplicationData
                    .getCharacterData(applicationPropertyConfig.characters);
            for (int i = 2; i <= characterData.info.pages; i++) {
                characterData.results
                        .addAll(getApplicationData
                                .getCharacterData(applicationPropertyConfig.characters + "/?page=" + i).results);
            }
            characterData.results.forEach(characterEntityDaoService::createNewCharacter);
            log.info("total character size, {}", characterData.results.size());
        }

        if (episodeEntityDaoService.countEpisodeEntity() == 0) {
            EpisodeApiResponse episodeData = getApplicationData.getEpisodeData(applicationPropertyConfig.episodes);
            if (!episodeData.info.next.isEmpty()) {
                episodeData.results.addAll(getApplicationData.getEpisodeData(episodeData.info.next).results);
            }
            log.info("total episodes size, {}", episodeData.results.size());
            episodeData.results.forEach(episodeEntityDaoService::createNewEpisodeEntity);
        }
    }

}
