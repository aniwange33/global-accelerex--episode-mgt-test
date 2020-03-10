package com.amos.amos.global.accelerex.domain;

import com.amos.amos.global.accelerex.domain.model.Location;
import com.amos.amos.global.accelerex.domain.model.Origin;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CharacterDomain {
    Long id;
    String name;
    String status;
    String species;
    String type;
    String gender;
    Origin OriginObject;
    Location LocationObject;
    String image;
    ArrayList<String> episode = new ArrayList<String>();
    String url;
    String created;
}
