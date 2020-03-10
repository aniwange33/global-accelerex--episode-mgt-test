package com.amos.amos.global.accelerex.domain.model;

import com.amos.amos.global.accelerex.domain.CharacterDomain;
import lombok.Data;

import java.util.ArrayList;

@Data
public class CharacterApiResponse {
    public Info info;
    public ArrayList<CharacterDomain> results =  new   ArrayList<CharacterDomain>();
}
