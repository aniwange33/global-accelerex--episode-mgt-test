package com.amos.amos.global.accelerex.domain.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EpisodeCommand {
    public Long id;
    public String name;
    public String air_date;
    public String episode;
    public String url;
    public int commentCount;
}
