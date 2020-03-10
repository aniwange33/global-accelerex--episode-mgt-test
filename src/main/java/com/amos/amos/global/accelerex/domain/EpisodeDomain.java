package com.amos.amos.global.accelerex.domain;


import lombok.Data;

import java.util.ArrayList;

@Data
public class EpisodeDomain {
    Long id;
    String name;
    String air_date;
    String episode;
    ArrayList<String> characters = new ArrayList<String>();
}
