package com.amos.amos.global.accelerex.domain;

import com.amos.amos.global.accelerex.domain.model.Location;
import com.amos.amos.global.accelerex.domain.model.Origin;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CharacterDomain {
    public Long id;
    public String name;
    public String status;
    public String species;
    public String type;
    public String gender;
    public Origin origin;
    public Location location;
    public String image;
    public ArrayList<String> episode = new ArrayList<String>();
    public String url;
    public String created;
}
