package com.amos.amos.global.accelerex.domain;


import lombok.Data;

import java.util.ArrayList;

@Data
public class EpisodeDomain {
    public  Long id;
    public String name;
    public String air_date;
    public String episode;
    public String created;
    public String url;
    public ArrayList<String> characters = new ArrayList<String>();
}
