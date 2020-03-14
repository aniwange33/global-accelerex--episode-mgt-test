package com.amos.amos.global.accelerex.infrastructure.persistence.dao.impl;

import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;


class EpisodeEntityDaoServiceImplTest {


    public static int getCharacterIdFromUrl(String url) {
        try {
            URI uri = new URI(url);
            String path = uri.getPath();
            String idStr = path.substring(path.lastIndexOf('/') + 1);
            return Integer.parseInt(idStr);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Test
    void shouldTestGetIdFromCharacterUrl() {
        assertEquals(1, getCharacterIdFromUrl("https://rickandmortyapi.com/api/character/1"));
    }
}